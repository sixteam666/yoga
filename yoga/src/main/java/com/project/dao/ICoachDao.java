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
			+ "values(#{co.c_id},#{co.c_name},#{co.c_password},#{co.c_phone},#{co.c_privacy}"
			+ "#{co.c_nickname},#{co.c_headimg},#{co.c_money},#{co.c_address},#{co.c_style}"
			+ "#{co.c_access},#{co.c_price},#{co.c_g_id})")
	public int addCoach(@Param("co")CoachBean coach);
	/**
	 * 锟斤拷锟斤拷锟矫伙拷锟斤拷锟斤拷询锟斤拷锟斤拷
	 * @param userName 锟斤拷锟斤拷锟矫伙拷锟斤拷
	 * @return 锟斤拷锟斤拷锟斤拷锟斤拷
	 */
	@Select("select * from t_coach where c_name = #{name} or c_phone = #{name}")
	public CoachBean findCoachByName(String name);
	/**
	 * 锟斤拷锟捷筹拷锟斤拷id锟斤拷询锟斤拷锟斤拷	
	 * @param gymId 锟斤拷锟斤拷id
	 * @return 锟斤拷锟斤拷锟斤拷锟斤拷
	 */
	@Select("select * from t_coach where c_g_id = #{gymId}")
	public List<CoachBean> findCoachByGymId(String gymId);
	/**
	 * ���ڽ�Լ����
	 * @param coach CoachBean����
	 * @return Ӱ������
	 */
	@Select("update t_coach set c_g_id = 0 where c_id = #{coach.id}")
	public int updateCoach(CoachBean coach);
}
