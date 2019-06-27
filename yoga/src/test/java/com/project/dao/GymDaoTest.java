package com.project.dao;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.bean.GymBean;
import com.project.run.BaseTest;


public class GymDaoTest extends BaseTest{
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
	public void addMessage(){
		GymBean gymBean =new GymBean();
		gymBean.setG_id("1");
		gymBean.setG_address("高新区");
		gymDao.updateMessage(gymBean);
		
	}
	
	/**
	 * 添加一个会馆(注册)
	 */
	@Test
	public void addGym() {
		GymBean gym = new GymBean();
		gym.setG_id(UUID.randomUUID().toString());
		gym.setG_password("1234567");
		gym.setG_email("752998419@qq.com");
		gym.setG_phone("18515779663");
		gym.setG_qq("752998418");
		gymDao.addGym(gym);

	}

	/**
	 * 修改密码
	 */
	@Test
	public void updatePassword() {
		String g_id = "620496a4-8401-4e66-a8c8-56fd4834b84f";
		String g_password = "xg123456";
		gymDao.updatePassowrd(g_id, g_password);

	}

	/**
	 * 通过邮箱或电话号查找场馆（登录）
	 */
	@Test
	public void findGymByEmailOrPhone() {
		// GymBean gymBean = gymDao.findGymByEmailOrPhone("752998419@qq.com");
		GymBean gymBean = gymDao.findGymByEmailOrPhone("18515779663");
		System.out.println(gymBean);
	}
	
	
	
}
