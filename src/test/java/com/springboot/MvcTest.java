package com.springboot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MvcTest {
	//传入springmvc的ioc
	@Autowired
	WebApplicationContext context;

	//虚拟mvc请求来获取处理结果
	MockMvc mvc;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Before
	public void initMvc() {
		mvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testDept() throws Exception {
		//模拟器请求拿到返回值
		MvcResult result=mvc.perform(MockMvcRequestBuilders.get("/dept/1")).andReturn();
		//请求成功后，获取返回数据并验证
		MockHttpServletRequest request=result.getRequest();
		logger.info("返回的数据为");
	}

}
