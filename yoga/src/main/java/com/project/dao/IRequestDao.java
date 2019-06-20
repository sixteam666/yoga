package com.project.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

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
	 * @param r_state 1:同意，2：拒绝
	 * @return 影响行数
	 */
	@Update("update t_request set r_state = #{r_state} where r_reqid = #{r_reqid} and r_resid = #{r_resid}")
	public int updateRequestState(@Param("r_reqid")String r_reqid,@Param("r_resid")String r_resid,@Param("r_state")int r_state);
}
