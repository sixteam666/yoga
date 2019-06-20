package com.project.service.impl;

import org.springframework.stereotype.Service;

import com.project.bean.CoachBean;
import com.project.bean.StudentBean;
import com.project.dao.IStudentDao;
import com.project.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService{

	private IStudentDao dao;
	
	@Override
	public boolean regist(StudentBean student) {
		if (dao.findStudentbyName(student.getS_name()) != null) {
			return false;
		}
		int result = dao.addStudent(student);
		if (result != 1) {
			return false;
		} else
			return true;
	};

	@Override
	public StudentBean login(String arg) {
		StudentBean student = null;
		Object obj = dao.findStudentbyName(arg);
		if (obj!=null) {
			student = (StudentBean) obj;
		}
		return student;
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

	@Override
	public StudentBean findStudentbyId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CoachBean findCoachbyId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
