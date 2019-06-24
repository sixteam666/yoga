package com.project.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
		 * 暂未加盐加密
		 */
		String id = UUID.randomUUID().toString();
		coach.setC_id(id);
		Boolean boo = service.register(coach);
		//注册成功：定向登录界面；失败：定向注册界面
		if (boo) return "redirect:/html/coach/coachLogin.html";
		return "forward:/html/coach/coachReg.html";
	}
	
	
	/**
	 * 教练信息完善，教练信息更新
	 * @author pan
	 * @param coach 信息完善或更新完成的教练实体对象
	 */
	@RequestMapping("updateInfo.do")
	public String updateCoachDetailInfo(CoachBean coach) {
		System.out.println("测试进入详情更新控制层方法>>>>>>>>>>>>>>>>>>>>>>>>");
		boolean boo = service.updateCoachDetailInfo(coach);
		//更新后的逻辑...
		//更新后转发至进行查询业务
		return "redirect:/coach/showCoach.do?id="+coach.getC_id();
	}
	
	/**
	 * 显示教练详情
	 * @author pan
	 * @param id 教练id
	 */
	@RequestMapping("showCoach.do")
	public String showCoachInfoByid(String id, ModelMap map) {
		System.out.println("测试进入详情展示控制层方法>>>>>>>>>>>>>>>>>>>>>>>>");
		//id从session域中获取？还是从前台传递？
		CoachBean coachInfo = service.getCoachDetailInfo(id);
		map.put("coachInfo", coachInfo);
		return "html/coach/my.html";
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
	 * 教练申请签约场馆
	 * @param r_reqid 教练id
	 * @param r_resid 场馆id
	 * @return 
	 */
	@RequestMapping("/signGym.do")
	public Boolean signGym(String r_reqid,String r_resid){
		Boolean boo = service.addRequest(r_reqid, r_resid);
		return boo;
	}
	/**
	 * 处理申请
	 * @param r_reqid 学员或者场馆id
	 * @param r_resid 教练id
	 * @param r_state 同意：1；拒绝：2
	 * @return 
	 */
	@RequestMapping("/handleRequest.do")
	public Boolean handleRequest(String r_reqid,String r_resid,int r_state){
		Boolean boo = service.updateRequest(r_reqid, r_resid, r_state);
		return boo;
	}
	
	/**
	 * 更改密码
	 * @author pan
	 * @param id 教练id
	 * @param newPassword 新密码
	 */
	@RequestMapping("changePwd.do")
	public void changePassword(String id, String newPassword) {
		service.updatePassword(id, newPassword);
	}
	
	/**
	 * 提现
	 * @author pan
	 * @param id 教练id
	 * @param money 提现金额
	 */
	public void withdraw(String id, double money) {
		service.updateMoney(id, money);
	}
	
	/**
	 * 显示我的学员
	 * @author pan
	 * @param id 教练id
	 */
	public String showMyStudent(String id, ModelMap map) {
		List<StudentBean> stuList = service.listMyStudent(id);
		map.put("stuList", stuList);
		return "/html/coach/showStudent.html";
	}
}
