package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.CoachBean;
import com.project.bean.LessonBean;
import com.project.bean.OrderBean;
import com.project.bean.StudentBean;
import com.project.bean.WordsBean;
import com.project.dao.CoachDaoTest;
import com.project.dao.ICoachDao;
import com.project.dao.IFollowDao;
import com.project.dao.ILessonDao;
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
		StudentBean stu =  dao.findStudentbyId(id);
		return stu;
	}

	@Override
	public CoachBean findCoachbyStudentId(String id) {
		CoachBean coach = dao.findCoachbyStudentId(id);
		return coach;
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
	public List<StudentBean> findFans(String id) {
		List<StudentBean> list = followDao.listFollowingStudent(id);
		for (StudentBean studentBean : list) {
			List<StudentBean> list2 = followDao.listFollowingStudent(studentBean.getS_id());
			for (StudentBean studentBean2 : list2) {
				if (studentBean2.getS_id().equals(studentBean.getS_id())) {
					list.remove(studentBean2);
				}
			}
		}
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
}
