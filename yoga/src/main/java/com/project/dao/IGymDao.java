package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.project.bean.GymBean;
import com.project.bean.LessonBean;
import com.project.bean.PictrueBean;

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
	@Insert("INSERT INTO t_gym(g_id,g_email,g_phone,g_qq) VALUES(#{id},#{g_email},#{g_phone},#{g_qq})")
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
	@Update("update t_gym set g_address=#{g_address},g_contactphone=#{g_contactphone},g_name=#{g_name},g_headimg=#{g_headimg} where g_id=#{g_id}")
	public int updateMessage(GymBean gymBean);
	
	/**
	 * 添加图片
	 * @param list
	 * @return
	 */
	//@Insert("insert into t_picture(p_imgname,p_g_id) values(#{p_imgname},#{p_g_id})")
	@Insert({"<script> insert into t_picture(p_imgname,p_g_id) values " +
 			"<foreach collection='list' item='item' index='index' separator=','>"+
            "(#{item.p_imgname},#{item.p_g_id})"+
            "</foreach> </script>"})
	public int addPictrue(@Param(value="list") List<PictrueBean> list);
	
	
	/**
	 * 教练课程安排
	 * @param lessonBean
	 * @return
	 */
	@Insert("insert into t_lesson(l_time,l_c_id,l_descirbe,l_size,l_price,l_g_id) "
			+ "values(#{l_time},#{l_c_id},#{l_descirbe},#{l_size},#{l_price},#{l_g_id})")
	public int addLesson(LessonBean lessonBean);

}
