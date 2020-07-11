package com.springboot.springmvc.handler;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.bean.Department;
import com.springboot.entity.Msg;
import com.springboot.spirng.service.DepartmentService;



//处理和部门有关的请求
@CrossOrigin
@RestController
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//返回所有部门信息
	@RequestMapping("/depts")
	public Msg getDepts() {
		//查出所有部门信息
		List<Department> list=departmentService.getDepts();
		return Msg.success().add("depts", list);
	}
	
	@RequestMapping("/dept")
	public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1")Integer pn) {
//		引入pagehelper分页插件
//		查询之前传入页码,以及每页大小
		PageHelper.startPage(pn, 5);
		List<Department> depts=departmentService.getAll();
		/* 使用pageinfo包装查询结果,只需要将pageinfo交给页面 
		 * 封装了详细的分页信息，包括查询的数据
		 * 传入连续显示的页数
		 * */
		//用PageInfo对结果进行包装
		  PageInfo<Department> page=new PageInfo<Department>(depts,5); 
		  logger.debug(Msg.success().add("pageInfo",page).toString()); 
		  return Msg.success().add("pageInfo", page);

	}
	/* 添加部门
	 * 
	 *  */
	@RequestMapping(value="/adddept",method = RequestMethod.POST)
	public Msg saveDept(@Valid Department department,BindingResult result) {
		departmentService.saveDept(department);
		return Msg.success();
	}
	
	//检查部门名是否重复
	@RequestMapping("/checkdept")
	public Msg checkUser(String deptName) {
		//数据库用户查重
		logger.info(deptName);
		boolean b=departmentService.checkDept(deptName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "用户名已存在");
		}
		
	}
	
	//根据id查询部门
	@RequestMapping(value = "/dept/{id}",method = RequestMethod.GET)
	public Msg getDept(@PathVariable("id") Integer id) {
		Department department=departmentService.getDept(id);
		return Msg.success().add("dept", department);
	}
	//更新部门
	@RequestMapping(value = "/dept/{id}",method = RequestMethod.PUT)
	public Msg saveDept(Department department) {
		logger.info("将要更新的数据"+department);
		departmentService.updaeDept(department);
		return Msg.success();
	}
	
	

}
