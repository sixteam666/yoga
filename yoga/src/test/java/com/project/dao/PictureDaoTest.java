package com.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.bean.PictureBean;
import com.project.run.BaseTest;

public class PictureDaoTest extends BaseTest{
	@Autowired
	private IPictureDao pictureDao;
	
	/**
	 * 查找图片
	 */
	@Test
	public void findAll(){
		List<PictureBean> list = pictureDao.findAll("1",1);
		for (PictureBean pictureBean : list) {
			System.out.println(pictureBean);
		}
	}
	
	/**
	 * 添加图片
	 */
	@Test
	public void addPictrue(){
		List<PictureBean> list = new ArrayList<PictureBean>();
		PictureBean bean1 = new PictureBean();
		bean1.setP_imgname("瑜伽");
		bean1.setP_g_id("1");
		PictureBean bean2 = new PictureBean();
		bean2.setP_imgname("瑜伽2");
		bean2.setP_g_id("1");
		list.add(bean1);
		list.add(bean2);
		pictureDao.addPicture(list);
	}
	
	/**
	 * 删除图片
	 */
	@Test
	public void deletePic(){
		int number = pictureDao.deletePicture(1);
		System.out.println(number);
	}

}
