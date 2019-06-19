package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
	@Insert("INSERT INTO t_gym(g_email,g_phone,g_qq) values(#{g_email},#{g_phone},#{g_qq})")
	int addGym(GymBean gym);

	/**
	 * 查找所有会馆
	 * 
	 * @return
	 */
	@Select("SELECT * FORM t_gym")
	List<GymBean> findAllGym();

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
	@Select("SELECT g_id, g_password FROM t_gym WHERE g_email=#{arg} OR g_phone=#{arg}")
	GymBean findGymByEmail(String arg);

	/**
	 * 修改会馆密码
	 * 
	 * @param id
	 * @param pwd
	 * @return 影响行数
	 */
	@Update("UPDATE t_gym SET g_password=#{pwd} WHERE g_id=#{id}")
	int updatePassowrd(@Param("id") String id, @Param("pwd") String pwd);

}
