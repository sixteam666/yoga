package com.project.service;

import com.project.bean.StudentBean;


public interface IStudentService {
	/**
	 * 注册
	 * @param Student StudentBean 对象
	 * @return true:注册成功，false:注册失败
	 */
	public boolean regist(StudentBean student);
	/**
	 * 登陆
	 * @param arg 邮箱或电话号
	 * @return StudentBean 对象
	 */
	public StudentBean login (String arg);
	
	
	/**
	 * 更新个人信息
	 * @param student
	 * @return true:修改成功，false:修改失败
	 */
	public boolean update (StudentBean student);
	
	/**
	 * 修改密码(即所谓的找回密码)
	 * @param pwd id
	 * @return true:修改成功，false:修改失败
	 */
	public boolean resetpassword(String pwd,String id);

	
	
	
}
