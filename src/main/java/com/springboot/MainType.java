package com.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.springboot.mybatis.dao")
@SpringBootApplication
public class MainType {
	public static void main(String[] args) {
		SpringApplication.run(MainType.class, args);
	}
}