package com.project.dao;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.project.bean.CoachBean;
import com.project.bean.GymBean;

/**
 * 会馆持久层接口
 * 
 * @author YuChen
 *
 */
public interface IGymDao {
	/**
	 * 添加会馆
	 * 
	 * @param gym
	 * @return
	 */
	@Insert("INSERT INTO t_gym(g_id,g_email,g_phone,g_qq,g_password) VALUES(#{g_id},#{g_email},#{g_phone},#{g_qq},#{g_password})")
	int addGym(GymBean gym);

	/**
	 * 查找所有会馆
	 * 
	 * @return
	 */
	@Select("SELECT * FROM t_gym WHERE g_id != '0'")
	List<GymBean> findAllGym();

	/**
	 * 查找热门会馆
	 * 
	 * @return
	 */
	@Select("SELECT * FROM t_gym limit 0,6 WHERE g_id != '0'")
	List<GymBean> findHotGym();
	
	
	/**
	 * 通过会馆id查询会馆
	 * 
	 * @param id
	 * @return 会馆对象
	 */
	@Select("SELECT * FROM t_gym WHERE g_id=#{id}")
	GymBean findGymById(String id);

	/**
	 * 通过 Email 或 Phone 查找会馆
	 * 
	 * @param arg 邮箱或电话号
	 * @return 会馆对象
	 */
	@Select("SELECT g_id,g_name,g_email,g_phone, g_password FROM t_gym WHERE g_email=#{arg} OR g_phone=#{arg}")
	GymBean findGymByEmailOrPhone(String arg);

	/**
	 * 修改会馆密码
	 * 
	 * @param id
	 * @param pwd
	 * @return 影响行数
	 */
	@Update("UPDATE t_gym SET g_password=#{pwd} WHERE g_id=#{id}")
	int updatePassowrd(@Param("id") String id, @Param("pwd") String pwd);
	
	
	/**
	 * 信息完善:地址、电话、名字
	 * @param gymBean
	 * @return
	 */
	@Update("update t_gym set g_address=#{g_address},g_contactphone=#{g_contactphone},g_name=#{g_name},g_headimg=#{g_headimg},g_qq=#{g_qq},g_message=#{g_message} where g_id=#{g_id}")
	public int updateMessage(GymBean gymBean);
	
	/**
	 * 通过场馆id查询响应签约的教练（教练向场馆发请求）
	 * 
	 * @param g_id
	 * @return 响应签约的教练集合
	 */
	@Select("SELECT c_id,c_name,c_phone,c_address,c_style,c_price,r_state AS c_g_id,c_headimg,c_nickname,r_date AS c_password "
			+ "FROM t_request r JOIN t_coach c ON r.r_reqid=c.c_id "
			+ "WHERE r.r_resid=#{g_id};")
	public List<CoachBean> findCoachByMyResponse(String g_id);
	
	/**
	 * 通过场馆id查询请求签约的教练（场馆向教练发请求）
	 * @param g_id
	 * @return 已请求签约的教练集合
	 */
	@Select("SELECT c_id,c_name,c_phone,c_address,c_style,c_price,r_state AS c_g_id,c_headimg,c_nickname,r_date AS c_password "
			+ "FROM t_request r JOIN t_coach c ON r.r_resid=c.c_id "
			+ "WHERE r.r_reqid=#{g_id}")
	public List<CoachBean> findCoachByMyRequest(String g_id);

}
