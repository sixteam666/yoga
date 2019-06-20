package com.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.bean.CoachBean;
import com.project.bean.GymBean;
import com.project.bean.LessonBean;
import com.project.bean.PictrueBean;
import com.project.service.IGymService;
import com.project.util.FileUtil;

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
	@Qualifier("gymService")
	private IGymService gymService;
	
	private FileUtil picUtil = new FileUtil();

	/**
	 * 登录
	 * 
	 * @param arg 邮箱或电话号
	 * @param pwd 登录密码
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
	 * 登录或注册前验证用户是否存在
	 * @param arg 邮箱或电话号
	 * @return 用户是否存在
	 */
	@RequestMapping("/findGym.do")
	@ResponseBody
	public Boolean loginTest(String arg) {
		if(gymService.login(arg) == null) {
			return false;
		}
		return true; 
	}

	/**
	 * 注册
	 * 
	 * @param gym
	 * @return
	 */
	@RequestMapping("/reg.do")
	@ResponseBody
	public int register(GymBean gym) {
		gym.setG_id(UUID.randomUUID().toString());

		// 盐值暂时无法确定
		// Object obj = new SimpleHash("MD5", gym.getG_password(),
		// gym.getG_email(),1024);
		// gym.setG_password(obj.toString());

		int result = gymService.register(gym);
		return result;
	}

	/**
	 * 查找所有场馆
	 * 
	 * @return
	 */
	@RequestMapping("/findAllGym.do")
	@ResponseBody
	public List<GymBean> findAllGym() {
		List<GymBean> list = gymService.findAllGym();
		return list;
	}

	/**
	 * 信息完善
	 * 
	 * @param gymBean
	 */
	@RequestMapping("/updateMsg.do")
	@ResponseBody
	public int updateMessage(Model model,ModelMap map,BindingResult result,@Validated GymBean gymBean) {
		model.addAttribute("gymBean", gymBean);
		if (result.hasErrors()) {
			System.out.println("有错！！！");
			//获取到所有的校验错误信息
			List<FieldError> errorList = result.getFieldErrors();
			for (FieldError fieldError : errorList) {
				map.put("error_"+fieldError.getField(), fieldError.getDefaultMessage());
			}
			//return "forward:/";
		}
		int number = gymService.updateMessage(gymBean);
		return number;
	}

	/**
	 * 一次添加一张图片
	 * 
	 * @param lists
	 */
	@RequestMapping("/addPictrue.do")
	@ResponseBody
	public int addOnePictrue(PictrueBean picBean,MultipartFile file,HttpServletRequest req) {
		//二进制传过来的文件
		System.out.println(file);
		//文件名
		System.out.println(file.getOriginalFilename());
		String imgName = picUtil.getFileName(file, req);
		//将图片名字保存数据库
		picBean.setP_imgname(imgName);
		//int number = gymService.addPictrue(list);
		return 0;
	}
	
	/**
	 * 一次添加多张图片
	 * 
	 * @param lists
	 */
	@RequestMapping("/addPictrues.do")
	@ResponseBody
	public int addPictrues(String gymId,MultipartFile[] files,HttpServletRequest req) {
		PictrueBean picBean = new PictrueBean();
		List<PictrueBean> list = new ArrayList<PictrueBean>();
		picBean.setP_g_id(gymId);
		
		if(files!=null && files.length>0){  
			//循环获取file数组中得文件  
			for(int i = 0;i<files.length;i++){  
			    MultipartFile file = files[i];  
				//二进制传过来的文件
				System.out.println(file);
				//文件名
				System.out.println(file.getOriginalFilename());
				String imgName = picUtil.getFileName(file, req);
				//将图片名字保存数据库
				picBean.setP_imgname(imgName);
				list.add(picBean);
		    }  
		}  
		
		int number = gymService.addPictrue(list);
		return 0;
	}
	
	/*
	 * 更改图片的名字，防止重名
	 */
	public String changeName(String name){
		String newName = name +"_"+ UUID.randomUUID().toString();
		return newName;
	}

	/**
	 * 教练课程安排
	 */
	@RequestMapping("/addLesson.do")
	@ResponseBody
	public int addLesson(Model model,ModelMap map,BindingResult result,@Validated LessonBean lessonBean) {
		model.addAttribute("lessonBean", lessonBean);
		if (result.hasErrors()) {
			System.out.println("有错！！！");
			//获取到所有的校验错误信息
			List<FieldError> errorList = result.getFieldErrors();
			for (FieldError fieldError : errorList) {
				map.put("error_"+fieldError.getField(), fieldError.getDefaultMessage());
			}
			//return "forward:/";
		}
		int number = gymService.addLesson(lessonBean);
		return number;
	}

	/**
	 * 查看我的签约教练
	 * 
	 * @param g_id
	 * @return
	 */
	@RequestMapping("/findMyCoach.do")
	@ResponseBody
	public List<CoachBean> findMyCoach(String g_id) {
		List<CoachBean> list = gymService.findMyCoach(g_id);
		return list;
	}

	/**
	 * 签约或解约教练
	 * 
	 * @param g_id
	 * @param c_id
	 * @return
	 */
	@RequestMapping("/updateCoach.do")
	@ResponseBody
	public int updateCoach(String g_id, String c_id) {
		int number = gymService.updateCoachBean(g_id, c_id);
		return number;
	}
	
	/**
	 * 提交签约教练的申请
	 * 
	 * @param g_id 提交申请的场馆id
	 * @param c_id 被申请的教练id
	 * @return 数据库签约申请表影响行数
	 */
	@RequestMapping("/submitSigingApplication.do")
	@ResponseBody
	public int submitSigingApplication(String g_id, String c_id) {
		
		return gymService.submitSigingApplication(g_id, c_id);
	}
	
<<<<<<< HEAD

=======
	/**
	 * 同意或拒绝教练的签约申请
	 * 
	 * @param g_id 被申请的场馆id
	 * @param c_id 提交申请的教练id
	 * @param state 1:同意，2：拒绝
	 * @return 影响行数
	 */
	@RequestMapping("/agreeSigingApplication.do")
	@ResponseBody
	public int agreeSigingApplication(String g_id, String c_id,int state) {
		return gymService.agreeSigingApplication(g_id, c_id, state);
	}
	
>>>>>>> faa8c209c6d752bab72b9058888ac1e98509d45c
}
