package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.bean.CoachBean;

public interface ICoachDao {
	/**
	 * 锟斤拷咏锟斤拷锟�
	 * @param coach 锟斤拷锟斤拷一锟斤拷CoachBean锟斤拷锟斤拷
	 * @return 影锟斤拷锟斤拷锟斤拷
	 */
	@Insert("insert into t_coach(c_name,c_password,c_phone,c_privacy,"
			+ "c_nickname,c_headimg,c_money,c_address,c_style,c_access,c_price,c_g_id)"
			+ "values()")
	public int addCoach(@Param("co")CoachBean coach);
	/**
	 * 锟斤拷锟斤拷锟矫伙拷锟斤拷锟斤拷询锟斤拷锟斤拷
	 * @param userName 锟斤拷锟斤拷锟矫伙拷锟斤拷
	 * @return 锟斤拷锟斤拷锟斤拷锟斤拷
	 */
	@Select("select * from t_coach where c_name = name")
	public CoachBean findCoachByName(String name);
	/**
	 * 锟斤拷锟捷筹拷锟斤拷id锟斤拷询锟斤拷锟斤拷	
	 * @param gymId 锟斤拷锟斤拷id
	 * @return 锟斤拷锟斤拷锟斤拷锟斤拷
	 */
	@Select("select * from t_coach where c_g_id = gymId")
	public List<CoachBean> findCoachByGymId(int gymId);
}
