package com.project.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.bean.CoachBean;
import com.project.run.BaseTest;

public class CoachDaoTest extends BaseTest {
	@Autowired
	private ICoachDao dao;
	@Autowired
	private IRequestDao reDao;
	@Autowired
	private IWordDao wd;
	//测试添加教练
	@Test
	public void addCoach(){
		
		CoachBean coach = new CoachBean();
		coach.setC_name("root");
		coach.setC_password("123456");
		coach.setC_phone("12344455566");
		dao.addCoach(coach);
	}
	//测试教练登录
	@Test
	public void login(){
		
		CoachBean coach =null;
		coach = dao.findCoachByName("12344455566");
		System.out.println(coach);
	}
	
	@Test
	public void testUpdateCoachDetailInfo() {
		CoachBean coach = new CoachBean();
		coach.setC_id("aaa");
		coach.setC_nickname("Coach Zhang");
		int res = dao.updateCoachDetailInfo(coach);
		System.out.println(res == 1);
	}
	
	@Test
	public void testGetCoachById() {
		String id = "aaa";
		CoachBean coach = dao.getCoachById(id);
		System.out.println(coach);
	}
	
	@Test
	public void testUpdateMoney() {
		String id = "aaa";
		Double money = 10.00005;
		System.out.println(dao.updateMoney(id, money));
	}
	
	@Test
	public void testGetMoney() {
		String id = "aaa";
		System.out.println(dao.getMoney(id));
	}
	
	@Test
	public void testListStudentByCoachId() {
		String id = "aaa";
		System.out.println(dao.listStudentByCoachId(id));
	}

	//测试申请签约场馆
	@Test
	public void addRequest(){
		reDao.addRequest("a", "b");
	}
	//测试修改申请状态
	@Test
	public void updateRequest(){
		int row = reDao.updateRequestState("a", "b", 1);
		System.out.println(row);
	}
	//通过昵称查找教练
	@Test
	public void findCoachByNick(){
		CoachBean bean = new CoachBean();
		bean.setC_g_id("1");
		bean.setC_nickname("");
		List<CoachBean> list = dao.findCoachByNick(bean);
		System.out.println(list.size());
	}
	//通过手机号查找教练
	@Test
	public void findCoachByPhone(){
		CoachBean coach = dao.findCoachByPhone("13345674567");
		System.out.println(coach);
	}
	

}
