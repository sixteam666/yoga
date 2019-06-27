package com.project.service.impl;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.bean.CoachBean;
import com.project.bean.DynamicBean;
import com.project.bean.GymBean;
import com.project.bean.RequestBean;
import com.project.bean.StudentBean;
import com.project.dao.IBankCardDao;
import com.project.dao.IBlogDao;
import com.project.dao.ICoachDao;
import com.project.dao.IFollowDao;
import com.project.dao.IGymDao;
import com.project.dao.IRequestDao;
import com.project.dao.IStudentDao;
import com.project.service.ICoachService;
@Service
public class CoachServiceImpl implements ICoachService {
	@Autowired
	private ICoachDao dao;
	@Autowired
	private IBlogDao blogDao;
	@Autowired
	private IFollowDao followDao;
	@Autowired
	private IGymDao gymDao;
	@Autowired
	private IStudentDao stuDao;
	//操作申请表的接口
	@Autowired
	private IRequestDao reDao;
	@Autowired
	private IBankCardDao bankDao;
	
	@Override
	public Boolean register(CoachBean coach) {
		
		//判断是否存在账号
		Object obj = dao.findCoachByName(coach.getC_name());
		if (obj==null) {
			int row = dao.addCoach(coach);
			if (row>0)return true;
		}
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
	public boolean updateCoachDetailInfo(CoachBean coach) {
		return dao.updateCoachDetailInfo(coach) == 1;
	}

	@Override
	public CoachBean getCoachById(String id) {
		return dao.getCoachById(id);
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
	public String addRequest(String r_reqid, String r_resid) {
		int row = 0;
		//先查询两个对象之间是否有申请关系
		Object obj = dao.findIsRequest(r_reqid, r_resid);
		if (obj == null) {
			row = reDao.addRequest(r_reqid, r_resid);
		}else{
			RequestBean rb = (RequestBean) obj;
			//已有申请关系
			if (rb.getR_state()==0) return "isRequest";
		}
		//申请成功
		if(row>0)return "add";
		//申请失败
		return "false";
	}

	@Override
	public Boolean updateRequest(String r_reqid, String r_resid,int r_state) {
		int row = reDao.updateRequestState(r_reqid, r_resid,r_state);
		if(row>0)return true;
		return false;
	}

	@Override
	public CoachBean showCoachDetailInfo(Integer id) {
		return null;
	}

	@Override
	public Boolean updatePassword(String id, String newPassword) {
		return dao.updatePassword(newPassword, id) == 1;
	}

	/**
	 * 事务管理为设置成功
	 */
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public Boolean updateMoney(String id, double money, Integer cardId) {
		//查询出钱包余额
		Double balance = dao.getMoney(id);
		if(balance < money) {
			System.out.println(id+"的余额不足还想提现");
			return false;
		}
		Integer subtractMoney = dao.updateMoney(id, money);
		Integer addMoney = bankDao.updateBankCard(cardId,money);
		return subtractMoney == 1 && addMoney == 1;
	}

	@Override
	public List<StudentBean> listMyStudent(String id) {
		return dao.listStudentByCoachId(id);
	}

	
	//=================关注功能开始了=====================
	@Override
	public List<DynamicBean> listFollowDynamic(String id) {
		return blogDao.listAllBlogByUserId(id);
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
		return blogDao.listFriendBlog(id);
	}

	@Override
	public CoachBean getPersonalInfo(String id) {
		CoachBean personalInfo = dao.getPersonalInfo(id);
		return personalInfo;
	}

	@Override
	public void updatePersonalInfo(CoachBean coach) {
		dao.updateCoachPersonalInfo(coach);
	}

	@Override
	public void updateAuthentication(CoachBean coach) {
		dao.updateAuthentication(coach);
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				dao.updateAuthenticationSuccess(coach);
			}
		}, 120000);
	}

	@Override
	public CoachBean getLessonInfo(String id) {
		return dao.getLessonInfo(id);
	}

	@Override
	public void updateLessonInfo(CoachBean coach) {
		dao.updateCoachLessonInfo(coach);
	}

	@Override
	public Double getMoney(String id) {
		return dao.getMoney(id);
	}

	@Override
	public CoachBean showToOtherUser(String currentUserId, String coachId, Integer type) {
		CoachBean c = dao.getCoachById(coachId);
		Integer privacy = c.getC_privacy();
		
		/**
		 * 给别人展示那些信息
		 * 昵称，手机号，QQ号，地址，流派
		 */
		
		if(privacy == 2) {	//如果教练信息完全公开，则直接返回
			return c;
		}
		if(privacy == 1) {	//如果教练信息仅对好友公开
			//判断两者是否为好友关系
			Integer follow = followDao.isFollow(currentUserId, coachId);
			Integer follow2 = followDao.isFollow(coachId, currentUserId);
			if(follow == 1 && follow2 == 1){
				//两者为好友，则将所有信息返回
				return c;
			}
			if(type == 2) {
				//判断是否与我签约
				
			}
			if(type == 0) {
				//判断是否是我的学员
			}
		}
		//如果是场馆或学员，则默认开放手机号，方便其约私教或与我签约
		if(type == 0 || type == 2) {
			c.setC_qq("****");
			return c;
		}
		//如果教练信息保密
		c.setC_phone("****");
		c.setC_qq("****");
		return c;
	}

}
