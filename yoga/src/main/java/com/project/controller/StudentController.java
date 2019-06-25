package com.project.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public String login(String arg1,String pwd,HttpSession session,HttpServletRequest request){
		//产生一个用户（门面对象）
		//暂无盐值
		//Object obj = new SimpleHash("MD5", pwd,"",1024);
		Subject currentUser = SecurityUtils.getSubject();
		 if (!currentUser.isAuthenticated()) {
	            UsernamePasswordToken token = new UsernamePasswordToken("s"+arg1,pwd);
	            try {
	            	//调用login进行认证
	                currentUser.login(token);
	                System.out.println("认证成功");        		
	            } catch (UnknownAccountException uae) {
	            	System.out.println("用户名错误");
	            	return "no";
	            } catch (IncorrectCredentialsException ice) {
	            	System.out.println("密码错误");
	            	return "no";
	            } catch (LockedAccountException lae) {
	               System.out.println("被锁定异常");
	               return "no";
	            }
	      }
		 
		 StudentBean student = service.findStudentbyName(arg1);
         request.getSession().setAttribute("stu", student);
 		 System.out.println(session.getAttribute("stu"));	
		 return "yes";
	}
	
	@RequestMapping("/register.do")
	@ResponseBody
		public String register(StudentBean student){
			/**
			 * 未确定加盐值
			 */
			String id = UUID.randomUUID().toString();
			student.setS_id(id);
			Boolean boo = service.regist(student);
			//注册成功：定向登录界面；失败：定向注册界面
			System.out.println(boo);
			if (boo) {
				return "yes";
			}else {
				return "no";
			}	
		}
	
	
			/**
			 * 显示学员个人信息
			 * @param id 学员id
			 */
			@RequestMapping("showStudent.do")
			public String showCoachInfoByid(HttpServletRequest req) {
				HttpSession session = req.getSession();
				String id = (String) session.getAttribute("id");
				StudentBean stu = service.findStudentbyId(id);
				session.setAttribute("user", stu);
				return "redirect:/register.html";
			}

			
			
			/**
			 * 查找所有学员
			 * 
			 * @return
			 */
			@RequestMapping("/findAllStudent.do")
			@ResponseBody
			public List<StudentBean> findAllStudent() {
				List<StudentBean> list = service.findAllStudent();
				return list;
			}
			
			
			/**
			 * 更新学员信息
			 * @return
			 */
			public String updateStudent( StudentBean stu){
				
			boolean boo =  service.update(stu);
			if (boo) {
				
			}
			// todo ..

				return null;
			}

			




















}
