package com.project.service;

import java.util.List;

import com.project.bean.CoachBean;
import com.project.bean.GymBean;
import com.project.bean.LessonBean;
import com.project.bean.PictrueBean;

/**
 * 场馆业务层接口
 * 
 * @author YuChen
 *
 */
public interface IGymService {
	/**
	 * 注册
	 * 
	 * @param gym 场馆对象
	 * @return int 影响行数
	 */
	int register(GymBean gym);

	/**
	 * 会馆登录
	 * 
	 * @param arg 邮箱或电话号
	 * @return 会馆对象
	 */
	GymBean login(String arg);

	/**
	 * 修改密码
	 * 
	 * @param id
	 * @param pwd
	 * @return 影响行数
	 */
	int updatePassword(String id, String pwd);
	
	
	/**
	 * 查找所有场馆
	 * @return
	 */
	public List<GymBean> findAllGym();
	
	/**
	 * 查找场馆by id
	 * @param id
	 * @return
	 */
	public GymBean findGymById(String id);
	
	/**
	 * 信息完善
	 * @param gymBean
	 * @return
	 */
	public int updateMessage(GymBean gymBean);
	
	/**
	 * 场馆添加图片
	 * @param lists
	 * @return
	 */
	public int addPictrue(List<PictrueBean> lists);
	
	/**
	 * 教练课程安排
	 * @param lessonBean
	 * @return
	 */
	public int addLesson(LessonBean lessonBean);
	
	
	/**
	 * 查看我的签约教练
	 * @param g_id
	 * @return
	 */
	public List<CoachBean> findMyCoach(String g_id);
	
	/**
	 * 解约签约教练
	 * @param coachBean
	 * @return
	 */
	public int endDeal(CoachBean coachBean);
	

}
