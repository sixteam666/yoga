package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.CoachBean;
import com.project.bean.GymBean;
import com.project.bean.LessonBean;
import com.project.bean.PictureBean;
import com.project.bean.RequestBean;
import com.project.dao.ICoachDao;
import com.project.dao.IGymDao;
import com.project.dao.ILessonDao;
import com.project.dao.IPictureDao;
import com.project.dao.IRequestDao;
import com.project.service.IGymService;

@Service(value = "gymService")
public class GymServiceImpl implements IGymService {
	@Autowired
	private IGymDao gymDao;

	@Autowired
	private ICoachDao coachDao;
	
	@Autowired
	private IRequestDao requestDao;
	
	@Autowired
	private IPictureDao pictureDao;
	
	@Autowired
	private ILessonDao lessonDao;
	

	@Override
	public int register(GymBean gym) {
		return gymDao.addGym(gym);
	}

	@Override
	public GymBean login(String arg) {
		return gymDao.findGymByEmailOrPhone(arg);
	}

	@Override
	public int updatePassword(String id, String pwd) {
		return gymDao.updatePassowrd(id, pwd);
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
	public List<CoachBean> findMyCoach(String g_id) {
		List<CoachBean> list = coachDao.findCoachByGymId(g_id);
		return list;
	}

	@Override
	public int updateCoachBean(String g_id, String c_id) {
		int number = coachDao.updateCoachGymId(g_id, c_id);
		return number;
	}

	@Override
	public int submitSigingApplication(String g_id, String c_id) {
		return requestDao.addRequest(g_id, c_id);
	}

	@Override
	public int agreeSigingApplication(String g_id, String c_id, int state) {
		return requestDao.updateRequestState(c_id, g_id, state);
	}
	
	

	@Override
	public int addPictrue(List<PictureBean> list) {
		int number = pictureDao.addPicture(list);
		return number;
	}

	@Override
	public List<PictureBean> findAllPic(String gymId,int type) {
		List<PictureBean> list = pictureDao.findAll(gymId,type);
		return list;
	}
	
	@Override
	public int updatePicture(PictureBean bean){
		int number = pictureDao.updatePicture(bean);
		return number;
	}

	@Override
	public int deletePicture(int id) {
		int number = pictureDao.deletePicture(id);
		return number;
	}

	@Override
	public List<LessonBean> findLesson(LessonBean lessonBean) {
		List<LessonBean> list = lessonDao.findLesson(lessonBean);
		return list;
	}
	
	@Override
	public int addLesson(LessonBean lessonBean) {
		int number = lessonDao.addLesson(lessonBean);
		return number;
	}

	@Override
	public int deleteLesson(int id) {
		int number = lessonDao.deleteLesson(id);
		return number;
	}

	@Override
	public List<CoachBean> findCoaByNameOrPho(String g_id, String nameOrPho) {
		CoachBean coach = new CoachBean();
		coach.setC_g_id(g_id);
		List<CoachBean> list = null;
		if(!nameOrPho.equals("") && nameOrPho!=null){
			coach.setC_nickname(nameOrPho);
			list = coachDao.findCoachByNick(coach);
			if (list.isEmpty()) {
				CoachBean bean = coachDao.findCoachByPhone(nameOrPho);
				list.add(bean);
			}
		}
		
		return list;
	}

	@Override
	public List<CoachBean> findCoachByMyResponse(String g_id) {
		return gymDao.findCoachByMyResponse(g_id);
	}

	@Override
	public List<CoachBean> findCoachByMyRequest(String g_id) {
		return gymDao.findCoachByMyRequest(g_id);
	}
}
