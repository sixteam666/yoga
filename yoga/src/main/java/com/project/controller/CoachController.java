package com.project.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.bean.CoachBean;
import com.project.bean.GymBean;
import com.project.bean.StudentBean;
import com.project.service.ICoachService;
import com.project.util.FileUtil;

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
	@ResponseBody
	public String login(String c_name,String c_password,Integer remenber){
		//产生一个用户（门面对象）
		Subject currentUser = SecurityUtils.getSubject();
		 if (!currentUser.isAuthenticated()) {
	            UsernamePasswordToken token = new UsernamePasswordToken("c"+c_name,c_password);
	            try {
	            	if (remenber != null && remenber == 1) {
						token.setRememberMe(true);
					}
	            	//调用login进行认证
	                currentUser.login(token);
	                System.out.println("认证成功");
	                return "success";
	            } catch (UnknownAccountException uae) {
	            	System.out.println("用户名异常");
	            	return "name_false";
	            } catch (IncorrectCredentialsException ice) {
	            	System.out.println("密码异常");
	            	return "password_false";
	            } catch (LockedAccountException lae) {
	               System.out.println("被锁定异常");
	               return "user_lock";
	            }
	      }
		return "success";
	}
	@RequestMapping("/register.do")
	@ResponseBody
	public String register(CoachBean coach){
		String id = UUID.randomUUID().toString();
		/**
		 * 暂未加盐
		 */
		Object obj = new SimpleHash("MD5",coach.getC_password(),"",1024);
		coach.setC_password(obj.toString());
		
		coach.setC_id(id);
		Boolean boo = service.register(coach);
		//注册成功：定向登录界面；失败：定向注册界面
		if (boo) return "success";
		return "false";
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
		//id从session域中获取？还是从前台传递？
		CoachBean coachInfo = service.getCoachDetailInfo(id);
		System.out.println(coachInfo);
		map.put("coachInfo", coachInfo);
		return "html/coach/my-pan.html";
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
	@RequestMapping("showMyStudent.do")
	public String showMyStudent(String id, ModelMap map) {
		List<StudentBean> stuList = service.listMyStudent(id);
		map.put("stuList", stuList);
		return "/html/coach/showStudent.html";
	}
	
	/**
	 * 显示教练基本信息
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("personalInfo.do")
	public String showPersonalInfo(String id, ModelMap map) {
		CoachBean personalInfo = service.getPersonalInfo(id);
		map.put("personalInfo", personalInfo);
		return "/html/coach/personalInfo.html";
	}
	
	/**
	 * 更新教练个人详细信息
	 * @author pan
	 * @param coach 要更新的数据
	 * @return 返回个人信息显示页面
	 */
	@RequestMapping("updatePersonalInfo.do")
	public String updatePersonalInfo(CoachBean coach, MultipartFile file, HttpServletRequest req) {
		//这里还有点问题，如果用户未重新上传文件情况未处理
		String headimg = "";//session中取出
		if(file.getOriginalFilename() != ""){
			headimg =FileUtil.getFileName(file, req);
		}
		coach.setC_headimg(headimg);
		coach.setC_id("1");
		System.out.println(coach);
		service.updatePersonalInfo(coach);
		//重定向到个人信息显示页面
		return "redirect:/coach/showCoach.do?id="+coach.getC_id();
	}
	
	/**
	 * 教练认证
	 * @author pan
	 * @param coach
	 */
	@RequestMapping("authentication.do")
	@ResponseBody
	public String coachAuthentication(CoachBean coach) {
		coach.setC_id("1");
		service.updateAuthentication(coach);
		return coach.getC_id();
	}
	
	/**
	 * 显示教练课程信息
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("lessonInfo.do")
	public String showLessonInfo(String id, ModelMap map) {
		CoachBean lessonInfo = service.getLessonInfo(id);
		map.put("lessonInfo", lessonInfo);
		return "/html/coach/lessonInfo.html";
	}
	
	/**
	 * 更新教练课程信息
	 * @author pan
	 * @param coach 要更新的数据
	 * @return 返回个人信息显示页面
	 */
	@RequestMapping("updateLessonInfo.do")
	public String updateLessonInfo(CoachBean coach) {
		coach.setC_id("1");
		System.out.println(coach);
		service.updateLessonInfo(coach);
		//重定向到个人信息显示页面
		return "redirect:/coach/showCoach.do?id="+coach.getC_id();
	}
}
