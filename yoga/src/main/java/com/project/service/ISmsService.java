package com.project.service;

/**
 * 短信业务层接口类
 * 
 * @author YuChen
 *
 */
public interface ISmsService {
	/**
	 * 发送短信
	 * @param phone
	 * @return
	 */
	String smsPost(String phone);

}
