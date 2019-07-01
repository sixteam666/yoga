package com.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.bean.BankCardBean;
import com.project.bean.CoachBean;
import com.project.bean.GymBean;
import com.project.bean.LessonBean;
import com.project.bean.PictureBean;
import com.project.bean.StudentBean;
import com.project.bean.WordsBean;
import com.project.service.IBankCardService;
import com.project.service.ICoachService;
import com.project.service.IGymService;
import com.project.service.IStudentService;
import com.project.util.FileUtil;
import com.project.util.UploadPathConstant;

@Controller
@RequestMapping("/coach")
public class CoachController {
	
	@Autowired
	private ICoachService service;
	@Autowired
	private IBankCardService bankCardService;
	@Autowired
	private IGymService gs;
	@Autowired
	private IStudentService ss;
	
	/**
	 * 
	 * @param arg1 用户名或者手机号
	 * @param pwd 密码
	 * @param remenber 
	 * @return
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public String login(@Validated String c_name,@Validated String c_password,Integer remenber ){
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
	                Session session = currentUser.getSession(true);
	                
	                CoachBean coach = service.login(c_name);
	                session.setAttribute("coach",coach);
	                
	                session.setAttribute("id", coach.getC_id());
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
	@RequestMapping("/getUser.do")
	@ResponseBody
	public CoachBean getUser(){
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession(true);
		System.out.println(session);
		if (session == null)return null;
		Object obj = session.getAttribute("coach");
		System.out.println(obj);
		if (obj == null) return null;
		CoachBean coach = (CoachBean)obj;
		
		return coach;
	} 
	
	@RequestMapping("/register.do")
	@ResponseBody
	public String register(@Validated CoachBean coach){
		String id = UUID.randomUUID().toString();
		/**
		 * 暂未加盐
		 */
		Object obj = new SimpleHash("MD5",coach.getC_password(),id,1024);
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
		service.updateCoachDetailInfo(coach);
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
	public String showCoachInfoByid(ModelMap map) {
		String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		CoachBean coachInfo = service.getCoachById(id);
		map.put("coachInfo", coachInfo);
		return "html/coach/coachDetail.html";
	}
	
	/**
	 * 地图展示所有学生
	 * @return
	 */
	@RequestMapping("/showAllStu.do")
	public String showAllStu(ModelMap map){
		
		return "html/coach/showStudent.html";
	}
	/**
	 * 查询所有学生
	 * @return
	 */
	@RequestMapping("/findAllStu.do")
	@ResponseBody
	public List<StudentBean> findAllStu(){
		return service.showAllStu();
	}
	/**
	 * 页面显示所有场馆
	 * @return 返回场馆集合，页面展示
	 */
	@RequestMapping("/showAllGym.do")
	public String showAllGym(ModelMap map){
		List<GymBean> list = service.showAllGym();
		map.put("gym", list);
		return "/html/coach/showGym.html";
	}
	/**
	 * 跳转到首页
	 * @return 返回场馆集合
	 */
	@RequestMapping("/showCoachHome.do")
	public String showCoachHome(ModelMap map){
		List<GymBean> list = service.showHotGym();
		map.put("gym", list);
		List<CoachBean> list2 = service.showHotCoach();
		map.put("coachList", list2);
		return "/html/coach/coach.html";
	}
	
