package com.springboot.springmvc.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	

}
