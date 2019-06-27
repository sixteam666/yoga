package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.bean.CoachBean;
import com.project.bean.GymBean;
import com.project.bean.StudentBean;

public interface IFollowDao {
	
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
	List<StudentBean> listFollowStudent(String id);
	
	/**
	 * 查询关注的教练
	 * @param id
	 * @return
	 */
	@Select("select * from t_coach where c_id in("
			+ "select a_idolid from t_attention where a_myid = #{id})")
	List<CoachBean> listFollowCoach(String id);
	
	/**
	 * 查询关注的场馆
	 * @param id
	 * @return
	 */
	@Select("select * from t_gym where g_id in("
			+ "select a_idolid from t_attention where a_myid = #{id})")
	List<GymBean> listFollowGym(String id);
	
	
	/**
	 * 查询学员粉丝
	 * @param id
	 * @return
	 */
	@Select("select * from t_student where s_id in("
			+ "select a_idolid from t_attention where a_myid = #{id})")
	List<StudentBean> listFollowingStudent(String id);
	
	/**
	 * 查询教练粉丝
	 * @param id
	 * @return
	 */
	@Select("select * from t_coach where c_id in("
			+ "select a_idolid from t_attention where a_myid = #{id})")
	List<CoachBean> listFollowingCoach(String id);
	
	/**
	 * 查询场馆粉丝
	 * @param id
	 * @return
	 */
	@Select("select * from t_gym where g_id in("
			+ "select a_myid from t_attention where a_idolid = #{id})")
	List<GymBean> listFollowingGym(String id);
	
	/**
	 * 查询学员好友
	 * @param id
	 * @return
	 */
	@Select("select * from t_student where s_id in("
			+ "select a_myid from t_attention where a_myid in("
			+ "select a_idolid from t_attention where a_myid = #{id})"
			+ "and a_idolid = #{id})")
	List<StudentBean> listFriendStudent(String id);
	
	/**
	 * 查询教练好友
	 * @param id
	 * @return
	 */
	@Select("select * from t_coach where c_id in("
			+ "select a_myid from t_attention where a_myid in("
			+ "select a_idolid from t_attention where a_myid = #{id})"
			+ "and a_idolid = #{id})")
	List<StudentBean> listFriendCoach(String id);
	
	/**
	 * 查询场馆好友（没有的功能别在意，peace and love）
	 * @param id
	 * @return
	 */
	@Select("select * from t_gym where g_id in("
			+ "select a_myid from t_attention where a_myid in("
			+ "select a_idolid from t_attention where a_myid = #{id})"
			+ "and a_idolid = #{id})")
	List<StudentBean> listFriendGym(String id);
	
	/**
	 * 查询关注数量
	 * @param id 用户id
	 * @return 关注数量
	 */
	@Select("select count(1) from t_attention where a_myid = #{id}")
	Integer countFollow(String id);
	
	/**
	 * 查询粉丝数量
	 * @param id 用户id
	 * @return 粉丝数量
	 */
	@Select("select count(1) from t_attention where a_idolid = #{id}")
	Integer countFollowing(String id);
	
	/**
	 * 判断是否关注某人
	 * @param id1
	 * @param id2
	 * @return
	 */
	@Select("select count(1) from t_attention where a_myid = #{fan} and a_idolid = #{idol}")
	Integer isFollow(@Param("fan") String fan, @Param("idol") String idol);
	
}
