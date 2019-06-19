package com.project.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
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
	 * 修改会馆密码
	 * 
	 * @param id
	 * @param pwd
	 * @return 影响行数
	 */
	@Update("UPDATE t_gym SET g_password=#{pwd} WHERE g_id=#{id}")
	int updatePassowrd(@Param("id") int id, @Param("pwd") String pwd);

}
