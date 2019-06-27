package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.bean.CoachBean;
import com.project.bean.DynamicBean;
import com.project.service.IBlogService;
import com.project.service.ICoachService;

@Controller
@RequestMapping("dynamic")
public class DynamicController {
	
	@Autowired
	private IBlogService blogService;
	@Autowired
	private ICoachService coachService;
	/**
	 * 查询所有动态
	 * @param map 
	 * @return
	 */
	@RequestMapping("showHot.do")
	public String showHotDynamics(ModelMap map) {
		List<DynamicBean> dynamicList = blogService.listAllDynamics();
		map.put("dynamicList", dynamicList);
		return "html/coach/dynamic.html";
	}
	
	/**
	 * 显示好友动态
	 * @return
	 */
	@RequestMapping("showFriend.do")
	public String showFriendDynamics(ModelMap map) {
		//String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		String id = "1";
		List<DynamicBean> friendDynamicList = blogService.listFriendDynamic(id);
		map.put("dynamicList", friendDynamicList);
		return "html/coach/dynamic.html";
	}
	
	/**
	 * 显示关注人动态
	 * @param map
	 * @return
	 */
	@RequestMapping("showFollow.do")
	public String showFollowDynamics(ModelMap map) {
		//String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		String id = "1";
		List<DynamicBean> followDynamicList = blogService.listFollowDynamic(id);
		map.put("dynamicList", followDynamicList);
		return "html/coach/dynamic.html";
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
		CoachBean coach = coachService.getCoachById(id);
		map.put("coach", coach);
		map.put("dynamicList", myDynamicList);
		map.put("follow", follow);
		map.put("following", following);
		return "/html/coach/dynamicIndex.html";
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
}
