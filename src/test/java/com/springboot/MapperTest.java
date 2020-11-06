package com.springboot;

import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.bean.Department;
import com.springboot.bean.Employee;
import com.springboot.mybatis.dao.DepartmentMapper;
import com.springboot.mybatis.dao.EmployeeMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private DepartmentMapper departmentMapper;


	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@Test
	public void testMapper() {
		//测试mapper方法
		System.out.println(employeeMapper);
		System.out.println(departmentMapper);
		for (int i = 0; i < 1000; i++) {
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			String temp = uuid.substring(0, 8);
			Employee e = new Employee();
			e.setEmpname(temp);
			e.setSex(Math.random() > 0.5 ? 0 : 1);
			e.setDate(new Date());
			e.setPhone("123456");
			e.setDid(Math.random() > 0.5 ? 1 : 2);
			employeeMapper.insertSelective(e);
		}
	}

}
