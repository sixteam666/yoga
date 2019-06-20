package com.project.service;

import com.project.bean.CoachBean;
import com.project.bean.StudentBean;


public interface IStudentService {
	
	/**
	 * 通过传入学员id查询学员
	 * @param id
	 * @return StudentBean对象
	 */
	public StudentBean findStudentbyId(String id);
	
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

	
	/**
	 * 手机换绑
	 * @param pwd id
	 * @return true:修改成功，false:修改失败
	 */
	public boolean resetphone(String phone,String id);

	
	/**
	 * 通过学员ID查询学员的教练
	 * @param id
	 * @return CoachBean
	 */
	public CoachBean findCoachbyStudentId(String id);
	
	/**
	 * 充值到钱包
	 * @param id 学员的id
	 * @param money 充值的金额
	 * @return true:充值成功，false:充值失败
	 */
	public boolean Recharge(String id ,double money);
	
	/**
	 * 消费扣款
	 * @param id 学员的id
	 * @param money 充值的金额
	 * @return true:充值成功，false:充值失败
	 */
	public boolean consume(String id ,double money);
	
	
}
