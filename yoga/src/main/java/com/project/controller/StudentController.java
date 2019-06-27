package com.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.bean.CoachBean;
import com.project.bean.GymBean;
import com.project.bean.LessonBean;
import com.project.bean.ShowWordsBean;
import com.project.bean.OrderBean;
import com.project.bean.StudentBean;
import com.project.bean.WordsBean;
import com.project.service.ICoachService;
import com.project.service.IGymService;
import com.project.service.IStudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private IStudentService service;
	@Autowired
	private IGymService gymService;
	@Autowired
	private ICoachService coachService;
	
	/**1111
	 * 
	 * @param arg1 用户名或者手机号
	 * @param pwd 密码
	 * @param remenber 
	 * @return
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public String login(String arg1,String pwd,HttpServletRequest request){
		//产生一个用户（门面对象）
		//暂无盐值
		//Object obj = new SimpleHash("MD5", pwd,"",1024);
		Subject currentUser = SecurityUtils.getSubject();
		 if (!currentUser.isAuthenticated()) {
	            UsernamePasswordToken token = new UsernamePasswordToken("s"+arg1,pwd);
	            try {
	            	//调用login进行认证
	            	System.out.println("!!!!!!!!!!");
	                currentUser.login(token);
	                System.out.println("认证成功");        		
	            } catch (UnknownAccountException uae) {
	            	System.out.println("用户名错误");
	            	return "用户名错误";
	            } catch (IncorrectCredentialsException ice) {
	            	System.out.println("密码错误");
	            	return "密码错误";
	            } catch (LockedAccountException lae) {
	               System.out.println("被锁定异常");
	               return "被锁定异常";
	            }
	      }
		 StudentBean student = service.findStudentbyName(arg1);
		 HttpSession session = request.getSession();
		 session.setAttribute("stu", student);
		 return "认证成功";
	}
	
	@RequestMapping("/login2.do")
	@ResponseBody
	public String phoneLogin(String arg1,String pwd,HttpServletRequest request){
		//产生一个用户（门面对象）
		//暂无盐值
		//Object obj = new SimpleHash("MD5", pwd,"",1024);
		Subject currentUser = SecurityUtils.getSubject();
		 if (!currentUser.isAuthenticated()) {
	            UsernamePasswordToken token = new UsernamePasswordToken("s"+arg1,pwd);
	            try {
	            	//调用login进行认证
	            	System.out.println("!!!!!!!!!!");
	                currentUser.login(token);
	                System.out.println("认证成功");        		
	            } catch (UnknownAccountException uae) {
	            	System.out.println("用户名错误");
	            	return "用户名错误";
	            } catch (IncorrectCredentialsException ice) {
	            	System.out.println("密码错误");
	            	return "密码错误";
	            } catch (LockedAccountException lae) {
	               System.out.println("被锁定异常");
	               return "被锁定异常";
	            }
	      }
		 StudentBean student = service.findStudentbyName(arg1);
		 HttpSession session = request.getSession();
		 session.setAttribute("stu", student);
		 return "认证成功";
	}
	
	/**
	 * 登录后跳转学生主页，显示热门场馆和教练
	 * @param string 
	 * @param model
	 * @return
	 */
	@RequestMapping("/stu.do")
	public String href(String string,Model model){
		List<GymBean> Gymlist = gymService.findHotGym();
		List<CoachBean> coachBeans = coachService.findHotCoach();
		model.addAttribute("Gymlist",Gymlist);
		model.addAttribute("Coach",coachBeans);
		return "html/student/student.html";
	}
		
	@RequestMapping("/register.do")
	@ResponseBody
		public String register(StudentBean student){
			/**
			 * 未确定加盐值
			 */
			String id = UUID.randomUUID().toString();
			student.setS_id(id);
			Object obj = new SimpleHash("MD5",student.getS_password(),id,1024);
			student.setS_password(obj.toString());
			Boolean boo = service.regist(student);
			//注册成功：定向登录界面；失败：定向注册界面
			//System.out.println(boo);
			if (boo) {
				return "yes";
			}else {
				return "no";
			}	
		}	
	
	@RequestMapping("/loginout.do")
	@ResponseBody
	public String logout(HttpSession session) {
		System.out.println("正在注销");
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		System.out.println(session.getAttribute("stu"));
		return "ok";
	}
	
			/**
			 * 显示学员个人信息
			 * @param id 学员id
			 */
			@RequestMapping("/showStudent.do")
			public String showCoachInfoByid(Model m,HttpSession session) {
				StudentBean studentBean = (StudentBean) session.getAttribute("stu");
				String id = studentBean.getS_id();
				StudentBean stu = service.findStudentbyId(id);
				/*stu.setS_name("文然");
				stu.setS_money(8838);
				stu.setS_phone("4564879");
				stu.setS_headimg("34.jpg");*/
				StudentBean stuuuu=service.findStudentbyId(stu.getS_id());
				//m.addAttribute("stu", stu);
				session.setAttribute("stu", stuuuu);
				return "html/student/my.html";
			}
			
			
			
			/**
			 * 查找所有学员
			 * 
			 * @return
			 */
			@RequestMapping("findAllStudent.do")
			@ResponseBody
			public List<StudentBean> findAllStudent() {
				List<StudentBean> list = service.findAllStudent();
				return list;
			}
			
			
			/**
			 * 更新学员信息
			 * @return
			 */
			@RequestMapping(value ="/modify.do",method = RequestMethod.GET)
			public String updateStudent(Model m,HttpServletRequest request,ModelMap map){
				String id = request.getParameter("id");
				//System.out.println(id);
				StudentBean stu = service.findStudentbyId(id);
				//System.out.println(stu);
				m.addAttribute("stu", stu);
				map.addAttribute("stuafter", stu);
				return "html/student/modify.html";
			}
			
			@PostMapping("/update.do")
			public String update(@ModelAttribute StudentBean stuafter,Model m){
						//System.out.println(stuafter);
						service.update(stuafter);
						m.addAttribute("stu", stuafter);
						return "redirect:/student/showStudent.do";
			}

			
			/**
			 * 查询课程
			 * @param session
			 * @param m
			 * @return
			 */
			@RequestMapping("/findcourse.do")
			public String findcourse(HttpSession session,ModelMap m){
				StudentBean stu=(StudentBean) session.getAttribute("stu");
				String id = stu.getS_id();
				List<LessonBean> lessonlist = service.findcourse(id);
				m.addAttribute("lesson", lessonlist);
				List<CoachBean> coachlist =service.findCoachbyStudentId(id);
				m.addAttribute("coach", coachlist);
				return "html/student/className.html";
			}
			
			
			/**
			 * 我的粉丝（暂时不用，有误）
			 *//*
			@RequestMapping("/findFans.do")
			public String myTips(HttpSession session,Model model){
				System.out.println("进来了");
				StudentBean bean = (StudentBean)session.getAttribute("stu");
				List<StudentBean> list = service.findFans(bean.getS_id());
				List<WordsBean> wordslist = service.findWords(bean.getS_id());
				List<ShowWordsBean> list3 = new ArrayList<ShowWordsBean>();
				for (WordsBean wordsBean : wordslist) {
					ShowWordsBean showWordsBean = new ShowWordsBean();
					StudentBean bean2 = service.findStudentbyId(wordsBean.getW_userid());
					showWordsBean.setHeadimg(bean2.getS_headimg());
					if (bean2.getS_nickname()!=null) {
						showWordsBean.setName(bean2.getS_nickname());
					}else {
						showWordsBean.setName(bean2.getS_name());
					}
					showWordsBean.setWord(wordsBean.getW_content());
					showWordsBean.setTime(wordsBean.getW_time());
					list3.add(showWordsBean);
				}
				
				model.addAttribute("list",list3);
				model.addAttribute("fans",list);
				return "html/student/inform.html";
			}*/
			
			/**
			 * 留言板
			 */
			@RequestMapping("/findWord.do")
			public String findWord(HttpSession session,Model model){
				System.out.println("进来了");
				StudentBean bean = (StudentBean)session.getAttribute("stu");
				List<WordsBean> wordslist = service.findWords(bean.getS_id());
				List<ShowWordsBean> list3 = new ArrayList<ShowWordsBean>();
				for (WordsBean wordsBean : wordslist) {
					ShowWordsBean showWordsBean = new ShowWordsBean();
					StudentBean bean2 = service.findStudentbyId(wordsBean.getW_userid());
					showWordsBean.setHeadimg(bean2.getS_headimg());
					if (bean2.getS_nickname()!=null) {
						showWordsBean.setName(bean2.getS_nickname());
					}else {
						showWordsBean.setName(bean2.getS_name());
					}
					showWordsBean.setWord(wordsBean.getW_content());
					showWordsBean.setTime(wordsBean.getW_time());
					list3.add(showWordsBean);
				}
				model.addAttribute("list",list3);
				return "html/student/guestbook.html";
			}
			
			/**
			 * 加关注
			 */
			@RequestMapping("/attention.do")
			public String addAttention(HttpSession session,HttpServletRequest request){
				String name = request.getParameter("name");
				StudentBean bean = (StudentBean)session.getAttribute("stu");
				System.out.println(name);
				StudentBean studentBean = service.findStudentbyName(name);
				service.addFollow(bean.getS_id(),studentBean.getS_id());
				return "redirect:/student/findFans.do";
			}
			
			/**
			 * 留言
			 */
			@RequestMapping("/insertWord.do")
			public String insertWord(HttpSession session,HttpServletRequest request){
				String content = request.getParameter("name");
				StudentBean bean = (StudentBean)session.getAttribute("stu");
				WordsBean wordsBean = new WordsBean();
				wordsBean.setW_content(content);
				wordsBean.setW_time("2019-06-27");
				wordsBean.setW_userid(bean.getS_id());
				wordsBean.setW_showid("sadasd");

				return "redirect:/student/findWord.do";				
			}
			
			
			@RequestMapping("/findorder.do")
			public String findorder(HttpSession session,ModelMap m){
				System.out.println("11111111111111");
				StudentBean stu=(StudentBean) session.getAttribute("stu");
				String id = stu.getS_id();
				List<OrderBean> orderlist = service.findorderbyid(id);
				m.addAttribute("order", orderlist);
				return "html/student/order.html";
			}
		
			@RequestMapping("/mypage.do")
			public String attention(){
				
				
				return "html/student/showInformation.html";
			}
}
