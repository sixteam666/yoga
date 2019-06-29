package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.CoachBean;
import com.project.bean.GymBean;
import com.project.bean.LessonBean;
import com.project.bean.OrderBean;
import com.project.bean.RequestBean;
import com.project.bean.StudentBean;
import com.project.bean.WordsBean;
import com.project.dao.ICoachDao;
import com.project.dao.IFollowDao;
import com.project.dao.IGymDao;
import com.project.dao.ILessonDao;
import com.project.dao.IRequestDao;
import com.project.dao.IStudentDao;
import com.project.dao.IWordDao;
import com.project.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService{

	@Autowired
	private IStudentDao dao;
	@Autowired
	private ILessonDao lessondao;
	@Autowired
	private ICoachDao CoachDao;
	@Autowired
	private IFollowDao followDao;
	@Autowired
	private IWordDao wordDao;
	@Autowired
	private IGymDao  Gymdao;
	@Autowired
	private IRequestDao requestdao;
	
	
	@Override
	public boolean regist(StudentBean student) {
		if (dao.findStudentbyName(student.getS_name()) != null) {
			return false;
		}
		int result = dao.addStudent(student);
		if (result != 1) {
			return false;
		} else
			return true;
	};

	@Override
	public StudentBean login(String arg) {
		StudentBean student = null;
		Object obj = dao.findStudentbyName(arg);
		if (obj!=null) {
			student = (StudentBean) obj;
		}
		return student;
	}

	@Override
	public boolean update(StudentBean student) {
		int result = dao.updateStudent(student);
		if(result>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean resetpassword(String pwd, String id) {
		int result = dao.updatePassowrd(id, pwd);
		if(result!=1){
			return false;
		}
		return true;
	}

	@Override
	public StudentBean findStudentbyId(String id) {
		StudentBean stu = dao.findStudentbyId(id);
		return stu;
	}

	@Override
	public List<CoachBean> findCoachbyStudentId(String id) {
		List<CoachBean> list = dao.findCoachbyStudentId(id);
		return list;
	}

	@Override
	public boolean resetphone(String phone, String id) {
		int result =dao.updatePhone(id, phone);
		if(result>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean recharge(String id, double money) {
		int result = dao.addMoney(id, money);
		if(result>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean consume(String id, double money) {
		int result = dao.subMoney(id, money);
		if(result>0){
			return true;
		}
		return false;
	}

	@Override
	public List<StudentBean> findAllStudent() {
		List<StudentBean> list = dao.findAllStudent();
		return list;
	}

	@Override
	public StudentBean findStudentbyName(String name) {
		StudentBean stu = dao.findStudentbyName(name);
		return stu;
	}

	@Override
	public boolean addorder(OrderBean order) {
		int result = dao.addorder(order);
		if(result!=1){
			return false;
		}
		return true;
	}

	@Override
	public List<OrderBean> findorderbyid(String id) {
		List<OrderBean> list = dao.findorderbyid(id);
		for (OrderBean orderBean : list) {
			int lessonid =orderBean.getO_l_id();
			LessonBean lesson= lessondao.findlessonbyid(lessonid);
			orderBean.setLessonname(lesson.getL_descirbe());
			String gymid = lesson.getL_g_id();
			GymBean gym= Gymdao.findGymById(gymid);
			orderBean.setGym(gym);
		}
		return list;
	}

	@Override
	public boolean updateorderstatus(String id, int status) {
			int result = dao.updateorder(id, status);
			if(result!=1){
				return false;
			}
			return true;
	}

	@Override
	public List<LessonBean> findcourse(String id) {
		 List<LessonBean> list =  lessondao.findlessonbystudentid(id);
		 for (LessonBean lessonBean : list) {
			String coach_id =lessonBean.getL_c_id();
			CoachBean coach = CoachDao.getCoachById(coach_id);
			lessonBean.setCoach(coach);
		}
		return list;
	}

	@Override
	public List<StudentBean> findstuFans(String id) {
		List<StudentBean> list = followDao.listFollowingStudent(id);
		return list;
	}

	@Override
	public List<WordsBean> findWords(String id) {
		List<WordsBean> list = wordDao.findWords(id);
		return list;
	}

	@Override
	public boolean addFollow(String myid, String idolid) {
		Integer num = followDao.insert(myid, idolid);
		return num>0?true:false;
	}

	@Override
	public int insertWords(WordsBean wordsBean) {
		wordDao.insertWords(wordsBean);
		return 0;
	}

	@Override
	public int countmyattention(String id) {
		int result = followDao.countFollow(id);
		return result;
	}

	@Override
	public int countmyfans(String id) {
		int result = followDao.countFollowing(id);
		return result;
	}

	@Override
	public List<CoachBean> findAllCoach() {
		return CoachDao.findAll();
	}
	
	@Override
	public List<RequestBean> findallreq(String id) {
		List<RequestBean> list = requestdao.listrequest(id);
		for (RequestBean requestBean : list) {
			String reqid = requestBean.getR_reqid();
			if (dao.findStudentbyId(reqid)!=null) {
				StudentBean stu = dao.findStudentbyId(reqid);
				requestBean.setReqid(stu.getS_id());
				requestBean.setReqname(stu.getS_nickname());
				requestBean.setHeadimg(stu.getS_headimg());
			}else if (CoachDao.getCoachById(reqid)!=null) {
				CoachBean coach = CoachDao.getCoachById(reqid);
				requestBean.setReqid(coach.getC_id());
				requestBean.setReqname(coach.getC_nickname());;
				requestBean.setHeadimg(coach.getC_headimg());
			}else {
				GymBean  gym  = Gymdao.findGymById(reqid);
				requestBean.setHeadimg(gym.getG_headimg());
				requestBean.setReqid(gym.getG_id());
				requestBean.setReqname(gym.getG_name());
			}{
				
			}
		} 
		
		return list;
	}

	@Override
	public int addRequeststu(String myid, String itid, String type,String date) {
		int Result  = requestdao.addRequeststu(myid, itid, type, date);
		return Result;
	}

	@Override
	public LessonBean findlessonbyid(int id) {
		 LessonBean findlessonbyid = lessondao.findlessonbyid(id);
		return findlessonbyid;
	}
}
