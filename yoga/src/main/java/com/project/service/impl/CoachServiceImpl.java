package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.CoachBean;
import com.project.bean.StudentBean;
import com.project.dao.ICoachDao;
import com.project.service.ICoachService;
@Service
public class CoachServiceImpl implements ICoachService {
	@Autowired
	private ICoachDao dao;
	@Override
	public Boolean register(CoachBean coach) {
		
		int row = dao.addCoach(coach);
		if (row>0)return true;
		return false;
	}

	@Override
	public CoachBean login(String name) {
		CoachBean coach = null;
		Object obj = dao.findCoachByName(name);
		if (obj!=null) {
			coach = (CoachBean) obj;
		}
		return coach;
	}

	@Override
	public Boolean signGym(String g_id, String c_id) {
		int row = dao.updateCoachGymId(g_id, c_id);
		if (row>0)return true; 
		return false;
	}

	@Override
	public List<StudentBean> findStu(String c_id) {
		return null;
	}

	@Override
	public boolean updateCoachDetailInfo(CoachBean coach) {
		return dao.updateCoachDetailInfo(coach) == 1;
	}

	@Override
	public CoachBean showCoachDetailInfo(Integer id) {
		return dao.findCoachById(id);
	}

	

}
