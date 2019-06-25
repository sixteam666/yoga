package com.project.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
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

import com.project.bean.StudentBean;
import com.project.service.IStudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private IStudentService service;
	
	/**1111
	 * 
	 * @param arg1 用户名或者手机号
	 * @param pwd 密码
	 * @param remenber 
	 * @return
	 */
	@RequestMapping("/login.do")
	public String login(String arg1,String pwd,Integer remember){
		//产生一个用户（门面对象）
		Subject currentUser = SecurityUtils.getSubject();
		 if (!currentUser.isAuthenticated()) {
	            UsernamePasswordToken token = new UsernamePasswordToken("s"+arg1,pwd);
	            try {
	            	if (remember != null && remember == 1) {
						token.setRememberMe(true);
					}
	            	//调用login进行认证
	                currentUser.login(token);
	                System.out.println("认证成功");
	                return "redirect:/index.html";
	            } 
	            // 父异常。认证失败异常
	            catch (AuthenticationException ae) {
	                //unexpected condition?  error?
	            	System.out.println("登录失败");
	            	return "redirect:/login.html";
	            }
	      }
		return "redirect:/index.html";
	}
	
	@RequestMapping("/register.do")
		public String register(StudentBean student){
			
			/**
			 * 未确定加盐值
			 */
			String id = UUID.randomUUID().toString();
			student.setS_id(id);
			Boolean boo = service.regist(student);
			//注册成功：定向登录界面；失败：定向注册界面
			if (boo) return "redirect:/login.html";
			return "redirect:/register.html";
		}
	
	
			/**
			 * 显示学员个人信息
			 * @param id 学员id
			 */
			@RequestMapping("/showStudent.do")
			public String showCoachInfoByid(Model m,HttpSession session) {
				/*HttpSession session = req.getSession();
				StudentBean studentBean = (StudentBean) session.getAttribute("user");
				String id = studentBean.getS_id();
				StudentBean stu = service.findStudentbyId(id);
				session.setAttribute("user", stu);*/
				StudentBean stu = new StudentBean();
				stu.setS_id("s001");
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
				System.out.println(id);
				StudentBean stu = service.findStudentbyId(id);
				System.out.println(stu);
				m.addAttribute("stu", stu);
				map.addAttribute("stuafter", stu);
				return "html/student/modify.html";
			}
			
			@PostMapping("/update.do")
			public String update(@ModelAttribute StudentBean stuafter,Model m){
						System.out.println(stuafter);
						service.update(stuafter);
						m.addAttribute("stu", stuafter);
						return "redirect:/student/showStudent.do";
			}

			




















}
