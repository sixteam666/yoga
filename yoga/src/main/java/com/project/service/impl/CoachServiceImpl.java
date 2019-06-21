package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.CoachBean;
import com.project.bean.GymBean;
import com.project.bean.StudentBean;
import com.project.dao.ICoachDao;
import com.project.dao.IGymDao;
import com.project.dao.IRequestDao;
import com.project.dao.IStudentDao;
import com.project.service.ICoachService;
@Service
public class CoachServiceImpl implements ICoachService {
	@Autowired
	private ICoachDao dao;
	@Autowired
	private IGymDao gymDao;
	@Autowired
	private IStudentDao stuDao;
	//操作申请表的接口
	@Autowired
	private IRequestDao reDao;
	
	@Override
	public Boolean register(CoachBean coach) {
		
		int row = dao.addCoach(coach);
		if (row>0)return true;
		return false;
	}

	@Override
	public CoachBean login(String name) {
		return dao.findCoachByName(name);
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

	@Override
	public List<GymBean> showAllGym() {
		return gymDao.findAllGym();
	}

	@Override
	public List<StudentBean> showAllStu() {
		//return stuDao.findAllStudent();
		return null;
	}

	@Override
	public Boolean addRequest(String r_reqid, String r_resid) {
		int row = reDao.addRequest(r_reqid, r_resid);
		if(row>0)return true;
		return false;
	}

	@Override
	public Boolean updateRequest(String r_reqid, String r_resid, int r_state) {
		int row = reDao.updateRequestState(r_reqid, r_resid, r_state);
		if(row>0)return true;
		return false;
	}
}
