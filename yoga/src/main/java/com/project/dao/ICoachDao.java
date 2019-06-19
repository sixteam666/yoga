package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.bean.CoachBean;

public interface ICoachDao {
	/**
	 * ��ӽ���
	 * @param coach ����һ��CoachBean����
	 * @return Ӱ������
	 */
	@Insert("insert into t_coach(c_name,c_password,c_phone,c_privacy,"
			+ "c_nickname,c_headimg,c_money,c_address,c_style,c_access,c_price,c_g_id)"
			+ "values(#{co.c_id},#{co.c_name},#{co.c_password},#{co.c_phone},#{co.c_privacy}"
			+ "#{co.c_nickname},#{co.c_headimg},#{co.c_money},#{co.c_address},#{co.c_style}"
			+ "#{co.c_access},#{co.c_price},#{co.c_g_id})")
	public int addCoach(@Param("co")CoachBean coach);
	/**
	 * �����û�����ѯ����
	 * @param userName �����û���
	 * @return ��������
	 */
	@Select("select * from t_coach where c_name = #{name} or c_phone = #{name}")
	public CoachBean findCoachByName(String name);
	/**
	 * ���ݳ���id��ѯ����	
	 * @param gymId ����id
	 * @return ��������
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
