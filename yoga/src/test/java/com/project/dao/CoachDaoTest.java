package com.project.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.bean.CoachBean;
import com.project.run.BaseTest;

public class CoachDaoTest extends BaseTest {
	@Autowired
	private ICoachDao dao;
	@Test
	public void addCoach(){
		
		CoachBean coach = new CoachBean();
		coach.setC_name("admin");
		coach.setC_password("123456");
		dao.addCoach(coach);
	}
	@Test
	public void login(){
		
		CoachBean coach =null;
		coach = dao.findCoachByName("root");
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
}
