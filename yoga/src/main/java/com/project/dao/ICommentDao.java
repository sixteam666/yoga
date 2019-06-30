package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.project.bean.CommentBean;

public interface ICommentDao {
	
	/**
	 * 查询教练的全部评价
	 * @param id
	 * @return
	 */
	@Select("select * from t_comment where pl_c_id = #{id} order by pl_time desc")
	List<CommentBean> listCommentById(String id);
	/**
	 * 查询全部评价数
	 * @param id
	 * @return
	 */
	@Select("select count(1) from t_comment where pl_c_id = #{id}")
	Integer countCommentById(String id);
	/**
	 * 查询好评
	 * @param id
	 * @return
	 */
	@Select("select * from t_comment where pl_level = 0 and pl_c_id = #{id} order by pl_time desc")
	List<CommentBean> listGreatCommentById(String id);
	/**
	 * 查询好评数量
	 * @param id
	 * @return
	 */
	@Select("select count(1) from t_comment where pl_level = 0 and pl_c_id = #{id}")
	Integer countGreatCommentById(String id);
	/**
	 * 查询中评
	 * @param id
	 * @return
	 */
	@Select("select * from t_comment where pl_level = 1 and pl_c_id = #{id} order by pl_time desc")
	List<CommentBean> listNormalCommentById(String id);
	/**
	 * 查询中评数量
	 * @param id
	 * @return
	 */
	@Select("select count(1) from t_comment where pl_level = 1 and pl_c_id = #{id}")
	Integer countNormalCommentById(String id);
	
	/**
	 * 查询差评
	 * @param id
	 * @return
	 */
	@Select("select * from t_comment where pl_level = 2 and pl_c_id = #{id} order by pl_time desc")
	List<CommentBean> listBadCommentById(String id);
	/**
	 * 查询差评数量
	 * @param id
	 * @return
	 */
	@Select("select count(1) from t_comment where pl_level = 2 and pl_c_id = #{id}")
	Integer countBadCommentById(String id);
	
	/**
	 * 添加评价
	 * @param comment
	 * @return
	 */
	@Insert("insert into t_comment(pl_content,pl_level,pl_c_id,pl_s_id,pl_time) "
			+ "values(#{pl_content},#{pl_level},#{pl_c_id},#{pl_s_id},#{pl_time})")
	Integer insert(CommentBean comment);
	
}
