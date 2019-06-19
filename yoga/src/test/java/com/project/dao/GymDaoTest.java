package com.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.bean.CoachBean;
import com.project.bean.GymBean;
import com.project.bean.LessonBean;
import com.project.bean.PictrueBean;
import com.project.dao.ICoachDao;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GymDaoTest {
	@Autowired
	private IGymDao gymDao;
	
	/**
	 * 测试Junit
	 */
	@Test
	public void ById(){
		System.out.println("1");
	}
	
	/**
	 * 查看所有场馆
	 */
	@Test
	public void findAllGym(){
		List<GymBean> list = gymDao.findAllGym();
		for (GymBean gymBean : list) {
			System.out.println(gymBean);
		}
		
	}
	
	/**
	 * 通过id查看场馆
	 */
	@Test
	public void findGymById(){
		GymBean bean = gymDao.findGymById("1");
		System.out.println(bean);
	}
	
	/**
	 * 信息完善
	 */
	@Test
	public void overMessage(){
		GymBean gymBean =new GymBean();
		gymBean.setG_id("1");
		gymBean.setG_address("高新区");
		gymDao.updateMessage(gymBean);
		
	}
	
	/**
	 * 添加图片
	 */
	@Test
	public void addPictrue(){
		List<PictrueBean> list = new ArrayList<PictrueBean>();
		PictrueBean bean1 = new PictrueBean();
		bean1.setP_imgname("瑜伽");
		list.add(bean1);
		gymDao.addPictrue(list);
	}
	
	/**
	 * 教练课程安排
	 */
	@Test
	public void addLesson(){
		LessonBean lessonBean = new LessonBean();
		lessonBean.setL_descirbe("早课");
		gymDao.addLesson(lessonBean);
	}
	
}
