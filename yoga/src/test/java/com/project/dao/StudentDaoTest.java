package com.project.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.bean.CoachBean;
import com.project.bean.OrderBean;
import com.project.bean.StudentBean;
import com.project.run.BaseTest;

public class StudentDaoTest extends BaseTest {
	@Autowired
	private IStudentDao dao;
	@Test
	public void testaddstudent(){
		StudentBean stu = new StudentBean();
		stu.setS_id("s003");
		stu.setS_name("xiaowang");
		stu.setS_password("66666");
		stu.setS_phone("1334567890");
		int result = dao.addStudent(stu);
		System.out.println(result);
	};
	
	@Test
	public void testfindStudentbyId(){
		String id = "s001";
		StudentBean stu = dao.findStudentbyId(id);
		System.out.println(stu);
	}
	
	@Test
	public void testfindallstudent(){
		List<StudentBean> list = dao.findAllStudent();
		for (StudentBean studentBean : list) {
			System.out.println(studentBean);
		}
	}
	
	@Test
	public void testfindStudentbyName(){
		String name = "";
		StudentBean stu = dao.findStudentbyName(name);
		System.out.println(stu);
}
	
	@Test
	public void testupdateStudent(){
		StudentBean stu = new StudentBean() ;
		stu.setS_address("武侯区");
		stu.setS_id("s001");
		stu.setS_nickname("重地通话");
		int result = dao.updateStudent(stu);
		System.out.println(result);
	}
	
	@Test
	public void testresetpassword(){
		String  id = "s001";
		String  pwd = "a314184417";
		dao.updatePassowrd(id, pwd);
	}
	
	@Test
	public void testaddmoney(){
		double money = 100.00;
		String id = "s001";
		dao.addMoney(id, money);
	}
	
	@Test
	public void testsubmoney(){
		double money = 20;
		String id = "s001";
		dao.subMoney(id, money);
	}
	
	@Test 
	public void testfindcoach(){
		String id ="s001";
		List<CoachBean> coach = dao.findCoachbyStudentId(id);
		System.out.println(coach);
	}
	
	
	@Test
	public void testaddorder(){
		OrderBean order = new OrderBean();
		order.setO_l_id(1);
		order.setO_price(8989);
		order.setO_s_id("s001");
		order.setO_status(1);
		order.setO_time("20130714");
		int result = dao.addorder(order);
		System.err.println(result);
	} 
     	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
