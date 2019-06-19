package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.bean.CoachBean;

public interface ICoachDao {
	/**
	 * 添加教练
	 * @param coach oachBean对象
	 * @return 影响行数
	 */
	@Insert("insert into t_coach(c_name,c_password,c_phone,c_privacy,"
			+ "c_nickname,c_headimg,c_money,c_address,c_style,c_access,c_price,c_g_id)"
			+ "values(#{c_name},#{c_password},#{c_phone},#{c_privacy},"
			+ "#{c_nickname},#{c_headimg},#{c_money},#{c_address},#{c_style},"
			+ "#{c_access},#{c_price},#{c_g_id})")
	public int addCoach(CoachBean coach);
	/**
	 * 根据用户名或电话号码查询教练
	 * @param name 用户名或电话号码
	 * @return 教练对象
	 */
	@Select("select * from t_coach where c_name = #{name} or c_phone = #{name}")
	public CoachBean findCoachByName(String name);
	/**
	 * 通过场馆id查询教练	
	 * @param gymId 场馆id
	 * @return 教练集合
	 */
	@Select("select * from t_coach where c_g_id = #{gymId}")
	public List<CoachBean> findCoachByGymId(String gymId);
	/**
	 * 用于签约或者解约教练
	 * @param coach CoachBean对象
	 * @return 影响行数
	 */
	@Update("update t_coach set c_g_id = #{g_id} where c_id = #{c_id}")
	public int updateCoachGymId(String g_id,String c_id);
}
