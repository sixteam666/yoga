package com.project.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.bean.RequestBean;

public interface IRequestDao {
	/**
	 * 添加申请
	 * @param r_reqid 申请方  id
	 * @param r_resid 被申请方 id
	 * @return 影响行数
	 */
	@Insert("insert into t_request(r_reqid,r_resid) values(#{r_reqid},#{r_resid})")
	public int addRequest(@Param("r_reqid")String r_reqid,@Param("r_resid")String r_resid);
	/**
	 * 修改申请
	 * @param r_reqid 申请方  id
	 * @param r_resid 被申请方 id
	 * 都是删除数据
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
	@Select("select * from t_request where r_reqid = #{req} and r_resid = #{res} or r_reqid = #{res} and r_resid = #{req}")
	public RequestBean findIsRequest(@Param("req")String r_reqid, @Param("res")String r_resid);
}
