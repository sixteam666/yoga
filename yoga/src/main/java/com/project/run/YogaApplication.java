package com.project.run;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.project.controller")
@ComponentScan("com.project.config")
@ComponentScan("com.project.service")
@MapperScan("com.project.dao")
@SpringBootApplication
public class YogaApplication {

	public static void main(String[] args) {
		SpringApplication.run(YogaApplication.class, args);
	}

}
