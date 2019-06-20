package com.project.controller;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.bean.CoachBean;
import com.project.bean.GymBean;
import com.project.bean.LessonBean;
import com.project.bean.PictrueBean;
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
	@Qualifier("gymService")
	private IGymService gymService;

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
	public int updateMessage(GymBean gymBean) {
		int number = gymService.updateMessage(gymBean);
		return number;
	}

	/**
	 * 添加图片
	 * 
	 * @param lists
	 */
	@RequestMapping("/addPictrue.do")
	@ResponseBody
	public int addPictrue(List<PictrueBean> list) {
		int number = gymService.addPictrue(list);
		return number;
	}

	/**
	 * 教练课程安排
	 */
	@RequestMapping("/addLesson.do")
	@ResponseBody
	public int addLesson(LessonBean lessonBean) {
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
	public int updateCoach(String g_id, String c_id) {
		int number = gymService.updateCoachBean(g_id, c_id);
		return number;
	}

}
