package com.project.service;

import com.project.bean.CoachBean;

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
	
}
