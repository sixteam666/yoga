package com.project.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.project.bean.DPictureBean;
import com.project.bean.DynamicBean;

public interface IBlogDao {
	
	/**
	 * 添加动态
	 * @param dynamic
	 */
	@Insert("insert into "
			+ "t_dynamic(d_content,d_time,d_userid,d_img,d_headimg,d_nickname,d_type) "
			+ "values(#{d_content},#{d_time},#{d_userid},#{d_img},#{d_headimg},"
			+ "#{d_nickname},#{d_type})")
	@Options(useGeneratedKeys=true, keyProperty="d_id", keyColumn="d_id")
	Integer insert(DynamicBean dynamic);
	
	/**
	 * 删除动态
	 * @param id
	 * @return
	 */
	@Delete("delete from t_dynamic where d_id = #{id}")
	Integer delete(Integer id);
	/**
	 * 删除动态图片
	 * @param dynamicId
	 * @return
	 */
	@Delete("delete from t_dpicture where dp_d_id = #{id}")
	Integer deleteDynamicImages(Integer dynamicId);

	/**
	 * 查询所有动态
	 * @return
	 */
	@Select("select * from t_dynamic order by d_time desc")
	@Results({
		@Result(id = true, property = "d_id", column = "d_id"),
		@Result(property = "d_content", column = "d_content"),
		@Result(property = "d_time", column = "d_time"),
		@Result(property = "d_userid", column = "d_userid"),
		@Result(property = "d_headimg", column = "d_headimg"),
		@Result(property = "d_nickname", column = "d_nickname"),
		@Result(property = "d_type", column = "d_type"),
		@Result(property = "pictures", column = "d_id", 
			many = @Many(select = "com.project.dao.IDpictureDao.findAll"))
	})
	List<DynamicBean> listAllBlog();
	
	/**
	 * 查询某一用户所有动态
	 * @param id
	 * @return
	 */
	@Select("select * from t_dynamic where d_userid = #{id} order by d_time desc")
	@Results({
		@Result(id = true, property = "d_id", column = "d_id"),
		@Result(property = "d_content", column = "d_content"),
		@Result(property = "d_time", column = "d_time"),
		@Result(property = "d_userid", column = "d_userid"),
		@Result(property = "d_headimg", column = "d_headimg"),
		@Result(property = "d_nickname", column = "d_nickname"),
		@Result(property = "d_type", column = "d_type"),
		@Result(property = "pictures", column = "d_id", 
			many = @Many(select = "com.project.dao.IDpictureDao.findAll"))
	})
	List<DynamicBean> listAllBlogByUserId(String id);
	
	/**
	 * 查询好友动态
	 * @param id
	 * @return
	 */
	@Select("select * from t_dynamic where d_userid in("
			+ "select a_myid from t_attention where a_myid in("
			+ "select a_idolid from t_attention where a_myid = #{id})"
			+ "and a_idolid = #{id})"
			+ "order by d_time desc limit 0,5")
	@Results({
		@Result(id = true, property = "d_id", column = "d_id"),
		@Result(property = "d_content", column = "d_content"),
		@Result(property = "d_time", column = "d_time"),
		@Result(property = "d_userid", column = "d_userid"),
		@Result(property = "d_headimg", column = "d_headimg"),
		@Result(property = "d_nickname", column = "d_nickname"),
		@Result(property = "d_type", column = "d_type"),
		@Result(property = "pictures", column = "d_id", 
			many = @Many(select = "com.project.dao.IDpictureDao.findAll"))
	})
	List<DynamicBean> listFriendBlog(@Param("id") String id);
	
	/**
	 * 查询关注人动态
	 * @return
	 */
	@Select("select * from t_dynamic where d_userid in("
			+ "select a_idolid from t_attention where a_myid = #{id})"
			+ " order by d_time desc limit 0,5")
	@Results({
		@Result(id = true, property = "d_id", column = "d_id"),
		@Result(property = "d_content", column = "d_content"),
		@Result(property = "d_time", column = "d_time"),
		@Result(property = "d_userid", column = "d_userid"),
		@Result(property = "d_headimg", column = "d_headimg"),
		@Result(property = "d_nickname", column = "d_nickname"),
		@Result(property = "d_type", column = "d_type"),
		@Result(property = "pictures", column = "d_id", 
			many = @Many(select = "com.project.dao.IDpictureDao.findAll"))
	})
	List<DynamicBean> listFollowsBlog(String id);
	
	/**
	 * 查询一组用于的所有动态
	 * 动态sql注解不太好实现？
	 * @param idSet
	 * @return
	 */
	List<DynamicBean> listBlogByUserIdSet(Set<String> idSet);
}

