package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.bean.CoachBean;
import com.project.bean.OrderBean;
import com.project.bean.StudentBean;

public interface IStudentDao {
      //	添加学员
	@Insert("insert into t_student(s_id,s_name,s_password,s_phone)"
			+"values(#{s_id},#{s_name},#{s_password},#{s_phone}) ")
	public int addStudent(StudentBean student);
	
	//  查询全部学员
	@Select("select * from t_student ")
	public List<StudentBean> findAllStudent();
	
	//      根据id查询学员
	@Select("select *from t_student where s_id = #{id} ")
	public StudentBean findStudentbyId(String id);
	
	//  根据用户名或者电话查询学员
	@Select("select *from t_student where s_name = #{name} or s_phone=#{name}")
	public StudentBean findStudentbyName(String name);
	
	//      更新学员个人信息
	@Update("update t_student set s_privacy=#{s_privacy}, s_nickname=#{s_nickname}"
			+",s_headimg=#{s_headimg},s_address=#{s_address} where s_id=#{s_id}")
	public int updateStudent(StudentBean stu);
	//      修改密码
	@Update("UPDATE t_student SET s_password=#{pwd} WHERE s_id=#{id}")
	public int updatePassowrd(@Param("id") String id, @Param("pwd") String pwd );
	
	//      更新手机
	@Update("UPDATE t_student SET s_phone=#{phone} WHERE s_id=#{id}")
	public int updatePhone(@Param("id") String id, @Param("phone") String phone );
	
	//      充值金额
	@Update("UPDATE t_student SET s_money=s_money+#{money} WHERE s_id=#{id}")
	public int addMoney(@Param("id") String id, @Param("money") Double money );
	
	//      消费扣款
	@Update("UPDATE t_student SET s_money=s_money-#{money} WHERE s_id=#{id}")
	public int subMoney(@Param("id") String id, @Param("money") Double money );
	
	
	
	//  根据学员id查询教练
	@Select(" SELECT * from t_coach WHERE c_id = ( SELECT l_c_id from t_lesson where l_id = (SELECT o_l_id from  t_order where o_s_id = #{id})) ")
	public CoachBean findCoachbyStudentId(String id);
	
	//    生成订单
	@Insert("insert into t_order VALUES (null,#{o_status},#{o_time},#{o_s_id},#{o_l_id},#{o_price})")
	public  int addorder(OrderBean order);
	
	
	//    查询订单
	@Select("select * from t_order where o_s_id = #{id}")
	public List<OrderBean> findorderbyid(String id);
	
	//    修改订单状态(包括确认订单以及取消订单)
	
	@Update("update t_order set o_status =#{status} where o_id = #{id}")
	public int updateorder(String id,int status);
	
	//     通过地图查询学生员
	

}
