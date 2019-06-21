package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.bean.CoachBean;
import com.project.bean.GymBean;
import com.project.bean.StudentBean;

public interface IFallowDao {
	
	/**
	 * 添加关注
	 * @param myid
	 * @param idolid
	 * @return
	 */
	@Insert("insert into t_attention(a_myid, a_idolid) values(#{myid}, #{idolid})")
	Integer insert(@Param("myid") String myid, @Param("idolid") String idolid);
	
	/**
	 * 取消关注
	 * @param myid
	 * @param idolid
	 * @return
	 */
	@Delete("delete from t_attention where a_myid = #{myid} and a_idolid = #{idolid}")
	Integer delete(@Param("myid") String myid, @Param("idolid") String idolid);
	
	/**
	 * 查询关注的学员
	 * @param id
	 * @return
	 */
	@Select("select * from t_student where s_id in("
			+ "select a_idolid from t_attention where a_myid = #{id})")
	List<StudentBean> listFallowStudent(Integer id);
	
	/**
	 * 查询关注的教练
	 * @param id
	 * @return
	 */
	@Select("select * from t_coach where c_id in("
			+ "select a_idolid from t_attention where a_myid = #{id})")
	List<CoachBean> listFallowCoach(Integer id);
	
	/**
	 * 查询关注的场馆
	 * @param id
	 * @return
	 */
	@Select("select * from t_gym where g_id in("
			+ "select a_idolid from t_attention where a_myid = #{id})")
	List<GymBean> listFallowGym(Integer id);
	
	
	/**
	 * 查询学员粉丝
	 * @param id
	 * @return
	 */
	@Select("select * from t_student where s_id in("
			+ "select a_idolid from t_attention where a_myid = #{id})")
	List<StudentBean> listFallowingStudent(Integer id);
	
	/**
	 * 查询教练粉丝
	 * @param id
	 * @return
	 */
	@Select("select * from t_coach where c_id in("
			+ "select a_idolid from t_attention where a_myid = #{id})")
	List<CoachBean> listFallowingCoach(Integer id);
	
	/**
	 * 查询场馆粉丝
	 * @param id
	 * @return
	 */
	@Select("select * from t_gym where g_id in("
			+ "select a_myid from t_attention where a_idolid = #{id})")
	List<GymBean> listFallowingGym(Integer id);
	
	/**
	 * 查询学员好友
	 * @param id
	 * @return
	 */
	@Select("select * from t_student where s_id in("
			+ "select a_myid from t_attention where a_myid in("
			+ "select a_idolid from t_attention where a_myid = #{id})"
			+ "and a_idolid = #{id})")
	List<StudentBean> listFriendStudent(Integer id);
	
	/**
	 * 查询学员教练
	 * @param id
	 * @return
	 */
	@Select("select * from t_coach where c_id in("
			+ "select a_myid from t_attention where a_myid in("
			+ "select a_idolid from t_attention where a_myid = #{id})"
			+ "and a_idolid = #{id})")
	List<StudentBean> listFriendCoach(Integer id);
	
	/**
	 * 查询学员好友
	 * @param id
	 * @return
	 */
	@Select("select * from t_gym where g_id in("
			+ "select a_myid from t_attention where a_myid in("
			+ "select a_idolid from t_attention where a_myid = #{id})"
			+ "and a_idolid = #{id})")
	List<StudentBean> listFriendGym(Integer id);
	
}
