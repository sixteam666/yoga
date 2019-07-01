package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.bean.CoachBean;
import com.project.bean.GymBean;
import com.project.bean.StudentBean;

public interface ICoachDao {
	/**
	 * 添加教练
	 * @param coach coachBean对象
	 * @return 影响行数
	 */
	@Insert("insert into t_coach(c_id,c_name,c_password,c_phone,c_privacy,"
			+ "c_nickname,c_headimg,c_money,c_address,c_style,c_access,c_price,c_g_id)"
			+ "values(#{c_id},#{c_name},#{c_password},#{c_phone},#{c_privacy},"
			+ "#{c_name},#{c_headimg},#{c_money},#{c_address},#{c_style},"
			+ "#{c_access},#{c_price},#{c_g_id})")
	public int addCoach(CoachBean coach);
	/**
	 * 根据用户名或电话号码查询教练
	 * @param name 用户名或电话号码
	 * @return 教练对象
	 */
	@Select("select * from t_coach where c_name = #{name}")
	public CoachBean findCoachByName(String name);
	/**
	 * 通过场馆id查询教练	
	 * @param gymId 场馆id
	 * @return 教练集合
	 */
	@Select("select * from t_coach where c_g_id = #{gymId}")
	public List<CoachBean> findCoachByGymId(String gymId);
	
	/**
	 * 查询热门教练	
	 * @param
	 * @return 教练集合
	 */
	@Select("select * from t_coach limit 0,6")
	public List<CoachBean> findHotCoach();
	
	
	//教练信息完善，方式变更
	/**
	 * 教练信息完善，包含：动态权限设置、昵称设置、头像设置、地址设置
	 * 业务：教练资料完善，教练资料更新
	 * @author pan
	 * @param coach
	 * @return
	 */
	@Update("update t_coach set c_privacy = #{c_privacy}, c_nickname = #{c_nickname},"
			+ "c_headimg = #{c_headimg}, c_address = #{c_address}, c_style = #{c_style},"
			+ "c_access = #{c_access}, c_price = #{c_price} where c_id = #{c_id} ")
	int updateCoachDetailInfo(CoachBean coach);
	
	/**
	 * 通过id查找教练，显示教练详细信息，懒加载查询教练所属场馆信息，场馆需提供findGymById方法
	 * 业务:教练资料展示
	 * @author pan
	 * @param id 教练id
	 * @return 教练对象
	 */
	@Select("select * from t_coach where c_id = #{id}")
	@Results({
		@Result(id = true, property = "c_id", column = "c_id"),
		@Result(property = "c_name", column = "c_name"),
		//@Result(property = "c_password", column = "c_password"),密码不展示
		@Result(property = "c_phone", column = "c_phone"),
		@Result(property = "c_privacy", column = "c_privacy"),
		@Result(property = "c_nickname", column = "c_nickname"),
		@Result(property = "c_headimg", column = "c_headimg"),
		@Result(property = "c_address", column = "c_address"),
		@Result(property = "c_style", column = "c_style"),
		@Result(property = "c_access", column = "c_access"),
		@Result(property = "c_price", column = "c_price"),
		@Result(property = "c_money", column = "c_money"),
		@Result(property = "gym", column = "c_g_id", 
			one = @One(select = "com.project.dao.IGymDao.findGymById"))})
	CoachBean getCoachById(String id);
	
	/**
	 * 密码修改
	 * @author pan
	 * @param newPassword 新密码
	 * @param id 教练id
	 * @return 修改结果，为 1 则修改成功
	 */
	@Update("update t_coach set c_password = #{newPassword} where c_id = #{id}")
	Integer updatePassword(@Param("newPassword") String newPassword, @Param("id") String id);
	
	/**
	 * 用于解约教练
	 * 用于签约或者解约教练
	 * @g_id 场馆id
	 * @c_id 教练id
	 * @return 影响行数
	 */
	@Update("update t_coach set c_g_id = #{g_id} where c_id = #{c_id}")
	public int updateCoachGymId(String g_id,String c_id);
	
	/**
	 * 钱包提现功能
	 * @param id 提现教练id
	 * @param money	提现金额
	 * @return 更新结果
	 */
	@Update("update t_coach set c_money = c_money - #{money} where c_id = #{id}")
	Integer updateMoney(@Param("id") String id, @Param("money") Double money);
	
	/**
	 * 钱包余额查询
	 * @param id
	 * @return
	 */
	@Select("select c_money from t_coach where c_id = #{id}")
	Double getMoney(String id);
	
	/**
	 * 查询教练所有私教学员(通过订单表来实现的学员与教练的关系，扯不扯？)
	 * @autor pan
	 * @param id 私教id
	 * @return 学员集合
	 */
	@Select("select po.po_time,s.* from t_student s,t_porder po where s.s_id=po.po_s_id and po.po_c_id = #{cid}")
	List<StudentBean> listStudentByCoachId(String id);
	
	/**
	 * 查询教练基本信息：昵称，手机号，qq号，地址，头像等
	 * @param id 教练id
	 * @return
	 */
	@Select("select c_id,c_phone,c_nickname,c_headimg,c_address,c_privacy,c_qq "
			+ "from t_coach where c_id = #{id}")
	public CoachBean getPersonalInfo(String id);
	
	/**
	 * 更改基本信息：昵称、头像、手机号 、他人查看权限、地址、qq
	 * @param coach
	 * @return
	 */
	@Update("update t_coach set c_nickname = #{c_nickname}, c_headimg = #{c_headimg},"
			+ " c_address = #{c_address}, c_privacy = #{c_privacy}, c_phone = #{c_phone},"
			+ " c_qq = #{c_qq} where c_id = #{c_id}")
	int updateCoachPersonalInfo(CoachBean coach);
	
	/**
	 * 教练认证
	 * @uthor pan
	 * @param coach
	 */
	@Update("update t_coach set authentication = 5 where c_id = #{c_id}")
	public void updateAuthentication(CoachBean coach);
	/**
	 * 教练认证结束
	 * @uthor pan
	 * @param coach
	 */
	@Update("update t_coach set authentication = 1 where c_id = #{c_id}")
	public void updateAuthenticationSuccess(CoachBean coach);
	
	/**
	 * 查询教练课程设置
	 * @author pan
	 * @param id
	 * @return
	 */
	@Select("select c_id,c_style,c_price,c_access from t_coach where c_id = #{id}")
	public CoachBean getLessonInfo(String id);
	
	/**
	 * 更新教练课程设置
	 * @author pan 
	 * @param coach
	 */
	@Update("update t_coach set c_style = #{c_style}, c_price = #{c_price}, c_access = #{c_access} where c_id = #{c_id}")
	public void updateCoachLessonInfo(CoachBean coach);
	/**
	 * 通过手机号查找教练
	 * @param phone
	 * @return
	 */
	@Select("select * from t_coach where c_phone = #{phone}")
	public CoachBean findCoachByPhone(String phone);
	/**
	 * 通过昵称查找教练
	 * @param nickname
	 * @return
	 */
	@Select("select * from t_coach where c_g_id=#{c_g_id} and c_nickname like concat('%',#{c_nickname},'%')")
	public List<CoachBean> findCoachByNick(CoachBean coach);
	
	/**
	 * 查询所有教练
	 * @return
	 */
	@Select("select * from t_coach")
	public List<CoachBean> findAll();
	/**
	 * 查询教练是否有签约场馆
	 * @param r_resid
	 * @return
	 */
	@Select("select * from t_coach where c_id=#{r_reqid}")
	public CoachBean findIsGym(String r_reqid);
}
