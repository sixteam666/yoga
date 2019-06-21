package com.project.service;

import java.util.List;

import com.project.bean.CoachBean;
import com.project.bean.DynamicBean;
import com.project.bean.GymBean;
import com.project.bean.StudentBean;

public interface ICoachService {
	/**
	 * 注册
	 * @param coach CoachBean 对象
	 * @return true:注册成功，false:注册失败
	 */
	public Boolean register(CoachBean coach);
	/**
	 * 教练登录
	 * @param name 用户名
	 * @return 教练对象
	 */
	public CoachBean login(String name);
	/**
	 * 教练签约或解约场馆
	 * @param g_id 场馆id或0
	 * @param c_id 教练id
	 * @return 影响行数
	 */
	public Boolean signGym(String g_id,String c_id);
	/**
	 * 查询学员
	 * @param c_id 教练id
	 * @return 学生集合
	 */
	public List<StudentBean> findStu(String c_id);
	
	/**
	 * 教练信息完善
	 * @author pan
	 * @param coach 完善后的教练对象
	 * @return 数据更新结果
	 */
	boolean updateCoachDetailInfo(CoachBean coach);
	
	/**
	 * 显示教练详细信息
	 * @author pan
	 * @param id 要展示的教练对象的id
	 * @return 教练实体对象
	 */
	CoachBean getCoachDetailInfo(String id);
	
	
	
//	//pan------
//	/**
//	 * 展示当前教练关注对象的所有动态
//	 * @param id
//	 * @return
//	 */
//	List<DynamicBean> listFollowDynamic(String id);
//	
//	/**
//	 * 显示教练关注的学员
//	 * @param id
//	 * @return
//	 */
//	List<StudentBean> listFollowStudent(String id);
//	
//	/**
//	 * 显示教练关注的教练
//	 * @param id
//	 * @return
//	 */
//	List<CoachBean> listFollowCoach(String id);
//	
//	/**
//	 * 显示教练关注的场馆
//	 * @param id
//	 * @return
//	 */
//	List<GymBean> listFollowGym(String id);
//	
//	/**
//	 * 显示教练学员粉丝
//	 * @param id
//	 * @return
//	 */
//	List<StudentBean> listFansById(String id);
//	
//	/**
//	 * 当前教练粉丝人数统计
//	 * @param id
//	 * @return
//	 */
//	Integer countIdolsById(String id);
//	
//	/**
//	 * 当前教练关注人数统计
//	 * @param id
//	 * @return
//	 */
//	Integer countFansById(String id);
//	
//	/**
//	 * 展示自己的动态所有动态
//	 * @param id
//	 * @return
//	 */
//	List<DynamicBean> listDynamicsById(String id);
//	//pan---------
}
