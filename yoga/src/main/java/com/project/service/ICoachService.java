package com.project.service;

import java.util.List;

import com.project.bean.CoachBean;
import com.project.bean.DynamicBean;
import com.project.bean.GymBean;
import com.project.bean.LessonBean;
import com.project.bean.StudentBean;
import com.project.bean.WordsBean;

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
	CoachBean getCoachById(String id);
	
	/**
	 * 修改密码
	 * @author pan
	 * @param id 教练id
	 * @return 修改结果
	 */
	Boolean updatePassword(String id, String newPassword);
	
	/**
	 * 提现
	 * @author pan
	 * @param id 教练id
	 * @param money 提取现金数量
	 * @return
	 */
	Boolean updateMoney(String id, double money, Integer cardId);
	
	/**
	 * 获得教练所有学员
	 * @author pan
	 * @param id 教练id
	 * @return 学员对象集合
	 */
	List<StudentBean> listMyStudent(String id);
	
	//pan------
	/**
	 * 展示当前教练关注对象的所有动态
	 * @param id
	 * @return
	 */
	List<DynamicBean> listFollowDynamic(String id);
	
	/**
	 * 展示当前教练好友的所有动态
	 * @param id
	 * @return
	 */
	List<DynamicBean> listFriendDynamic(String id);
	
	/**
	 * 显示教练关注的学员
	 * @param id
	 * @return
	 */
	List<StudentBean> listFollowStudent(String id);
	
	/**
	 * 显示教练关注的教练
	 * @param id
	 * @return
	 */
	List<CoachBean> listFollowCoach(String id);
	
	/**
	 * 显示教练关注的场馆
	 * @param id
	 * @return
	 */
	List<GymBean> listFollowGym(String id);
	
	/**
	 * 显示教练学员粉丝
	 * @param id
	 * @return
	 */
	List<StudentBean> listStudentFans(String id);
	
	/**
	 * 显示教练的教练粉丝
	 * @param id
	 * @return
	 */
	List<CoachBean> listCoachFans(String id);
	
	/**
	 * 当前教练粉丝人数统计
	 * @param id
	 * @return
	 */
	Integer countFollow(String id);
	
	/**
	 * 当前教练关注人数统计
	 * @param id
	 * @return
	 */
	Integer countFollowing(String id);
	
	/**
	 * 展示自己的所有动态
	 * @param id
	 * @return
	 */
	List<DynamicBean> listDynamicsById(String id);
	//pan---------
	
	CoachBean showCoachDetailInfo(Integer id);
	/**
	 * 查看所有场馆
	 * @return 场馆集合
	 */
	List<GymBean> showAllGym();
	/**
	 * 显示周围学生
	 * @return 学生集合
	 */
	List<StudentBean> showAllStu();
	/**
	 * 向场馆申请签约
	 * @param r_reqid 申请人id
	 * @param r_resid 被申请人id
	 * @return true:添加成功；false：添加失败
	 */
	String addRequest(String r_reqid,String r_resid);
	/**
	 * 处理申请
	 * @param r_reqid 申请人id
	 * @param r_resid 被申请人id
	 * @param state 申请状态
	 * @return 是否处理成功
	 */
	Boolean updateRequest(String r_reqid,String r_resid,int r_state);
	
	/**
	 * 获得教练基本信息，用于个人资料基本信息修改
	 * @author pan
	 * @param id
	 * @return
	 */
	public CoachBean getPersonalInfo(String id);
	/**
	 * 更新基本信息
	 * @author pan
	 * @param coach
	 */
	public void updatePersonalInfo(CoachBean coach);
	/**
	 * 教练认证
	 * @author pan
	 * @param coach
	 */
	public void updateAuthentication(CoachBean coach);
	
	/**
	 * 获得教练课程设置
	 * @param id
	 * @return
	 */
	public CoachBean getLessonInfo(String id);
	/**
	 * 更新教练课程设置
	 * @param coach
	 */
	public void updateLessonInfo(CoachBean coach);
	/**
	 * 查询钱包余额
	 * @param id
	 */
	public Double getMoney(String id);
	
	/**
	 * 查询热门教练
	 * @param id
	 */
	public List<CoachBean> findHotCoach();

	/**
	 * 教练留言
	 * @param stuId
	 * @param message
	 * @return
	 */
	public String sendMessage(WordsBean words);
	/**
	 * 展示给其他用户的个人信息
	 * @param currentUserId 要查看信息的用户id
	 * @param coachId 教练id
	 * @return 教练对象
	 */
	public CoachBean showToOtherUser(String currentUserId, String coachId, Integer type);
	
	/**
	 * 查询我的所有课程
	 * @param id
	 * @return
	 */
	List<LessonBean> listLessons(String id);
}
