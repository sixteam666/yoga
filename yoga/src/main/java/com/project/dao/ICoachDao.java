package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.bean.CoachBean;

public interface ICoachDao {
	/**
	 * 閿熸枻鎷峰拸閿熸枻鎷烽敓锟�
	 * @param coach 閿熸枻鎷烽敓鏂ゆ嫹涓�閿熸枻鎷稢oachBean閿熸枻鎷烽敓鏂ゆ嫹
	 * @return 褰遍敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
	 */
	@Insert("insert into t_coach(c_name,c_password,c_phone,c_privacy,"
			+ "c_nickname,c_headimg,c_money,c_address,c_style,c_access,c_price,c_g_id)"
			+ "values(#{co.c_id},#{co.c_name},#{co.c_password},#{co.c_phone},#{co.c_privacy}"
			+ "#{co.c_nickname},#{co.c_headimg},#{co.c_money},#{co.c_address},#{co.c_style}"
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
	
	/**
	 * 教练信息完善，包含：动态权限设置、昵称设置、头像设置、地址设置
	 * @param coach
	 * @return
	 */
	@Update("update t_coach set c_privacy = #{c_privacy}, c_nickname = #{c_nickname},"
			+ "c_headimg = #{c_headimg}, c_address = #{c_address}, c_style = #{c_style},"
			+ "c_access = #{c_access}, c_price = #{c_price} ")
	public int update(CoachBean coach);
	
	@Select("select * from t_coach where id = #{id}")
	@Results({
		@Result(id = true, property = "c_id", column = "c_id"),
		@Result(property = "c_name", column = "c_name"),
		@Result(property = "c_password", column = "c_password"),
		@Result(property = "c_phone", column = "c_phone"),
		@Result(property = "c_privacy", column = "c_privacy"),
		@Result(property = "c_nickname", column = "c_nickname"),
		@Result(property = "c_headimg", column = "c_headimg"),
		@Result(property = "c_address", column = "c_address"),
		@Result(property = "c_style", column = "c_style"),
		@Result(property = "c_access", column = "c_access"),
		@Result(property = "c_price", column = "c_price"),
		@Result(property = "c_money", column = "c_money"),
		@Result(property = "gym", column = "c_g_id", 
			one = @One(select = "com.project.dao.IGymDao.findGymById"))})
	public CoachBean findCoachById(Integer id);
	
}
