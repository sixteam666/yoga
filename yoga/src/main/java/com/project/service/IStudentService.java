package com.project.service;

import com.project.bean.StudentBean;

public interface IStudentService {
	
	public boolean regist(StudentBean student);
	public boolean login (StudentBean student);
}
