package com.project.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.bean.CoachBean;
import com.project.dao.ICoachDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoachDaoTest {
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
}
