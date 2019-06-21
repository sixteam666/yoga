package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.project.bean.DynamicBean;

/**
 * 粉丝关注业务数据持久层
 * @author Administrator
 * 
 * 自己列着玩的，别在意
 * 	1.展示所有动态
 *  2.展示关注对象的所有动态
 *  3.展示好友的所有动态
 *  4.展示自己的动态
 *  5.展示粉丝 （粉丝具有三种类型，需要查找三张表）
 *  6.展示好友
 *  7.展示关注的人
 *  8.添加动态
 *  9.删除动态
 *  10.加关注
 *  11.取消关注
 */
public interface IDynamicDao {
	
	/**
	 * 展示所有动态
	 * @return
	 */
	@Select("select * from t_dynamic")
	List<DynamicBean> listAllDynamics();
	
	/**
	 * 方法一：查询教练关注学员动态
	 * 1.查询出当前用户关注的所有对象id
	 * 2.使用id集合查询出学员（在学员表中进行查询，自动过滤了教练和场馆）的名字与头像
	 * 3.使用id集合查询出动态表查询出所有动态集合，如何将每个动态集合分配给对应的学员？
	 * 
	 * 方法一被判死刑
	 * 
	 * 展示当前用户所有关注对象的动态
	 * @param id 当前用户id
	 * @return 关注的对象所有动态内容集合
	 */
	@Select("select * from t_dynamic where id "
			+ "in(select a_idolid from t_attention where a_myid = #{id})")
	List<DynamicBean> listIdolsDynamics(String id);
	
	/**
	 * 显示当前用户所有关注对象名称
	 * @param id
	 * @return
	 */
	@Select("select d_userid,d_nickname,d_type from t_dynamic where id "
			+ "in(select a_idolid from t_attention where a_myid = #{id})")
	List<DynamicBean> listIdols(String id);
	
	/**
	 * 显示当前用户所有粉丝对象名称
	 * @param id
	 * @return
	 */
	@Select("select d_userid,d_nickname,d_type from t_dynamic where id "
			+ "in(select f_fansid from t_fans where f_myid = #{id})")
	List<DynamicBean> listFans(String id);
	
	/**
	 * 查询当前用户关注数量
	 * @param id 当前用户id
	 * @return 关注数量
	 */
	@Select("select count(1) from t_attention where a_myid = #{id}")
	Integer countIdols(String id);
	
	/**
	 * 查询当前用户粉丝数量
	 * @param id 当前用户id
	 * @return 粉丝数量
	 */
	@Select("select count(1) from t_fans where f_myid = #{id}")
	Integer countFans(String id);
	
	/**
	 * 展示指定用户的所有动态
	 * @param id 指定用户的id
	 * @return 指定用户的动态集合
	 */
	@Select("select * from t_dynamic where d_userid = #{id}")
	List<DynamicBean> listDynamics(String id);
	
	/**
	 * 展示朋友动态
	 * @param id
	 * @return
	 */
	@Select("select * from t_dynamic where d_userid "
			+ "in(select * from t_attention,t_fans where)")
	List<DynamicBean> listFriendsDynamics(String id);
	
	
	/**
	 * 添加动态
	 * @param dynamic 添加的动态实体内容
	 * @return 添加结果
	 */
	@Insert("insert into "
			+ "t_dynamic(d_content,d_time,d_userid,d_img,d_heading,d_nickname,d_type) "
			+ "values(#{d_content},#{d_time},#{d_userid},#{d_img},#{d_heading},"
			+ "#{d_nickname},#{d_type})")
	Integer saveDynamic(DynamicBean dynamic);
	
	/**
	 * 删除动态
	 * @param id
	 * @return
	 */
	@Delete("delete from t_dynamic where d_id = #{id}")
	Integer deleteDynamic(Integer id);

}
