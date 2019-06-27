package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.service.ISmsService;

/**
 * 短信验证控制器类
 * 
 * @author YuChen
 *
 */
@Controller
@RequestMapping("/sms")
public class SmsController {
	@Autowired
	private ISmsService smsService;

	@RequestMapping("/postSms.do")
	public int postSms(String g_phone) {
		// 需要发送短信的手机号码(一个或多个)
		String[] phoneNumbers = { g_phone };

		return 0;
	}
}
