package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.IGymDao;
import com.project.service.ISmsService;

/**
 * 短信业务实现类
 * 
 * @author YuChen
 *
 */
@Service
public class SmsServiceImpl implements ISmsService {
	@Autowired
	private IGymDao gymDao;

	// 短信应用SDK AppID
	private int appid = 1400222996; // 1400开头
	// 短信应用SDK AppKey
	private String appkey = "04248fb812dfba053d95d7c755a4bb46";

	// 签名
	// NOTE: 这里的签名"腾讯云"只是一个示例，
	// 真实的签名需要在短信控制台中申请，另外
	// 签名参数使用的是`签名内容`，而不是`签名ID`
	private String smsSign = "腾讯云";

	// 短信模板ID，需要在短信应用中申请
	// NOTE: 这里的模板ID`7839`只是一个示例，
	// 真实的模板ID需要在短信控制台中申请
	private int templateId = 5623;

	@Override
	public String smsPost(String phone) {
		// 需要发送短信的手机号码(一个或多个)
		String[] phoneNumbers = { phone };

		return null;
	}

}
