package com.springboot.spirng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bean.Department;
import com.springboot.mybatis.dao.DepartmentMapper;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	//获得所有部门信息
	public List<Department> getDepts() {
		// TODO Auto-generated method stub		
		return departmentMapper.selectByExample(null);
	}

}
