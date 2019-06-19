package com.project.service;

import com.project.bean.GymBean;

/**
 * 场馆业务层接口
 * 
 * @author YuChen
 *
 */
public interface IGymService {
	/**
	 * 注册
	 * 
	 * @param gym 场馆对象
	 * @return int 影响行数
	 */
	int register(GymBean gym);

	/**
	 * 会馆登录
	 * 
	 * @param arg 邮箱或电话号
	 * @return 会馆对象
	 */
	GymBean login(String arg);

	/**
	 * 修改密码
	 * 
	 * @param id
	 * @param pwd
	 * @return 影响行数
	 */
	int updatePassword(int id, String pwd);

}
