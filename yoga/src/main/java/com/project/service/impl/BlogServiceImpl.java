package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.DynamicBean;
import com.project.dao.IBlogDao;
import com.project.service.IBolgService;

@Service
public class BlogServiceImpl implements IBolgService {

	@Autowired
	IBlogDao blogDao;
	
	@Override
	public List<DynamicBean> listAllBlog() {
		return blogDao.listAllBlog();
	}

}
