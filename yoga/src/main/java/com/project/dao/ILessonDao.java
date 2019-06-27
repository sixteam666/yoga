package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.project.bean.LessonBean;

public interface ILessonDao {
	
	/**
	 * 通过课程id查课程
	 * @return
	 */
	@Select("select * from t_lesson where l_id = #{id}")
	public LessonBean findlessonbyid(int id);
	
	
	
	/**
	 * 查找某场馆星期几第几节课
	 * @param lessonBean
	 * @return
	 */
	@Select("select * from t_lesson where l_g_id=#{l_g_id} and l_weekday=#{l_weekday} and l_datetime=#{l_datetime}")
	public List<LessonBean> findLesson(LessonBean lessonBean);
	
	
	/**
	 * 教练课程安排
	 * @param lessonBean
	 * @return
	 */
	@Insert("insert into t_lesson(l_time,l_c_id,l_descirbe,l_size,l_price,l_g_id,l_weekday,l_datetime) "
			+ "values(#{l_time},#{l_c_id},#{l_descirbe},#{l_size},#{l_price},#{l_g_id},#{l_weekday},#{l_datetime})")
	public int addLesson(LessonBean lessonBean);
	
	/**
	 * 删除课程
	 * @param id
	 * @return
	 */
	@Delete("delete from t_lesson where l_id=#{l_id}")
	public int deleteLesson(int id);

	
	
	/**
	 * 通过学生id查询该生课程
	 * @param id
	 * @return
	 */
	@Select("SELECT * from t_lesson where l_id in (SELECT o_l_id from t_order where o_s_id = #{s_id}) ")
	public List<LessonBean> findlessonbystudentid(String id);
	
	
	/**通过课程id查教练id
	 * @return
	 */
	@Select("select l_c_id  from t_lesson where l_id = #{l_id}")
	public String findcoachidbylessonid(Integer l_id);
}
