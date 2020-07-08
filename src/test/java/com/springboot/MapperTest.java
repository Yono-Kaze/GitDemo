package com.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

		System.out.println(employeeMapper.selectByPrimaryKeyWithDept(13));

	}

}
