package com.project.service;

import java.util.List;

import com.project.bean.CoachBean;
import com.project.bean.LessonBean;
import com.project.bean.OrderBean;
import com.project.bean.POrderBean;
import com.project.bean.RequestBean;
import com.project.bean.StudentBean;
import com.project.bean.WordsBean;


public interface IStudentService{
	
	/**
	 * 注册
	 * @param Student StudentBean 对象
	 * @return true:注册成功，false:注册失败
	 */
	public boolean regist(StudentBean student);
	
	/**
	 * 查询全部学员
	 * @return StudentBean对象集合
	 */
	public List<StudentBean> findAllStudent();
	
	
	/**
	 * 通过传入学员id查询学员
	 * @param id
	 * @return StudentBean对象
	 */
	public StudentBean findStudentbyId(String id);
	
	
	/**
	 * 通过传入用户名查询学员
	 * @param id
	 * @return StudentBean对象
	 */
	public StudentBean findStudentbyName(String name);
	
	
	/**
	 * 登陆
	 * @param arg 邮箱或电话号
	 * @return StudentBean 对象
	 */
	public StudentBean login (String arg);
	
	
	/**
	 * 更新个人信息
	 * @param student
	 * @return true:修改成功，false:修改失败
	 */
	public boolean update (StudentBean student);
	
	/**
	 * 修改密码(即所谓的找回密码)
	 * @param pwd id
	 * @return true:修改成功，false:修改失败
	 */
	public boolean resetpassword(String pwd,String id);

	
	/**
	 * 手机换绑
	 * @param pwd id
	 * @return true:修改成功，false:修改失败
	 */
	public boolean resetphone(String phone,String id);

	
	/**
	 * 通过学员ID查询学员的教练
	 * @param id
	 * @return CoachBean
	 */
	public List<String> findCoachidbyStudentId(String id);
	
	/**
	 * 充值到钱包
	 * @param id 学员的id
	 * @param money 充值的金额
	 * @return true:充值成功，false:充值失败
	 */
	public boolean recharge(String id ,double money);
	
	/**
	 * 消费扣款
	 * @param id 学员的id
	 * @param money 充值的金额
	 * @return true:充值成功，false:充值失败
	 */
	public boolean consume(String id ,double money);
	
	
	/**
	 * 创建订单
	 * @param  orderbean对象 
	 * @return true:订单创建成功，false:创建失败
	 */
	public boolean addorder(OrderBean order); 
	
	/**
	 * 通过订单id查询订单及相关信息
	 * @param orderid
	 * @return
	 */
	public OrderBean findorderbyid(int orderid);
	
	
	/**
	 * 通过私教订单id查询私教订单及相关信息
	 * @param orderid
	 * @return
	 */
	public POrderBean findPorderbyid(int porderid);
	
	
	/**
	 * 查询学生所有的订单
	 * @param id
	 * @return
	 */
	public List<OrderBean> listorderbystuid(String id);
	
	/**
	 * 修改订单状态
	 * @return
	 */
	public boolean updateorderstatus(String id,int status);
	
	
	
	/**
	 * 查询我的关注人数
	 * @param id
	 * @return
	 */
	public int countmyattention(String id);
	

	/**
	 * 查询我的粉丝人数
	 * @param id
	 * @return
	 */
	public int countmyfans(String id);
	
	
	/**
	 * 查询学生所有公共课程
	 * @param id
	 * @return
	 */
	public List<LessonBean> findcourse(String id);
	
	
	
	
	/**
	 * 查询私教订单
	 * @param id
	 * @return
	 */
	public List<POrderBean> listporder(String id);
	
	/**
	 * 查询我的学员粉丝
	 * @param id
	 * @return
	 */
	public List<StudentBean> findstuFans(String id);
	
	/**
	 * 查询我的留言
	 * @param id
	 * @return
	 */
	public List<WordsBean> findWords(String id);
	
	/**
	 * 留言
	 * @param id
	 * @return
	 */
	public int insertWords(WordsBean wordsBean);
	
	/**
	 * 添加关注
	 * @param id
	 * @return
	 */
	public boolean addFollow(String myid,String idolid);
	
	/**
	 * 查找所有教练
	 * @param id
	 * @return
	 */
	public List<CoachBean> findAllCoach();
	
	 /** 查询个人通知
	 * @param id
	 * @return
	 */
	public List<RequestBean> findallreq(String id);
	
	/**
	 * 根据id查询课程
	 * @param id
	 * @return
	 */
	public LessonBean findlessonbyid(int id);
	
	/**
	 * 增加通知
	 * @param myid
	 * @param itid
	 * @param status
	 * @return
	 */
	public int addRequeststu(String myid, String itid, String type,String date);
}
