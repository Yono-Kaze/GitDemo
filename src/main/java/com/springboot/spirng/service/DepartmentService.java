package com.springboot.spirng.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.bean.Department;
import com.springboot.bean.DepartmentExample;
import com.springboot.bean.DepartmentExample.Criteria;
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
	//获得所有部门信息,用于分页查询
	public List<Department> getAll() {
		// TODO Auto-generated method stub
		return departmentMapper.selectByExample(null);
	}
	//保存部门
	public void saveDept(@Valid Department department) {
		// TODO Auto-generated method stub
		departmentMapper.insertSelective(department);
		
	}
	//检查部门名是否重复
	public boolean checkDept(@RequestParam("deptName") String deptName) {
		DepartmentExample example=new DepartmentExample();
		Criteria criteria =example.createCriteria();
		criteria.andDeptnameEqualTo(deptName);
		long count=departmentMapper.countByExample(example);
		return count==0;
	}
	//根据部门ID查询
	public Department getDept(Integer id) {
		// TODO Auto-generated method stub
		Department department=departmentMapper.selectByPrimaryKey(id);
		return department;
	}
	//更新员工
	public void updaeDept(Department department) {
		// TODO Auto-generated method stub
		departmentMapper.updateByPrimaryKeySelective(department);
		
	}

}
