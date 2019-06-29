package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bean.CommentBean;
import com.project.service.ICommentService;

@RestController
@RequestMapping("comment")
public class CommentController {
	
	@Autowired
	private ICommentService commentService;
	
	/**
	 * 查询所有评价
	 * @param id
	 * @return
	 */
	@RequestMapping("allComment.do")
	public List<CommentBean> findAllComment(String id) {
		return commentService.listCommentById(id);
	}
	
	/**
	 * 查询好评
	 * @param id
	 * @return
	 */
	@RequestMapping("greatComment.do")
	public List<CommentBean> findGreatComment(String id) {
		return commentService.listGreatCommentById(id);
	}
	
	/**
	 * 查询中评
	 * @param id
	 * @return
	 */
	@RequestMapping("normalComment.do")
	public List<CommentBean> findNormalComment(String id) {
		return commentService.listNormalCommentById(id);
	}
	
	/**
	 * 查询差评
	 * @param id
	 * @return
	 */
	@RequestMapping("badComment.do")
	public List<CommentBean> findBadComment(String id) {
		return commentService.listBadCommentById(id);
	}
	
	/**
	 * 评价数
	 * @return
	 */
	@RequestMapping("all.do")
	public Integer countComment(String id) {
		return commentService.countCommentById(id);
	}
	/**
	 * 好评数
	 * @return
	 */
	@RequestMapping("great.do")
	public Integer countGreatComment(String id) {
		return commentService.countGreatCommentById(id);
	}
	/**
	 * 中评数
	 * @return
	 */
	@RequestMapping("normal.do")
	public Integer countNormalComment(String id) {
		return commentService.countNormalCommentById(id);
	}
	/**
	 * 差评数
	 * @return
	 */
	@RequestMapping("bad.do")
	public Integer countBadComent(String id) {
		return commentService.countBadCommentById(id);
	}
	
}
