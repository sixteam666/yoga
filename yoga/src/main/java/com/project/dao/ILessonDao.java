package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.bean.LessonBean;

public interface ILessonDao {
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
	 * 修改课程学员人数
	 * @param lessonBean
	 * @return
	 */
	@Update("update t_lesson set l_s_number=#{l_s_number} where l_id=#{l_id}")
	public int updateStuNumber(LessonBean lessonBean);

}
