package com.project.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.CoachBean;
import com.project.bean.DynamicBean;
import com.project.bean.GymBean;
import com.project.bean.StudentBean;
import com.project.dao.IBlogDao;
import com.project.dao.IFollowDao;
import com.project.service.IBlogService;

@Service
public class BlogServiceImpl implements IBlogService {

	@Autowired
	IBlogDao blogDao;
	@Autowired
	IFollowDao followDao;

	@Override
	public List<DynamicBean> listFollowDynamic(String id) {
		List<DynamicBean> listFollowsBlog = blogDao.listFollowsBlog(id);
		for (DynamicBean dynamic : listFollowsBlog) {
			//关注者的动态代表所有人均已关注
			dynamic.setFollow(true);
		}
		return listFollowsBlog;
	}

	@Override
	public List<StudentBean> listFollowStudent(String id) {
		return followDao.listFollowingStudent(id);
	}

	@Override
	public List<CoachBean> listFollowCoach(String id) {
		return followDao.listFollowCoach(id);
	}

	@Override
	public List<GymBean> listFollowGym(String id) {
		return followDao.listFollowGym(id);
	}

	@Override
	public List<StudentBean> listStudentFans(String id) {
		return followDao.listFollowingStudent(id);
	}

	@Override
	public List<CoachBean> listCoachFans(String id) {
		return followDao.listFollowingCoach(id);
	}

	@Override
	public Integer countFollow(String id) {
		return followDao.countFollow(id);
	}

	@Override
	public Integer countFollowing(String id) {
		return followDao.countFollowing(id);
	}

	@Override
	public List<DynamicBean> listDynamicsById(String id) {
		return blogDao.listAllBlogByUserId(id);
	}

	@Override
	public List<DynamicBean> listFriendDynamic(String id) {
		List<DynamicBean> listFriendBlog = blogDao.listFriendBlog(id);
		for (DynamicBean dynamic : listFriendBlog) {
			dynamic.setFollow(true);
		}
		return listFriendBlog;
	}

	@Override
	public List<DynamicBean> listAllDynamics() {
		//String currentUserId = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		String currentUserId = "1";
		List<DynamicBean> dynamicList = blogDao.listAllBlog();
		for (DynamicBean dynamic : dynamicList) {
			String userid = dynamic.getD_userid();
			Integer follow = followDao.isFollow(currentUserId, userid);
			if(follow == 1) {
				dynamic.setFollow(true);
			}
		}
		return dynamicList;
	}

	@Override
	public Integer insert(DynamicBean dynamic) {
		return blogDao.insert(dynamic);
	}

	@Override
	public Integer delete(Integer id) {
		return blogDao.delete(id);
	}
	
}
