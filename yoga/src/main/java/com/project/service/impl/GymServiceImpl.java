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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GymBean findGymById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMessage(GymBean gymBean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addPictrue(List<PictrueBean> lists) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addLesson(LessonBean lessonBean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CoachBean> findMyCoach(String g_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int endDeal(CoachBean coachBean) {
		// TODO Auto-generated method stub
		return 0;
	}

}
