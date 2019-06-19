package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ICoachDao {
	/**
	 * 添加教练
	 * @param coach 接收一个CoachBean对象
	 * @return 影响行数
	 */
	@Insert("insert into t_coach(c_name,c_password,c_phone,c_privacy,"
			+ "c_nickname,c_headimg,c_money,c_address,c_style,c_access,c_price,c_g_id)"
			+ "values()")
	public int addCoach(@Param("co")CoachBean coach);
	/**
	 * 根据用户名查询教练
	 * @param userName 接收用户名
	 * @return 教练对象
	 */
	@Select("select * from t_coach where c_name = name")
	public CoachBean findCoachByName(String name);
	/**
	 * 根据场馆id查询教练	
	 * @param gymId 场馆id
	 * @return 教练集合
	 */
	@Select("select * from t_coach where c_g_id = gymId")
	public List<CoachBean> findCoachByGymId(int gymId);
}