	/**
	 * 教练申请签约场馆
	 * @param r_reqid 教练id
	 * @param r_resid 场馆id
	 * @return 
	 */
	@RequestMapping("/signGym.do")
	@ResponseBody
	public String signGym(String r_resid,int r_state){
		String str = "";
		Object obj = getUser();
		if (obj != null) {
			CoachBean coach = (CoachBean)obj;
			str = service.addRequest(coach.getC_id(), r_resid,r_state);
		}
		return str;
	}
	/**
	 * 处理学员请求
	 * @param r_resid
	 * @param r_state
	 * @return
	 */
	@RequestMapping("/dealStuRequest.do")
	@ResponseBody
	public String dealStuRequest(String r_reqid,int r_state){
		String str = "";
		Boolean boo = false;
		Object obj = getUser();
		if (obj != null) {
			CoachBean coach = (CoachBean)obj;
			boo = service.updateRequest(r_reqid,coach.getC_id(),r_state);
		}
		//同意
		if (r_state==1) {
			if (boo)return "accept";
			return "network_error";
		}else if(r_state==2){
			if (boo) return "noaccept";
			return "network_error";
		}
		return str;
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
	@RequestMapping("withdraw.do")
	@ResponseBody
	public boolean withdraw(double money, Integer cardId) {
		String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		Boolean res = service.updateMoney(id, money, cardId);
		return res;
	}
	
	/**
	 * 显示我的学员
	 * @author pan
	 * @param id 教练id
	 */
	@RequestMapping("myStudent.do")
	public String showMyStudent(ModelMap map) {
		String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		List<StudentBean> stuList = service.listMyStudent(id);
		System.out.println(stuList);
		map.put("myStuList", stuList);
		return "html/coach/myStudent.html";
	}
	
	/**
	 * 显示教练基本信息
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("personalInfo.do")
	public String showPersonalInfo(ModelMap map) {
		String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
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
		String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		if(id == null) {
			throw new RuntimeException("教练个人信息更改时教练还未登录");
		}
		String headimg = service.getCoachById(id).getC_headimg();
		if(!"".equals(file.getOriginalFilename())){
			headimg =FileUtil.getFileName(file, req, UploadPathConstant.HEADIMG);
		}
		coach.setC_headimg(headimg);
		coach.setC_id(id);
		service.updatePersonalInfo(coach);
		//重定向到个人信息显示页面
		return "redirect:/coach/showCoach.do";
	}
	
	/**
	 * 教练认证
	 * @author pan
	 * @param coach
	 */
	@RequestMapping("authentication.do")
	@ResponseBody
	public void coachAuthentication(CoachBean coach) {
		String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		coach.setC_id(id);
		service.updateAuthentication(coach);
	}
	
	/**
	 * 显示教练课程信息
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("lessonInfo.do")
	public String showLessonInfo(ModelMap map) {
		String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
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
		String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		coach.setC_id(id);
		service.updateLessonInfo(coach);
		//重定向到个人信息显示页面
		return "redirect:/coach/showCoach.do";
	}
	
	/**
	 * 查看钱包余额
	 * @author pan
	 * @param coach 要更新的数据
	 * @return 返回个人信息显示页面
	 */
	@RequestMapping("showMoney.do")
	public String updateLessonInfo(ModelMap map) {
		String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		Double money = service.getMoney(id);
		List<BankCardBean> cardList = bankCardService.listBankCard(id);
		map.put("cardList", cardList);
		map.put("money", money);
		return "/html/coach/money.html";
	} 
	/**
	 * 显示某个场馆信息
	 * @param map
	 * @param gymId
	 * @return
	 */
	@RequestMapping("/showGymDetail.do")
	public String showGymDetail(ModelMap map,String gymId){
		GymBean gb = gs.findGymById(gymId);
		map.put("gymBean", gb);
		return "/html/coach/gymIndex.html";
	} 
	
	/**
	 * 向他人展示我的资料
	 * @param coachId
	 * @return
	 */
	@RequestMapping("showToOther.do")
	public String showToOtherUser(String coachId,ModelMap map) {
		
		 String currentUserId = "";
		 Integer type = null;
		 Session session = SecurityUtils.getSubject().getSession(false);
		 StudentBean stu = (StudentBean) session.getAttribute("student");
		 if(stu == null) {
			 CoachBean coach = (CoachBean) session.getAttribute("coach");
			 if(coach == null) {
				 GymBean gym = (GymBean) session.getAttribute("gym");
				 if(gym == null) {
					 return null;
				 } else {
					 currentUserId = gym.getG_id();
					 type = 2;
				 }
			 } else {
				 currentUserId = coach.getC_id();
				 type = 1;
			 }
		 } else {
			 currentUserId = stu.getS_id();
			 type = 0;
		 }
		 CoachBean c = service.showToOtherUser(currentUserId,coachId,type);
		 map.put("coach", c);
		 map.put("type", type);
		 
		 return "html/coach/coachIndex.html";
	}
	
	
	@RequestMapping("/showOneGym.do")
	public String showOneGym(ModelMap map,String gymId){
		GymBean gb = gs.findGymById(gymId);
		map.put("gymBean", gb);
		return "/html/coach/msgShow.html";
	}
	/**
	 * 显示某个学生详细信息
	 * @param map
	 * @param stuId
	 * @return
	 */
	@RequestMapping("/showStuDetail.do")
	public String showStuDetail(ModelMap map,String stuId){
		StudentBean sb = ss.findStudentbyId(stuId);
		map.put("sb", sb);
		return "/html/coach/showStudentDetail.html";
	}
	/**
	 * 教练给学员留言
	 * @param stuId
	 * @param message
	 * @return
	 */
	@RequestMapping("/sendMessage.do")
	@ResponseBody
	public String sendMessage(String stuId,String message){
		String re = "false";
		WordsBean words = new WordsBean();
		Object obj = getUser();
		if (obj != null) {
			CoachBean coach = (CoachBean)obj;
			words.setW_content(message);
			words.setW_userid(coach.getC_id());
			//获取时间
			SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sp.format(new Date());
			words.setW_time(date);
			words.setW_showid(stuId);
			re = service.sendMessage(words);
		}
		return re;
	}
	/**
	 * 注销
	 * @return
	 */
	@RequestMapping("/loginOut.do")
	public String loginOut(){
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "redirect:/html/index.html";
	}
	
	/**
	 * 显示我的签约
	 * @return
	 */
	@RequestMapping("findSign.do")
	public String findSign(ModelMap map) {
		CoachBean coach = (CoachBean) SecurityUtils.getSubject().getSession().getAttribute("coach");
		GymBean gym = gs.findGymById(coach.getC_g_id());
		List<PictureBean> pictureList = gs.findAllPic(coach.getC_g_id(), 1);
		List<LessonBean> lessonList = service.listLessons(coach.getC_id());
		map.put("gym", gym);
		System.out.println(gym);
		map.put("lessonList", lessonList);
		map.put("pictureList", pictureList);
		return "html/coach/mySign.html";
	}
	
	/**
	 * 显示我的通知
	 * @return
	 */
	@RequestMapping("myAdvise.do")
	public String myAdivse(ModelMap map) {	
		String resid = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		//场馆的通知
		List<GymBean> list = service.findMyAdvise(resid);
		map.put("gymList", list);
		//学员的通知
		List<StudentBean> list2 = service.findMyAdviseStu(resid);
		map.put("stuList", list2);
		return "/html/coach/myAdvise.html";
	}
	/**
	 * 显示图片
	 * 
	 * @param lists
	 */
	@RequestMapping("/showPictures.do")
	@ResponseBody
	public List<PictureBean> showPictures(int p_type,String gymId) {
		
		List<PictureBean> list = gs.findAllPic(gymId, p_type);
		
		return list;
	}
	@RequestMapping("/showLesson.do")
	//@ResponseBody
	public String showLesson(LessonBean lessonBean,ModelMap map){ 
		List<LessonBean> list = gs.findLesson(lessonBean);
		if (list.isEmpty()) {
			list.add(lessonBean);
		}
		map.put("list", list);
		return "/html/coach/addLesson.html";
	}
	
	/**
	 * 教练申请签约场馆
	 * @param r_reqid 教练id
	 * @param r_resid 场馆id
	 * @return 
	 */
	@RequestMapping("/lessonDone.do")
	public void lessonDone(String stuId){
		int r_state = 5;
		Object obj = getUser();
		if (obj != null) {
			CoachBean coach = (CoachBean)obj;
			service.updateRequest(stuId,coach.getC_id(),r_state);
		}
	}
	
}
