package com.project.service;

import java.util.List;

import com.project.bean.CommentBean;

public interface ICommentService {

	/**
	 * 查询教练的全部评价
	 * @param id
	 * @return
	 */
	List<CommentBean> listCommentById(String id);
	/**
	 * 查询全部评价数
	 * @param id
	 * @return
	 */
	Integer countCommentById(String id);
	/**
	 * 查询好评
	 * @param id
	 * @return
	 */
	List<CommentBean> listGreatCommentById(String id);
	/**
	 * 查询好评数量
	 * @param id
	 * @return
	 */
	Integer countGreatCommentById(String id);
	/**
	 * 查询中评
	 * @param id
	 * @return
	 */
	List<CommentBean> listNormalCommentById(String id);
	/**
	 * 查询中评数量
	 * @param id
	 * @return
	 */
	Integer countNormalCommentById(String id);
	
	/**
	 * 查询差评
	 * @param id
	 * @return
	 */
	List<CommentBean> listBadCommentById(String id);
	/**
	 * 查询差评数量
	 * @param id
	 * @return
	 */
	Integer countBadCommentById(String id);
	
	/**
	 * 添加评价
	 * @param comment
	 * @return
	 */
	Integer insert(CommentBean comment);
	
}
