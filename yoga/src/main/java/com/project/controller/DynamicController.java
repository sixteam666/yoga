package com.project.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.bean.CoachBean;
import com.project.bean.DynamicBean;
import com.project.bean.GymBean;
import com.project.bean.StudentBean;
import com.project.service.IBlogService;
import com.project.service.ICoachService;
import com.project.service.IGymService;
import com.project.util.DateUtil;

@Controller
@RequestMapping("dynamic")
public class DynamicController {
	
	@Autowired
	private IBlogService blogService;
	@Autowired
	private ICoachService coachService;
	@Autowired
	@Qualifier("gymService")
	private IGymService gymService;
	/**
	 * 查询所有动态
	 * @param map 
	 * @return
	 */
	@RequestMapping("showHot.do")
	public String showHotDynamics(ModelMap map) {
		Session session = SecurityUtils.getSubject().getSession();
		List<DynamicBean> dynamicList = blogService.listAllDynamics();
		map.put("dynamicList", dynamicList);
		//根据session判断当前登陆用户类型,分类转发
		if (session.getAttribute("stu")!=null) {
			return "/html/student/dynamic.html";
		}else if (session.getAttribute("coach")!=null) {
			return "/html/coach/dynamic.html";
		}else if (session.getAttribute("gym")!=null) {
			return "/html/gym/dynamic.html";
		}else {
			throw new RuntimeException("显示热门动态时未找到当前用户！");
		}
	}
	
	/**
	 * 显示好友动态
	 * @return
	 */
	@RequestMapping("showFriend.do")
	public String showFriendDynamics(ModelMap map) {
		Session session = SecurityUtils.getSubject().getSession();
		String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		//String id = "1";
		List<DynamicBean> friendDynamicList = blogService.listFriendDynamic(id);
		map.put("dynamicList", friendDynamicList);
		//根据session判断当前登陆用户类型,分类转发
				if (session.getAttribute("stu")!=null) {
					return "html/student/dynamic.html";
				}else if (session.getAttribute("coach")!=null) {
					return "html/coach/dynamic.html";
				}else if (session.getAttribute("gym")!=null) {
					return "html/gym/dynamic.html";
				}else {
					throw new RuntimeException("显示好友动态时未找到当前用户！");
				}
	}
	
	/**
	 * 显示关注人动态
	 * @param map
	 * @return
	 */
	@RequestMapping("showFollow.do")
	public String showFollowDynamics(ModelMap map) {
		Session session = SecurityUtils.getSubject().getSession();
		//String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		String id = "1";
		List<DynamicBean> followDynamicList = blogService.listFollowDynamic(id);
		map.put("dynamicList", followDynamicList);
		//根据session判断当前登陆用户类型,分类转发
		if (session.getAttribute("stu")!=null) {
			return "html/student/dynamic.html";
		}else if (session.getAttribute("coach")!=null) {
			return "html/coach/dynamic.html";
		}else if (session.getAttribute("gym")!=null) {
			return "html/gym/dynamic.html";
		}else {
			throw new RuntimeException("显示关注动态时未找到当前用户！");
		}
	}
	
	/**
	 * 显示自己所有动态
	 * @param map
	 * @return
	 */
	@RequestMapping("showMy.do")
	public String showMyDynamics(ModelMap map) {
		//String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		String id = "1";
		
		List<DynamicBean> myDynamicList = blogService.listDynamicsById(id);
		Integer follow = blogService.countFollow(id);
		Integer following = blogService.countFollowing(id);
		Integer friends = blogService.countFriends(id);
		map.put("dynamicList", myDynamicList);
		map.put("follow", follow);
		map.put("following", following);
		map.put("friends", friends);
		return "/html/coach/dynamicIndex.html";
	}
	
	@RequestMapping("gymShowMy.do")
	public String gymShowMyDyn(ModelMap map) {
		Session session = SecurityUtils.getSubject().getSession();
		GymBean gymBean = (GymBean) session.getAttribute("gym");
		String id = gymBean.getG_id();
		gymBean = gymService.findGymById(id);
		map.put("gymBean", gymBean);
		List<DynamicBean> myDynamicList = blogService.listDynamicsById(id);
		//Integer follow = blogService.countFollow(id);
		Integer following = blogService.countFollowing(id);
		//Integer friends = blogService.countFriends(id);
		map.put("dynamicList", myDynamicList);
		//map.put("follow", follow);
		map.put("following", following);
		//map.put("friends", friends);
		return "/html/gym/dynamicIndex.html";
	}
	
	/**
	 * 向他人展示动态页面
	 * @param map
	 * @return
	 */
	@RequestMapping("showOther.do")
	public String showMyDynamics(String id,ModelMap map) {
		String currentId = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		if(id.equals(currentId)) {
			return "redirect:/dynamic/showMy.do";
		}
		
		//还可能是学员，或场馆
		CoachBean coach = coachService.getCoachById(id);
		
		
		if(blogService.isFollow(currentId, id)){
			coach.setC_privacy(-1);
		}
		
		
		List<DynamicBean> myDynamicList = blogService.listDynamicsById(id);
		Integer follow = blogService.countFollow(id);
		Integer following = blogService.countFollowing(id);
		Integer friends = blogService.countFriends(id);
		map.put("coach", coach);
		map.put("dynamicList", myDynamicList);
		map.put("follow", follow);
		map.put("following", following);
		map.put("friends", friends);
		return "/html/coach/dynamicToOther.html";
	}
	
