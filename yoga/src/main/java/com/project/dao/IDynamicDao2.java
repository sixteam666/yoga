package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.project.bean.DynamicBean;

/**
 * 动态持久层接口
 * @author Administrator
 *
 */
public interface IDynamicDao2 {
	/**
	 * 查找所有的动态
	 * @param usreId
	 * @return
	 */
	public List<DynamicBean> findAllDyn(@Param("d_userid") String d_userid, @Param("page") int page, @Param("size") int size);
	
	
	/**
	 * 查找用户的动态
	 * @param usreId
	 * @return
	 */
	public List<DynamicBean> findMyDyn(@Param("d_userid") String d_userid, @Param("page") int page, @Param("size") int size);
	
	/**
	 * 查找关注对象或好友、粉丝的动态
	 * @param usreId
	 * @return
	 */
	public List<DynamicBean> findOtherDyn(@Param("d_userid") String d_userid, @Param("page") int page, @Param("size") int size);
	
	/**
	 * 添加动态
	 * @param usreId
	 * @return
	 */
	@Insert("insert into t_dynamic(d_content,d_time,d_userid,d_img,d_headimg,d_nickname,d_type) "
			+ "values(#{d_content},#{d_time},#{d_userid},#{d_img},#{d_headimg},#{d_nickname},#{d_type})")
	public int insertDynamic(DynamicBean bean);
	
	
	/**
	 * 删除动态
	 * @param id
	 * @return
	 */
	@Delete("delete from t_dynamic where d_id=#{d_id}")
	public int deleteDynamic(int id);

}
