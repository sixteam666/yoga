package com.project.service;

import java.util.List;

import com.project.bean.CoachBean;
import com.project.bean.StudentBean;

public interface ICoachService {
	/**
	 * 注册
	 * @param coach CoachBean 对象
	 * @return true:注册成功，false:注册失败
	 */
	public Boolean register(CoachBean coach);
	/**
	 * 教练登录
	 * @param name 用户名
	 * @return 教练对象
	 */
	public CoachBean login(String name);
	/**
	 * 教练签约或解约场馆
	 * @param g_id 场馆id或0
	 * @return 影响行数
	 */
	public int signGym(int g_id);
	/**
	 * 
	 * @param c_id
	 * @return
	 */
	public List<StudentBean> findStu(String c_id);
}
