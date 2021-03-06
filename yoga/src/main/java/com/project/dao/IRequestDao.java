package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.bean.GymBean;
import com.project.bean.RequestBean;
import com.project.bean.StudentBean;

public interface IRequestDao {
	/**
	 * 添加申请
	 * @param r_reqid 申请方  id
	 * @param r_resid 被申请方 id
	 * @param r_date 申请时间
	 * @return 影响行数
	 /** 添加申请
	  * 
	 */
	@Insert("insert into t_request(r_reqid,r_resid,r_date) values(#{r_reqid},#{r_resid},#{r_date})")
	public int addRequest(@Param("r_reqid")String r_reqid,@Param("r_resid")String r_resid,@Param("r_date")String r_date);

	/**
	 * 添加申请
	 * @param r_reqid 申请方  id
	 * @param r_resid 被申请方 id
	 * @return 影响行数
	 */
	@Insert("insert into t_request(r_reqid,r_resid,r_reqname,r_resname) values(#{r_reqid},#{r_resid},#{r_reqname},#{r_resname})")
	public int addRequeststu(@Param("r_reqid")String r_reqid,@Param("r_resid")String r_resid,@Param("r_reqname") String r_reqname ,@Param("r_resname") String r_resname);
	/**
	 * 修改申请
	 * @param r_reqid 申请方  id
	 * @param r_resid 被申请方 id
	 * @return 影响行数
	 */
	@Update("update t_request set r_state = #{r_state} where r_reqid = #{r_reqid} and r_resid = #{r_resid}")
	public int updateRequestState(@Param("r_reqid")String r_reqid,@Param("r_resid")String r_resid,@Param("r_state")int r_state);
	/**
	 * 查询是否有申请关系
	 * @param r_reqid 声请人id
	 * @param r_resid 被声请人id
	 * @return
	 */
	@Select("select * from t_request where r_reqid = #{req} and r_resid = #{res} or (r_reqid = #{res} and r_resid = #{req})")
	public RequestBean findIsRequest(@Param("req")String r_reqid, @Param("res")String r_resid);

	/**
	 * 查询我的场馆通知
	 * @param resid 
	 * @return
	 */
	@Select("select g_id,g_headimg,g_name,r_date as g_password from t_request r join t_gym g on g.g_id=r.r_reqid where r.r_resid=#{resid} and r.r_state=0")
	public List<GymBean> findOtherToMe(String resid);
	/**
	 * 查看我的学员通知
	 * @param resid
	 * @return
	 */
	@Select("select s_id,s_nickname,s_headimg,r_date as s_password from t_request r join t_student s on s.s_id=r.r_reqid where r.r_resid=#{resid} and r.r_state=0")
	public List<StudentBean> findStuToMe(String resid);
	


	/**
	 * 查询自己的通知
	 * @param id
	 * @return
	 */
	@Select("select * from t_request where r_resid = #{id}")
	public List<RequestBean> listrequest(String id);
	
}
