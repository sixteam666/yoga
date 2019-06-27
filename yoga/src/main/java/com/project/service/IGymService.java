package com.project.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.project.bean.CoachBean;
import com.project.bean.GymBean;
import com.project.bean.LessonBean;
import com.project.bean.PictureBean;
import com.project.bean.RequestBean;

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
	 * 
	 * @return
	 */
	public List<GymBean> findAllGym();
	
	/**
	 * 查找热门场馆
	 * 
	 * @return
	 */
	public List<GymBean> findHotGym();

	/**
	 * 查找场馆by id
	 * 
	 * @param id
	 * @return 会馆对象
	 */
	public GymBean findGymById(String id);

	/**
	 * 信息完善
	 * 
	 * @param gymBean
	 * @return
	 */
	public int updateMessage(GymBean gymBean);


	/**
	 * 查看我的签约教练
	 * 
	 * @param g_id
	 * @return
	 */
	public List<CoachBean> findMyCoach(String g_id);
	
	/**
	 * 通过姓名或电话号码查看我的签约教练
	 * @param g_id
	 * @param nameOrPho
	 * @return
	 */
	public List<CoachBean> findCoaByNameOrPho(String g_id ,String nameOrPho);

	/**
	 * 解约或者签约教练
	 * 
	 * @param g_id 如g_id参数值为0，表示解约教练
	 * @param c_id
	 * @return
	 */
	public int updateCoachBean(String g_id, String c_id);

	/**
	 * 提交签约教练的申请
	 * 
	 * @param g_id 提交申请的场馆id
	 * @param c_id 被申请的教练id
	 * @return 数据库签约申请表影响行数
	 */
	public int submitSigingApplication(String g_id, String c_id);

	/**
	 * 同意或拒绝教练的签约申请
	 * 
	 * @param g_id 被申请的场馆id
	 * @param c_id 提交申请的教练id
	 * @param state 1:同意，2：拒绝
	 * @return 影响行数
	 */
	public int agreeSigingApplication(String g_id, String c_id,int state);
	
	/**
	 * 场馆添加图片
	 * 
	 * @param lists
	 * @return
	 */
	public int addPictrue(List<PictureBean> list);
	
	/**
	 * 查找图片
	 * @param gymId
	 * @return
	 */
	public List<PictureBean> findAllPic(String gymId);
	
	/**
	 * 删除图片
	 * @param id
	 * @return
	 */
	public int deletePicture(int id);

	/**
	 * 教练课程安排
	 * 
	 * @param lessonBean
	 * @return
	 */
	public int addLesson(LessonBean lessonBean);
	
	/**
	 * 查看课程
	 * @param lessonBean
	 * @return
	 */
	public List<LessonBean> findLesson(LessonBean lessonBean);
	
	/**
	 * 删除课程
	 * @param id
	 * @return
	 */
	public int deleteLesson(int id);
	
	/**
	 * 通过场馆id查询响应签约的教练（教练向场馆发请求）
	 * 
	 * @param g_id
	 * @return 响应签约的教练集合
	 */
	public List<CoachBean> findCoachByMyResponse(String g_id);
	
	/**
	 * 通过场馆id查询请求签约的教练（场馆向教练发请求）
	 * @param g_id
	 * @return 已请求签约的教练集合
	 */
	public List<CoachBean> findCoachByMyRequest(String g_id);
	
}
