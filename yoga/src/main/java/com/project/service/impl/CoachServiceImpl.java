package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.CoachBean;
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

}
