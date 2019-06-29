package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.CommentBean;
import com.project.dao.ICommentDao;
import com.project.service.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private ICommentDao commentDao;
	
	@Override
	public List<CommentBean> listCommentById(String id) {
		return commentDao.listCommentById(id);
	}

	@Override
	public Integer countCommentById(String id) {
		return commentDao.countCommentById(id);
	}

	@Override
	public List<CommentBean> listGreatCommentById(String id) {
		return commentDao.listGreatCommentById(id);
	}

	@Override
	public Integer countGreatCommentById(String id) {
		return commentDao.countGreatCommentById(id);
	}

	@Override
	public List<CommentBean> listNormalCommentById(String id) {
		return commentDao.listNormalCommentById(id);
	}

	@Override
	public Integer countNormalCommentById(String id) {
		return commentDao.countNormalCommentById(id);
	}

	@Override
	public List<CommentBean> listBadCommentById(String id) {
		return commentDao.listBadCommentById(id);
	}

	@Override
	public Integer countBadCommentById(String id) {
		return commentDao.countBadCommentById(id);
	}

	@Override
	public Integer insert(CommentBean comment) {
		return commentDao.insert(comment);
	}

}
