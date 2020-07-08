package com.springboot.springmvc.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.bean.Employee;
import com.springboot.entity.Msg;
import com.springboot.spirng.service.EmployeeService;

/*处理和员工查询有关的请求*/
@CrossOrigin
@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//测试springmvc
	@RequestMapping("/test")
	public String test() {
		return "springboot运行成功";
	}
	
	/* 查询员工数据 
	 * 需要返回json数据
	 * 同时需要分页查询 引入pagehelper分页插件
	 * 
	 * */
	@RequestMapping("/emps")
	public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1")Integer pn) {
//		引入pagehelper分页插件
//		查询之前传入页码,以及每页大小
		PageHelper.startPage(pn, 5);
		List<Employee> emps=employeeService.getAll();
		/* 使用pageinfo包装查询结果,只需要将pageinfo交给页面 
		 * 封装了详细的分页信息，包括查询的数据
		 * 传入连续显示的页数
		 * */
		//用PageInfo对结果进行包装
		  PageInfo<Employee> page=new PageInfo<Employee>(emps,5); 
		  logger.debug(Msg.success().add("pageInfo",page).toString()); 
		  return Msg.success().add("pageInfo", page);

	}
	
	/* 员工保存
	 * 
	 *  1.支持jsr303校验
	 *  2.Hibernate Validator Engine
	 *  */
	@RequestMapping(value="/emp",method = RequestMethod.POST)
	public Msg saveEmp(@Valid Employee employee,BindingResult result) {
		if(result.hasErrors()) {
			//校验失败，返回失败，在模态框中显示失败信息
			Map<String, Object> map=new HashMap<>();
			List<FieldError> errors=result.getFieldErrors();
			for(FieldError fieldError:errors) {
				System.out.println("错误的字段名"+fieldError.getField());
				System.out.println("错误信息"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
			System.out.println(employee);
			return Msg.success();	
		}
	}	
	
	//检验用户名是否可用
	@RequestMapping("/checkuser")
	public Msg checkUser(String empName) {
		//先判断用户名是否合法
		String regx="(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!empName.matches(regx)) {
			return Msg.fail().add("va_msg", "用户名必须是2-5位的中文或者6-11的英文和数字的组合");
		};
		//数据库用户查重
		boolean b=employeeService.checkUser(empName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "用户名已存在");
		}
		
	}	
	
	//根据id查询员工
	@RequestMapping(value="/emp/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id") Integer id) {
		Employee employee=employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	/* 员工更新方法 
	 * 
	 * 注：如果直接发送ajax=PUT请求,	请求体中有数据但是employee对象封装不上
	 * sql:update tbl_empp where emp_id=1014;
	 * 原因：1.Tomcat 将请求体中数据封装一个map，2.request.getparameter("empName")就会从map中取值
	 * 3.springmvc封装pojo对象时候会把pojo中的每个属性的值，request.getparameter("email")
	 * PUT请求，请求体中的数据拿不到，Tomcat发现是PUT请求就不会封装请求体重的数据为map只有POST的请求才封装请求体
	 * 要直接发送PUT请求需要配置上HttpPutFormContentFilter
	 * 作用：将请求体中数据解析包装成一个map,request被重新包装
	 * 
	 * */
	@RequestMapping(value = "/emp/{empid}",method=RequestMethod.PUT)
	public Msg saveEmp(Employee employee) {
		System.out.println("将要更新的员工数据"+employee);
		employeeService.updateEmp(employee);
		return Msg.success();
	}	

	/* 删除员工 
	 * 单个批量组合
	 * */
	@RequestMapping(value = "/emp/{ids}",method=RequestMethod.DELETE)
	public Msg deleteEmpById(@PathVariable("ids") String ids) {
		if(ids.contains("-")) {
			String[] str_ids=ids.split("-");
			List<Integer> del_ids=new ArrayList<>();
			//组装id的集合
			for(String string:str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			System.out.println(del_ids);
			employeeService.deleteBatch(del_ids);
		}else {
			Integer id=Integer.parseInt(ids);
			System.out.println(id);
			employeeService.deleteEmp(id);			
		}
		return Msg.success();
	}	
	
	

}
