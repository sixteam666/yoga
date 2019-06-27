package com.project.service;

import java.util.List;

import com.project.bean.CoachBean;
import com.project.bean.DynamicBean;
import com.project.bean.GymBean;
import com.project.bean.StudentBean;

public interface IBlogService {
	
	/**
	 * 添加动态
	 * @param dynamic
	 * @return
	 */
	Integer insert(DynamicBean dynamic);
	
	/**
	 * 删除动态
	 * @param id
	 * @return
	 */
	Integer delete(Integer id);
	
	/**
	 * 展示热门动态
	 * @return
	 */
	List<DynamicBean> listAllDynamics();
	
	/**
	 * 展示当前用户关注对象的所有动态
	 * @param id
	 * @return
	 */
	List<DynamicBean> listFollowDynamic(String id);
	
	/**
	 * 展示当前用户好友的所有动态
	 * @param id
	 * @return
	 */
	List<DynamicBean> listFriendDynamic(String id);
	
	/**
	 * 展示自己的所有动态
	 * @param id
	 * @return
	 */
	List<DynamicBean> listDynamicsById(String id);
	
	/**
	 * 显示用户关注的学员
	 * @param id
	 * @return
	 */
	List<StudentBean> listFollowStudent(String id);
	
	/**
	 * 显示用户关注的教练
	 * @param id
	 * @return
	 */
	List<CoachBean> listFollowCoach(String id);
	
	/**
	 * 显示用户关注的场馆
	 * @param id
	 * @return
	 */
	List<GymBean> listFollowGym(String id);
	
	/**
	 * 显示用户学员粉丝
	 * @param id
	 * @return
	 */
	List<StudentBean> listStudentFans(String id);
	
	/**
	 * 显示用户的教练粉丝
	 * @param id
	 * @return
	 */
	List<CoachBean> listCoachFans(String id);
	
	/**
	 * 当前用户粉丝人数统计
	 * @param id
	 * @return
	 */
	Integer countFollow(String id);
	
	/**
	 * 当前用户关注人数统计
	 * @param id
	 * @return
	 */
	Integer countFollowing(String id);
	
}
