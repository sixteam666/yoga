package com.project.controller;

import java.util.ArrayList;
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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;

import com.project.bean.CoachBean;
import com.project.bean.DynamicBean;
import com.project.bean.GymBean;
import com.project.bean.LessonBean;
import com.project.bean.OrderBean;
import com.project.bean.POrderBean;
import com.project.bean.RequestBean;
import com.project.bean.ShowWordsBean;
import com.project.bean.StudentBean;
import com.project.bean.WordsBean;
import com.project.service.IBlogService;
import com.project.service.ICoachService;
import com.project.service.IGymService;
import com.project.service.IStudentService;
import com.project.util.DateUtil;
import com.project.util.FileUtil;
import com.project.util.UploadPathConstant;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private IStudentService service;
	@Autowired
	private IGymService gymService;
	@Autowired
	private ICoachService coachService;
	@Autowired
	private IBlogService IBlogService;
	
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
		 Session session = currentUser.getSession(true);
		 session.setAttribute("stu", student);
		 session.setAttribute("id", student.getS_id());
		 return "认证成功";
	}
	
	@RequestMapping("/login2.do")
	@ResponseBody
	public String phoneLogin(String arg1,String pwd){
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
		 Session session = currentUser.getSession(true);
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
	
	@RequestMapping("/getUser.do")
	@ResponseBody
	public StudentBean getUser(){
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession(true);
		if (session == null)return null;
		Object obj = session.getAttribute("stu");
		if (obj == null) return null;
		StudentBean studentBean = (StudentBean)obj;
		
		return studentBean;
	} 
		
	@RequestMapping("/register.do")
	@ResponseBody
		public String register(String username,String password){
			/**
			 * 未确定加盐值
			 */
			StudentBean student = new StudentBean();
			student.setS_name(username);
			student.setS_password(password);
			String id = UUID.randomUUID().toString();
			student.setS_id(id);
			Object obj = new SimpleHash("MD5",student.getS_password(),id,1024);
			student.setS_password(obj.toString());
			Boolean boo = service.regist(student);
			if (boo) {
				return "success";
			}else {
				return "false";
			}	
		}	
	
	@RequestMapping("/logout.do")
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "redirect:/html/index.html";
	}
	
			/**
			 * 显示学员个人信息
			 * @param id 学员id
			 */
			@RequestMapping("/showStudent.do")

			public String showCoachInfoByid(Model m) {
				Session session = SecurityUtils.getSubject().getSession();
				StudentBean studentBean = (StudentBean) session.getAttribute("stu");
				String id = studentBean.getS_id();
				StudentBean stu = service.findStudentbyId(id);
				
				/*StudentBean stu = new StudentBean();
				stu.setS_id("s001");*/
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
				StudentBean stu = service.findStudentbyId(id);
				m.addAttribute("stu", stu);
				map.addAttribute("stuafter", stu);
				return "html/student/modify.html";
			}
			
			@PostMapping("/update.do")
			public String update(@ModelAttribute StudentBean stuafter,Model m,MultipartFile file,HttpServletRequest req){
				Session session = SecurityUtils.getSubject().getSession();
				StudentBean stu=(StudentBean) session.getAttribute("stu");
					if(stu.getS_id() == null) {
						throw new RuntimeException("学员个人信息更改时教练还未登录");
					}
					String headimg = service.findStudentbyId(stu.getS_id()).getS_headimg();
					if(!"".equals(file.getOriginalFilename())){
						headimg = FileUtil.getFileName(file, req, UploadPathConstant.HEADIMG);
					}
					stuafter.setS_headimg(headimg);
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
			public String findcourse( ModelMap m){
				Session session = SecurityUtils.getSubject().getSession();
				StudentBean stu=(StudentBean) session.getAttribute("stu");
				String id = stu.getS_id();
				List<LessonBean> lessonlist = service.findcourse(id);
				m.addAttribute("lesson", lessonlist);
				List<CoachBean> coachlist = new ArrayList<CoachBean>();
				List<String> coachidlist =service.findCoachidbyStudentId(id);
					for (String string : coachidlist) {
						 CoachBean coach = coachService.getCoachById(string);
						 		coachlist.add(coach);
					}
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
			public String findWord(Model model){
				Session session = SecurityUtils.getSubject().getSession();
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
			 * ta的留言板
			 */
			@RequestMapping("/findWord2.do")
			public String findWord2(Model model,String userid){
				List<WordsBean> wordslist = service.findWords(userid);
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
					System.out.println(showWordsBean);
					list3.add(showWordsBean);
				}
				model.addAttribute("list",list3);
				return "html/student/guestbook2.html";
			}
			
			/**
			 * 加关注
			 */
			@RequestMapping("/attention.do")
			@ResponseBody
			public String addAttention(String idolid){
				Session session = SecurityUtils.getSubject().getSession();
				StudentBean stu = (StudentBean)session.getAttribute("stu");
				String myid = stu.getS_id();
				service.addFollow(myid,idolid);
				return "ok";
			}
			
			/**
			 * 自己留言
			 */
			@RequestMapping("/insertWord.do")
			public String insertWord(HttpServletRequest request){
				Session session = SecurityUtils.getSubject().getSession();
				String content = request.getParameter("message");
				StudentBean bean = (StudentBean)session.getAttribute("stu");
				WordsBean wordsBean = new WordsBean();
				wordsBean.setW_content(content);
				wordsBean.setW_time("2019-06-27");
				wordsBean.setW_userid(bean.getS_id());
				wordsBean.setW_showid(bean.getS_id());
				service.insertWords(wordsBean);

				return "redirect:/student/findWord.do";				
			}
			
			/**
			 * 留言
			 */
			@RequestMapping("/insertWord2.do")
			public String insertWord2(HttpServletRequest request){
				String date2String = DateUtil.Date2String(new java.util.Date(), "yy-MM-dd HH:mm:ss");
				Session session = SecurityUtils.getSubject().getSession();
				StudentBean bean = (StudentBean)session.getAttribute("stu");
				String content = request.getParameter("message");
				String id = request.getParameter("showid");
				WordsBean wordsBean = new WordsBean();
				wordsBean.setW_content(content);
				wordsBean.setW_userid(bean.getS_id());
				wordsBean.setW_showid(id);
				wordsBean.setW_time(date2String);
				service.insertWords(wordsBean);
				service.addRequeststu(bean.getS_id(), id, "新增留言", date2String);

				return "redirect:/student/findWord2.do?userid="+id;				
			}
			
			
			
			/**
			 * 公共课支付订单
			 * @return
			 */
			@RequestMapping("/pay.do")
			public String pay(ModelMap m,int orderid){
				OrderBean order = service.findorderbyid(orderid);
				m.addAttribute("order",order);
				return "/html/student/pay.html";
			}
			
			
			/**
			 * 私教课支付订单
			 * @return
			 */
			@RequestMapping("/payp.do")
			public String payp(ModelMap m,int porderid){
				POrderBean porder = service.findPorderbyid(porderid);
				m.addAttribute("porder",porder);
				return "/html/student/pay2.html";
			}
			
			/**
			 * 查询我的订单
			 * @param m
			 * @return
			 */
			@RequestMapping("/findorder.do")
			public String findorder(ModelMap m){
				Session session = SecurityUtils.getSubject().getSession();
				StudentBean stu=(StudentBean) session.getAttribute("stu");
				String id = stu.getS_id();
				List<OrderBean> orderlist = service.listorderbystuid(id);
				m.addAttribute("order", orderlist);
				List<POrderBean> listpo = service.listporder(id);
				m.addAttribute("porder", listpo);
				return "html/student/order.html";
			}
		
			/**
			 * 跳转个人主页
			 * @param m
			 * @return
			 */
			@RequestMapping("/mypage.do")
			public String mypage(ModelMap m){
				Session session = SecurityUtils.getSubject().getSession();
				StudentBean stu=(StudentBean) session.getAttribute("stu");
				String id = stu.getS_id();
				int fansnumber = service.countmyfans(id);
				int idolnumber = service.countmyattention(id);
				m.addAttribute("fansnumber", fansnumber);
				m.addAttribute("idolnumber", idolnumber);
				List<DynamicBean> dynamiclist = IBlogService.listDynamicsById(id);
				m.addAttribute("dynamiclist",dynamiclist);
				
				return "html/student/mypage.html";
			}
			
			/**
			 * 跳转他的个人主页
			 */
			@RequestMapping("/hispage.do")
			public String hispage(String id,Model m){
				int fansnumber = service.countmyfans(id);
				int idolnumber = service.countmyattention(id);
				m.addAttribute("fansnumber", fansnumber);
				m.addAttribute("idolnumber", idolnumber);
				List<DynamicBean> dynamiclist = IBlogService.listDynamicsById(id);
				m.addAttribute("dynamiclist",dynamiclist);
				StudentBean studentBean = service.findStudentbyId(id);
				if (studentBean == null) {
					CoachBean coachBean = coachService.getCoachById(id);
					m.addAttribute("user",coachBean);
					return "redirect:/dynamic/showOther.do?id="+id;
				}
				m.addAttribute("user",studentBean);
				return "html/student/hispage.html";
			}
			
			/**
			 * 跳转充值页面
			 * @param m
			 * @return
			 */
			@RequestMapping("/recharge.do")
			public String recharge(ModelMap m){
				return "html/student/pay.html";
			}
			
			/**
			 * 跳转支付宝支付页面
			 * @param session
			 * @param m
			 * @return
			 */
			
			@RequestMapping("/alipay.do")
			//@ResponseBody
			public String alipay(HttpServletRequest request){
				String money1 = request.getParameter("money");
				Double money = Double.parseDouble(money1);
				Session session = SecurityUtils.getSubject().getSession();
				session.setAttribute("money", money);
				return "redirect:/jsp/index.jsp";
			}
			
			/**
			 * 所有场馆
			 */
			@RequestMapping("/showGym.do")
			public String showGym(Model model){
				List<GymBean> list = gymService.findAllGym();
				model.addAttribute("Gym",list);
				return "html/student/showGym.html";
			}
			
			/**
			 * 附近场馆
			 */
			@RequestMapping("/nearbyGym.do")
			public String showNearByGym(){
				return "html/map/nearbyGym.html";
			}
			
			/**
			 * 查询认证教练
			 */
			@RequestMapping("/showCoach.do")
			public String showCoach(Model model){
				List<CoachBean> list = service.findAllCoach();
				model.addAttribute("Coach",list);
				return "html/student/showCoach.html";
			}
			
			/**
			 * 附近场馆
			 */
			@RequestMapping("/nearbyCoach.do")
			public String showNearByCoach(){
				return "html/map/nearbyCoach.html";
			}
			
			@RequestMapping("/notify.do")
			public String  notify(Model model){
				Session session = SecurityUtils.getSubject().getSession();
				StudentBean stu=(StudentBean) session.getAttribute("stu");
				String id = stu.getS_id();
				List<RequestBean> listnotify  =service.findallreq(id);
				model.addAttribute("notify",listnotify );
				return "html/student/inform.html";
				
			}
			
			/**
			 * 充值成功后跳转
			 * @param model
			 * @return
			 */
			@RequestMapping("/investMoney.do")
			public String  investMoney(Model model){
				Session session = SecurityUtils.getSubject().getSession();
				StudentBean stu=(StudentBean) session.getAttribute("stu");
				Double money = (Double) session.getAttribute("money");
				String id = stu.getS_id();
				service.recharge(id, money);
				return "redirect:/student/showStudent.do";
			}
			 /* 查所有场馆
			 * @return
			 */
			@RequestMapping("/findAllGym.do")
			@ResponseBody
			public List<GymBean> findAllGym(){
				return gymService.findAllGym();
			}
			
			/**
			 * 显示某个场馆详细信息
			 * @param map
			 * @param stuId
			 * @return
			 */
			@RequestMapping("/showGymDetail.do")
			public String showGymDetail(ModelMap map,String stuId){
				StudentBean sb = service.findStudentbyId(stuId);
				map.put("sb", sb);
				return "/html/student/hispage.html";
			}
			
			@RequestMapping("gym.do")
			public String findgym(HttpServletRequest request){
				String gymid = request.getParameter("gymid");
				 GymBean gymBean = gymService.findGymById(gymid);
				 Session session = SecurityUtils.getSubject().getSession();
				 session.setAttribute("gym", gymBean);
				return "html/student/gymIndex.html";
			}
			
			
			
			/**
			 * 获取Session中的数据
			 * 
			 * @return
			 */
			@RequestMapping("/getGymToSession.do")
			@ResponseBody
			public GymBean getGymToSession() {
				// System.out.println("正在获取Session");
				Subject currentUser = SecurityUtils.getSubject();
				Session session = currentUser.getSession(false);
				if (session == null) {
					System.out.println("Session:null");
					return null;
				}
				Object object = session.getAttribute("gym");
				if (object == null) {
					System.out.println("gym:null");
					return null;
				}
				GymBean gymBean = (GymBean) object;
				// System.out.println(gymBean);
				return gymBean;
			}
			
			
			/**
			 * 选择课程
			 */
			@RequestMapping("/pickLesson.do")
			//@ResponseBody
			public String showLesson(LessonBean lessonBean,ModelMap map){
				System.out.println(lessonBean);
				String gymId = this.getGymToSession().getG_id();
				lessonBean.setL_g_id(gymId); 
				System.out.println(lessonBean);
				List<LessonBean> list = gymService.findLesson(lessonBean);
				if (list.isEmpty()) {
					list.add(lessonBean);
				}
				map.put("list", list);
				return "/html/student/addLesson.html";
			}
			
			/**
			 * 确认订单
			 * @param lessonid
			 * @return
			 */
			@RequestMapping("/order.do")
			@ResponseBody
			public int purchase(int lessonid){
				Session session = SecurityUtils.getSubject().getSession();
				StudentBean stu=(StudentBean) session.getAttribute("stu");
				String stuid = stu.getS_id();
				System.out.println(lessonid);
				LessonBean lessonbean = service.findlessonbyid(lessonid);
				if (stu.getS_money()>=lessonbean.getL_price()) {
					String date2String = DateUtil.Date2String(new java.util.Date(), "yy-MM-dd HH:mm:ss");
					OrderBean order =  new OrderBean();
					order.setCode("订单号");
					order.setO_l_id(lessonid);
					order.setO_price(lessonbean.getL_price());
					order.setO_s_id(stuid);
					order.setO_time(date2String);
					service.addorder(order);
					return order.getO_id();
				}
				else {
					
					return 0;
				}
			}	
				
}
