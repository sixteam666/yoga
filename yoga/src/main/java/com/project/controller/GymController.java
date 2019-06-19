package com.project.controller;

import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.bean.GymBean;
import com.project.service.IGymService;

/**
 * 会馆控制层类
 * 
 * @author YuChen
 *
 */
@Controller
@RequestMapping("/gym")
public class GymController {
	@Autowired
	private IGymService service;

	/**
	 * 登录
	 * 
	 * @param arg
	 * @param pwd
	 * @return
	 */
	@RequestMapping("/login.do")
	public String login(String arg, String pwd) {
		// 产生一个用户（门面对象）
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken("g" + arg, pwd);
			try {
				// 调用login进行认证
				currentUser.login(token);
				System.out.println("认证成功");
				return "redirect:/html/main.html";
			}
			// 父异常。认证失败异常
			catch (AuthenticationException ae) {
				// unexpected condition? error?
				System.out.println("异常不详：自己解决");
				return "redirect:/html/login.html";
			}
		}
		return "redirect:/html/main.html";
	}

	/**
	 * 注册
	 * 
	 * @param gym
	 * @return
	 */
	@RequestMapping("/reg.do")
	public int register(GymBean gym) {
		gym.setG_id(UUID.randomUUID().toString());
		
		// 盐值暂时无法确定
		// Object obj = new SimpleHash("MD5", gym.getG_password(), gym.getG_email(),1024);
		// gym.setG_password(obj.toString());

		int result = service.register(gym);
		return result;
	}
}
