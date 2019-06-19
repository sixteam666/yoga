package com.project.service.impl;

import org.springframework.stereotype.Service;

import com.project.bean.StudentBean;
import com.project.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService{

	@Override
	public boolean regist(StudentBean student) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public StudentBean login(String arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(StudentBean student) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resetpassword(String pwd, String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
