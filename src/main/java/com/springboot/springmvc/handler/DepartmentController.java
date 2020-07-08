package com.springboot.springmvc.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bean.Department;
import com.springboot.entity.Msg;
import com.springboot.spirng.service.DepartmentService;



//处理和部门有关的请求
@CrossOrigin
@RestController
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	//返回所有部门信息
	@RequestMapping("/depts")
	public Msg getDepts() {
		//查出所有部门信息
		List<Department> list=departmentService.getDepts();
		return Msg.success().add("depts", list);
	}

}
