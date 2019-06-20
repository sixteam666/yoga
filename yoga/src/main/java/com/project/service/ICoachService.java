package com.project.service;

import java.util.List;

import com.project.bean.CoachBean;
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
}
