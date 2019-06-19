package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.CoachBean;
import com.project.bean.GymBean;
import com.project.bean.LessonBean;
import com.project.bean.PictrueBean;
import com.project.dao.ICoachDao;
import com.project.dao.IGymDao;
import com.project.service.IGymService;

@Service(value="gymService")
public class GymServiceImpl implements IGymService {
	@Autowired
	private IGymDao gymDao;
	
	@Autowired
	private ICoachDao coachDao;

	@Override
	public int register(GymBean gym) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GymBean login(String arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePassword(String id, String pwd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GymBean> findAllGym() {
		List<GymBean> list = gymDao.findAllGym();
		return list;
	}

	@Override
	public GymBean findGymById(String id) {
		GymBean gymBean = gymDao.findGymById(id);
		return gymBean;
	}

	@Override
	public int updateMessage(GymBean gymBean) {
		int number = gymDao.updateMessage(gymBean);
		return number;
	}

	@Override
	public int addPictrue(List<PictrueBean> list) {
		int number = gymDao.addPictrue(list);
		return number;
	}

	@Override
	public int addLesson(LessonBean lessonBean) {
		int number = gymDao.addLesson(lessonBean);
		return number;
	}

	@Override
	public List<CoachBean> findMyCoach(String g_id) {
		List<CoachBean> list = coachDao.findCoachByGymId(g_id);
		return list;
	}

	@Override
<<<<<<< HEAD
	public int updateCoachBean(String g_id, String c_id) {
		// TODO Auto-generated method stub
		return 0;
=======
	public int endDeal(CoachBean coachBean) {
		int number = coachDao.updateCoach(coachBean);
		return number;
>>>>>>> branch 'master' of https://github.com/sixteam666/yoga.git
	}


}
