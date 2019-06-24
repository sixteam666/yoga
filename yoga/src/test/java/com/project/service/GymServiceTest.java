package com.project.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.project.bean.GymBean;
import com.project.run.BaseTest;


public class GymServiceTest extends BaseTest{
	@Autowired
	@Qualifier("gymService")
	private IGymService gymService;
	
	@Test
	public void register(){
		
	}
	
	@Test
	public void login(){
		
	}
	
	@Test
	public void updatePassword(){
		
	}
	
	/**
	 * 查找所有场馆
	 */
	@Test
	public void findAllGym(){
		List<GymBean> list = gymService.findAllGym();
		for (GymBean gymBean : list) {
			System.out.println(gymBean);
		}
	}
	
	@Test
	public void findGymById(){
		System.out.println(gymService.findGymById("1"));
	}
	
	@Test
	public void updateMessage(){
		GymBean bean = new GymBean();
		bean.setG_address("孵化园");
		bean.setG_id("1");
		int num = gymService.updateMessage(bean);
		System.out.println(num);
	}
	
	@Test
	public void findMyCoach(){
		
	}
	
	@Test
	public void updateCoachBean(){
		
	}
	
	@Test
	public void submitSigingApplication(){
		
	}
	
	
	@Test
	public void agreeSigingApplication(){
		
	}
	
	@Test
	public void addPictrue(){
		
	}
	
	@Test
	public void findAllPic(){
		
	}
	
	@Test
	public void deletePicture(){
		
	}
	
	
	@Test
	public void addLesson(){
		
	}
	
	@Test
	public void findLesson(){
		
	}
}
