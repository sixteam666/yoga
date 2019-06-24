package com.project.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.bean.LessonBean;
import com.project.run.BaseTest;

public class LessonDaoTest extends BaseTest{
	@Autowired
	private ILessonDao lessonDao;
	
	/**
	 * 查找课程
	 */
	@Test
	public void findAll(){
		LessonBean lessonBean = new LessonBean();
		lessonBean.setL_datetime(1);
		lessonBean.setL_weekday(1);
		lessonBean.setL_g_id("1");
		List<LessonBean> list = lessonDao.findLesson(lessonBean);
		for (LessonBean lessonBean2 : list) {
			System.out.println(lessonBean2);
		}
	}
	
	/**
	 * 教练课程安排
	 */
	@Test
	public void addLesson(){
		LessonBean lessonBean = new LessonBean();
		lessonBean.setL_descirbe("早课");
		lessonBean.setL_datetime(1);
		lessonBean.setL_weekday(1);
		lessonBean.setL_g_id("1");
		lessonDao.addLesson(lessonBean);
	}
	
	/**
	 * 删除课程
	 */
	@Test
	public void deleteLesson(){
		int number = lessonDao.deleteLesson(2);
		System.out.println(number);
	}
	

}
