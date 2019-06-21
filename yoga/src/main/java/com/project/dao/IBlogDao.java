package com.project.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.bean.DynamicBean;

public interface IBlogDao {
	
	/**
	 * 添加动态
	 * @param dynamic
	 */
	@Insert("insert into "
			+ "t_dynamic(d_content,d_time,d_userid,d_img,d_heading,d_nickname,d_type) "
			+ "values(#{d_content},#{d_time},#{d_userid},#{d_img},#{d_heading},"
			+ "#{d_nickname},#{d_type})")
	Integer insert(DynamicBean dynamic);
	
	/**
	 * 删除动态
	 * @param id
	 * @return
	 */
	@Delete("delete from t_dynamic where d_id = #{id}")
	Integer delete(Integer id);

	/**
	 * 查询所有动态
	 * @return
	 */
	@Select("select * from t_dynamic")
	List<DynamicBean> listAllBlog();
	
	/**
	 * 查询某一用户所有动态
	 * @param id
	 * @return
	 */
	@Select("select * from t_dynamic where * d_userid = #{id}")
	List<DynamicBean> listAllBlogByUserId(Integer id);
	
	/**
	 * 查询好友动态
	 * @param id
	 * @return
	 */
	@Select("select * from t_dynamic where d_userid in("
			+ "select a_myid from t_attention where a_myid in("
			+ "select a_idolid from t_attention where a_myid = #{id})"
			+ "and a_idolid = #{id})"
			+ "and order by d_time limit 0,5")
	List<DynamicBean> listFriendBlog(@Param("id") Integer id);
	
	/**
	 * 查询关注人动态
	 * @return
	 */
	@Select("select * from t_dynamic where d_userid in("
			+ "select a_idolid from t_attention where a_myid = #{id})"
			+ "and order by d_time limit 0,5")
	List<DynamicBean> listFollowsBlog(Integer id);
	
	/**
	 * 查询一组用于的所有动态
	 * 动态sql注解不太好实现？
	 * @param idSet
	 * @return
	 */
	List<DynamicBean> listBlogByUserIdSet(Set<Integer> idSet);
}

