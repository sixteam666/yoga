package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ICoachDao {
	/**
	 * ��ӽ���
	 * @param coach ����һ��CoachBean����
	 * @return Ӱ������
	 */
	@Insert("insert into t_coach(c_name,c_password,c_phone,c_privacy,"
			+ "c_nickname,c_headimg,c_money,c_address,c_style,c_access,c_price,c_g_id)"
			+ "values()")
	public int addCoach(@Param("co")CoachBean coach);
	/**
	 * �����û�����ѯ����
	 * @param userName �����û���
	 * @return ��������
	 */
	@Select("select * from t_coach where c_name = name")
	public CoachBean findCoachByName(String name);
	/**
	 * ���ݳ���id��ѯ����	
	 * @param gymId ����id
	 * @return ��������
	 */
	@Select("select * from t_coach where c_g_id = gymId")
	public List<CoachBean> findCoachByGymId(int gymId);
}
