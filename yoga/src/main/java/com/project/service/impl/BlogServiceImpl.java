package com.project.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.bean.CoachBean;
import com.project.bean.DPictureBean;
import com.project.bean.DynamicBean;
import com.project.bean.GymBean;
import com.project.bean.StudentBean;
import com.project.dao.IBlogDao;
import com.project.dao.IDpictureDao;
import com.project.dao.IFollowDao;
import com.project.service.IBlogService;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class BlogServiceImpl implements IBlogService {

	@Autowired
	IBlogDao blogDao;
	@Autowired
	IFollowDao followDao;
	@Autowired
	IDpictureDao dpictureDao;

	@Override
	public List<DynamicBean> listFollowDynamic(String id) {
		List<DynamicBean> listFollowsBlog = blogDao.listFollowsBlog(id);
		for (DynamicBean dynamic : listFollowsBlog) {
			//关注者的动态代表所有人均已关注
			dynamic.setFollow(1);
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
		List<StudentBean> followingStudent = followDao.listFollowingStudent(id);
		for (StudentBean s : followingStudent) {
			Integer follow = followDao.isFollow(id, s.getS_id());
			if(follow == 1) {
				s.setS_privacy(-1);
			}
		}
		return followingStudent;
	}

	@Override
	public List<CoachBean> listCoachFans(String id) {
		List<CoachBean> followingCoach = followDao.listFollowingCoach(id);
		for (CoachBean c : followingCoach) {
			Integer follow = followDao.isFollow(id, c.getC_id());
			if(follow == 1) {
				c.setC_privacy(-1);
			}
		}
		return followingCoach;
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
			dynamic.setFollow(1);
		}
		return listFriendBlog;
	}

	@Override
	public List<DynamicBean> listAllDynamics() {
		String currentUserId = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		List<DynamicBean> dynamicList = blogDao.listAllBlog();
		for (DynamicBean dynamic : dynamicList) {
			String userid = dynamic.getD_userid();
			//动态属于用户自己
			if(currentUserId.equals(userid)){
				dynamic.setFollow(2);
			} else {
				Integer follow = followDao.isFollow(currentUserId, userid);
				if(follow == 1) {
					dynamic.setFollow(1);
				}
			}
		}
		return dynamicList;
	}

	@Override
	public Integer insert(DynamicBean dynamic,List<DPictureBean> list) {
		int number = blogDao.insert(dynamic);
		
		if (!list.isEmpty()) {
			for (DPictureBean dPictureBean : list) {
				dPictureBean.setDp_d_id(dynamic.getD_id());
			}
			dpictureDao.addPicture(list);
		}
		
		return number;
	}

	@Override
	public boolean delete(Integer id) {
		Integer deleteDynamic = blogDao.delete(id);
		Integer deleteDynamicImages = blogDao.deleteDynamicImages(id);
		return deleteDynamic == 1 && deleteDynamicImages > 0 && deleteDynamicImages < 10;
	}
	
	public Integer addFollow(String followId) {
		String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		return followDao.insert(id, followId);
	}
	
	public Integer cancelFollow(String followId) {
		String id = (String) SecurityUtils.getSubject().getSession().getAttribute("id");
		return followDao.delete(id, followId);
	}

	@Override
	public Integer countFriends(String id) {
		return followDao.countFriends(id);
	}

	@Override
	public List<StudentBean> listStudentFriends(String id) {
		return followDao.listFriendStudent(id);
	}

	@Override
	public List<CoachBean> listCoachFriends(String id) {
		return followDao.listFriendCoach(id);
	}

	@Override
	public boolean isFollow(String fanId, String idolId) {
		return followDao.isFollow(fanId, idolId) == 1;
	}
	
}
