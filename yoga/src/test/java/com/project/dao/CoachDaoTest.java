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
		coach.setC_name("root");
		coach.setC_password("123456");
		coach.setC_phone("12344455566");
		dao.addCoach(coach);
	}
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
}
