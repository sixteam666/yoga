package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.bean.CoachBean;

public interface ICoachDao {
	/**
	 * 閿熸枻鎷峰拸閿熸枻鎷烽敓锟�
	 * @param coach 閿熸枻鎷烽敓鏂ゆ嫹涓�閿熸枻鎷稢oachBean閿熸枻鎷烽敓鏂ゆ嫹
	 * @return 褰遍敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
	 */
	@Insert("insert into t_coach(c_name,c_password,c_phone,c_privacy,"
			+ "c_nickname,c_headimg,c_money,c_address,c_style,c_access,c_price,c_g_id)"
			+ "values(#{co.c_name},#{co.c_password},#{co.c_phone},#{co.c_privacy},"
			+ "#{co.c_nickname},#{co.c_headimg},#{co.c_money},#{co.c_address},#{co.c_style},"
			+ "#{co.c_access},#{co.c_price},#{co.c_g_id})")
	public int addCoach(@Param("co")CoachBean coach);
	/**
	 * 閿熸枻鎷烽敓鏂ゆ嫹閿熺煫浼欐嫹閿熸枻鎷烽敓鏂ゆ嫹璇㈤敓鏂ゆ嫹閿熸枻鎷�
	 * @param userName 閿熸枻鎷烽敓鏂ゆ嫹閿熺煫浼欐嫹閿熸枻鎷�
	 * @return 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
	 */
	@Select("select * from t_coach where c_name = #{name} or c_phone = #{name}")
	public CoachBean findCoachByName(String name);
	/**
	 * 閿熸枻鎷烽敓鎹风鎷烽敓鏂ゆ嫹id閿熸枻鎷疯閿熸枻鎷烽敓鏂ゆ嫹	
	 * @param gymId 閿熸枻鎷烽敓鏂ゆ嫹id
	 * @return 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
	 */
	@Select("select * from t_coach where c_g_id = #{gymId}")
	public List<CoachBean> findCoachByGymId(String gymId);
	/**
	 * 用于解约教练
	 * @param coach CoachBean对象
	 * @return 影响行数
	 */
	@Select("update t_coach set c_g_id = 0 where c_id = #{coach.id}")
	public int updateCoach(CoachBean coach);
}
