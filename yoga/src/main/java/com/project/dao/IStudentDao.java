package com.project.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.bean.StudentBean;

public interface IStudentDao {
      //	添加学生
	@Insert("insert into t_student(s_id,s_name,s_password,s_phone,s_privacy,"
			+"s_nickname,s_headimg,s_money,s_address)values () ")
	public int addStudent(StudentBean student);
	
	//      查询学生
	@Select("select *from t_student where s_id = id ")
	public StudentBean findStudentbyId(String id);
	
	//      更新学生个人信息
	@Update("updata t_student set s_privacy=#{s_privacy}, s_nickname=#{s_nickname}"
			+",s_headimg=#{s_headimg},s_address=#{s_address} where s_id=#{s_id}")
	public int updateStudent();
	
	//      修改密码
	@Update("UPDATE t_student SET s_password=#{pwd} WHERE s_id=#{id}")
	public int updatePassowrd(@Param("id") String id, @Param("pwd") String pwd );
	
	//      更新手机
	@Update("UPDATE t_student SET s_phone=#{phone} WHERE s_id=#{id}")
	public int updatePhone(@Param("id") String id, @Param("phone") String phone );
	
	//      充值金额
	@Update("UPDATE t_student SET s_money=s_money+#{money} WHERE s_id=#{id}")
	public int addMoney(@Param("id") String id, @Param("money") String money );
	
	//      消费扣款
	@Update("UPDATE t_student SET s_money=s_money-#{money} WHERE s_id=#{id}")
	public int subMoney(@Param("id") String id, @Param("money") String money );
	
	//     通过地图查询学生
	

}