	/**
	 * 删除动态
	 * @param id
	 * @return
	 */
	@RequestMapping("delete.do")
	public String deleteDynamic(Integer id) {
		blogService.delete(id);
		return "redirect:/dynamic/showMy.do";
	}
	
	/**
	 * 删除场馆动态
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteGymDynamic.do")
	public String deleteGymDynamic(Integer id) {
		blogService.delete(id);
		return "redirect:/dynamic/gymShowMy.do";
	}
	
	/**
	 * 显示关注的人
	 * 垃圾代码
	 * @param map
	 * @return
	 */
	@RequestMapping("follow.do")
	public String follows(String id, ModelMap map) {
		if(id == null) {
		 id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
			//id = "1";
		}
		List<CoachBean> followCoach = blogService.listFollowCoach(id);
		List<StudentBean> followStudent = blogService.listFollowStudent(id);
		List<GymBean> followGym = blogService.listFollowGym(id);
		map.put("followCoach", followCoach);
		map.put("followStudent", followStudent);
		map.put("followGym", followGym);
		
		return "html/coach/follow.html";
	}
	
	/**
	 * 显示粉丝
	 * 垃圾代码
	 * @param map
	 * @return
	 */
	@RequestMapping("following.do")
	public String followings(String id, ModelMap map) {
		if(id == null) {
			//String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
			id = "1";
		}
		
		List<CoachBean> coachFans = blogService.listCoachFans(id);
		List<StudentBean> studentFans = blogService.listStudentFans(id);
		map.put("coachFans", coachFans);
		map.put("studentFans", studentFans);
		
		return "html/coach/following.html";
	}
	
	/**
	 * 显示粉丝
	 * 垃圾代码
	 * @param map
	 * @return
	 */
	@RequestMapping("gymFollowing.do")
	public String gymFollowing(ModelMap map) {
		Session session = SecurityUtils.getSubject().getSession();
		GymBean gymBean = (GymBean) session.getAttribute("gym");
		String id = gymBean.getG_id();
		List<CoachBean> coachFans = blogService.listCoachFans(id);
		List<StudentBean> studentFans = blogService.listStudentFans(id);
		map.put("coachFans", coachFans);
		map.put("studentFans", studentFans);
		
		return "/html/gym/following.html";
	}
	
	/**
	 * 显示好友
	 * 垃圾代码
	 * @param map
	 * @return
	 */
	@RequestMapping("friends.do")
	public String friends(String id, ModelMap map) {
		if(id == null) {
			//String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
			id = "1";
		}
		
		List<CoachBean> coachFriends = blogService.listCoachFriends(id);
		List<StudentBean> studentFriends = blogService.listStudentFriends(id);
		map.put("coachFriends", coachFriends);
		map.put("studentFriends", studentFriends);
		
		return "html/coach/friends.html";
	}
	
	/**
	 * 添加动态
	 * @param dynamic
	 * @return
	 */
	@RequestMapping("/addDynamic.do")
	public String addDynamic(DynamicBean dynamic) {
		String d_time = DateUtil.Date2String(new Date(), "yyyy-MM-dd hh:mm:ss");
		String d_userId = "";
		String d_headimg = "";
		String d_nickname = "";
		Integer d_type = null;
		CoachBean coach = (CoachBean) SecurityUtils.getSubject().getSession().getAttribute("coach");
		System.out.println("===========***"+coach);
		if(coach != null) {
			d_userId = coach.getC_id();
			d_headimg = coach.getC_headimg();
			d_nickname = coach.getC_nickname();
			d_type = 1;
		} else {
			StudentBean student = (StudentBean) SecurityUtils.getSubject().getSession().getAttribute("stu");
			if(student != null) {
				d_userId = student.getS_id();
				d_headimg = student.getS_headimg();
				d_nickname = student.getS_nickname();
				d_type = 0;
			} else {
				GymBean gym = (GymBean) SecurityUtils.getSubject().getSession().getAttribute("gym");
				if(gym != null) {
					d_userId = gym.getG_id();
					GymBean gymBean = gymService.findGymById(d_userId);
					d_headimg = gymBean.getG_headimg();
					//d_headimg = gym.getG_headimg();
					
					d_nickname = gym.getG_name();
					d_type = 2;
				} else {
					throw new RuntimeException("添加动态时未找到当前用户！");
				}
			}
		}
		dynamic.setD_userid(d_userId);
		dynamic.setD_headimg(d_headimg);
		dynamic.setD_nickname(d_nickname);
		dynamic.setD_type(d_type);
		dynamic.setD_time(d_time);
		blogService.insert(dynamic);
		return "redirect:/dynamic/showHot.do";
	}
	
	/**
	 * 添加关注
	 * @param followId 关注人id
	 */
	@RequestMapping("addFollow.do")
	@ResponseBody
	public void addFollow(String followId) {
		blogService.addFollow(followId);
	}
	
	/**
	 * 取消关注
	 * @param followId
	 */
	public void cancelFollow(String followId) {
		blogService.cancelFollow(followId);
	}
}
