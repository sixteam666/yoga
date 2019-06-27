package com.project.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.run.BaseTest;
import com.project.service.impl.BlogServiceImpl;

public class BlogServiceImplTest extends BaseTest {
	
	@Autowired
	private BlogServiceImpl blogService;
	
	@Test
	public void testListAllBlog() {
		System.out.println(blogService.listAllDynamics());
	}
}
