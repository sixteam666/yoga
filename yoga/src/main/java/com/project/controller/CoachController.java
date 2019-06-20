package com.project.controller;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.bean.CoachBean;
import com.project.bean.GymBean;
import com.project.bean.StudentBean;
import com.project.service.ICoachService;

@Controller
@RequestMapping("/coach")
public class CoachController {
	
	@Autowired
	private ICoachService service;
	
	/**
	 * 
	 * @param arg1 用户名或者手机号
	 * @param pwd 密码
	 * @param remenber 
	 * @return
	 */
	@RequestMapping("/login.do")
	public String login(String arg1,String pwd,Integer remenber){
		//产生一个用户（门面对象）
		Subject currentUser = SecurityUtils.getSubject();
		 if (!currentUser.isAuthenticated()) {
	            UsernamePasswordToken token = new UsernamePasswordToken("c"+arg1,pwd);
	            try {
	            	if (remenber != null && remenber == 1) {
						token.setRememberMe(true);
					}
	            	//调用login进行认证
	                currentUser.login(token);
	                System.out.println("认证成功");
	                return "redirect:/index.html";
	            } catch (UnknownAccountException uae) {
	            	System.out.println("用户名异常");
	            	return "redirect:/login.html";
	            } catch (IncorrectCredentialsException ice) {
	            	System.out.println("密码异常");
	            	return "redirect:/login.html";
	            } catch (LockedAccountException lae) {
	               System.out.println("被锁定异常");
	               return "redirect:/login.html";
	            }
	            
	      }
		return "redirect:/index.html";
	}
	@RequestMapping("/register.do")
	public String register(CoachBean coach){
		
		/**
		 * 未确定加盐值
		 */
		String id = UUID.randomUUID().toString();
		coach.setC_id(id);
		Boolean boo = service.register(coach);
		//注册成功：定向登录界面；失败：定向注册界面
		if (boo) return "redirect:/login.html";
		return "redirect:/register.html";
	}
	
	
	/**
	 * 教练信息完善，教练信息更新
	 * @author pan
	 * @param coach 信息完善或更新完成的教练实体对象
	 */
	@RequestMapping("updateInfo.do")
	public void updateCoachDetailInfo(CoachBean coach) {
		System.out.println("测试进入详情更新控制层方法>>>>>>>>>>>>>>>>>>>>>>>>");
		boolean boo = service.updateCoachDetailInfo(coach);
		//更新后的逻辑...
	}
	
	/**
	 * 显示教练详情
	 * @param id 教练id
	 */
	@RequestMapping("showCoach.do")
	public void showCoachInfoByid(Integer id) {
		System.out.println("测试进入详情展示控制层方法>>>>>>>>>>>>>>>>>>>>>>>>");
		//id从session域中获取？还是从前台传递？
		CoachBean coachInfo = service.showCoachDetailInfo(id);
	}
	/**
	 * 页面显示所有场馆
	 * @return 返回场馆集合，页面展示
	 */
	@RequestMapping("/showAllGym.do")
	@ResponseBody
	public List<GymBean> showAllGym(){
		//返回场馆集合，页面地图展示
		return service.showAllGym();
	}
	/**
	 * 页面展示所有学生
	 * @return
	 */
	@RequestMapping("/showAllStu.do")
	@ResponseBody
	public List<StudentBean> showAllStu(){
		//返回学生集合，页面地图展示
		return service.showAllStu();
	}	
}
