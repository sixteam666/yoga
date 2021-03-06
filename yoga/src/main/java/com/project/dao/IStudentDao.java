package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.bean.CoachBean;
import com.project.bean.OrderBean;
import com.project.bean.POrderBean;
import com.project.bean.StudentBean;
import com.project.bean.WordsBean;

public interface IStudentDao {
      //	添加学员
	@Insert("insert into t_student(s_id,s_name,s_password )"
			+"values(#{s_id},#{s_name},#{s_password}) ")
	public int addStudent(StudentBean student);
	
	//  查询全部学员
	@Select("select * from t_student ")
	public List<StudentBean> findAllStudent();
	
	//      根据id查询学员
	@Select("select *from t_student where s_id = #{id} ")
	public StudentBean findStudentbyId(String id);
	
	//  根据用户名或者电话查询学员
	@Select("select *from t_student where s_name = #{name} or s_phone = #{name}")
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
	@Select(" SELECT l_c_id from t_lesson where l_id IN (SELECT o_l_id from  t_order where o_s_id = #{id}) ")
	public List<String> findCoachidbyStudentId(String id);

	//  根据学员id查询私教教练
	@Select(" SELECT po_c_id from t_porder where po_s_id = #{id} ")
	public List<String> findpCoachidbyStudentId(String id);
	
	//    生成订单
	@Insert("insert into t_order (o_time,o_s_id,o_l_id,o_price,code) VALUES (#{o_time},#{o_s_id},#{o_l_id},#{o_price},#{code})")
	@Options(useGeneratedKeys = true,keyProperty= "o_id",keyColumn = "o_id")
	public  int addorder(OrderBean order);
	
	// 根据订单id查订单
	@Select("select * from t_order where o_id = #{id}")
	public OrderBean findorderbyid(int id);
	
	// 根据私教订单id查私教订单
		@Select("select * from t_porder where po_id = #{id}")
		public POrderBean findporderbyid(int id);
	
	//    查询学生所有订单
	@Select("select * from t_order where o_s_id = #{id}")
	public List<OrderBean> listorderbystuid(String id);
	
	//  查询学生所有私教订单
	@Select("select * from t_porder where po_s_id = #{id}")
	public List<POrderBean> listporderbystuid(String id);
	
	//    修改订单状态(包括确认订单以及取消订单)
	
	@Update("update t_order set o_status =#{status} where o_id = #{id}")
	public int updateorder(String id,int status);
	
	
	//     通过地图查询学生员
	
	

}
