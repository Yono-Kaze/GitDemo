package com.springboot.spirng.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.bean.Employee;
import com.springboot.bean.EmployeeExample;
import com.springboot.bean.EmployeeExample.Criteria;
import com.springboot.mybatis.dao.EmployeeMapper;



@Service
public class EmployeeService {
	
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	
	
	//查询所有员工
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return employeeMapper.selectByExampleWithDept(null);
	}

	//保存员工
	public void saveEmp(@Valid Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.insertSelective(employee);
		
	}

	//检查用户名是否重复
	public boolean checkUser(@RequestParam("empName") String empName) {
		// TODO Auto-generated method stub
		EmployeeExample example=new EmployeeExample();
		Criteria criteria =example.createCriteria();
		criteria.andEmpnameEqualTo(empName);
		long count=employeeMapper.countByExample(example);
		return count==0;
	}
	//根据员工id查询员工
	public Employee getEmp(Integer id) {
		// TODO Auto-generated method stub
		Employee employee=employeeMapper.selectByPrimaryKey(id);
		return employee;
	}

	//员工更新
	public void updateEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
	//批量删除
	public void deleteBatch(List<Integer> del_ids) {
		// TODO Auto-generated method stub
		EmployeeExample example=new EmployeeExample();
		Criteria criteria=example.createCriteria();
		//delete from xxx where emp_id in(1,2,3)
		criteria.andEmpidIn(del_ids);
		employeeMapper.deleteByExample(example);
	}

	//删除单个
	public void deleteEmp(Integer id) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(id);
		
	}
}
