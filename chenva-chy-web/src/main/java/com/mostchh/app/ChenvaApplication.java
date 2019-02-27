package com.mostchh.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.dao")
@ComponentScan({"com.controller", "com.service"})
public class ChenvaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChenvaApplication.class, args);
	}

}

