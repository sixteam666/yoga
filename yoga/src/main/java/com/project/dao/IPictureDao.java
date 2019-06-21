package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.bean.PictureBean;

public interface IPictureDao {
	/**
	 * 查找场馆图片
	 * @param gymId
	 * @return
	 */
	@Select("select * from t_picture where p_g_id=#{p_g_id}")
	public List<PictureBean> findAll(String gymId);
	
	/**
	 * 添加图片
	 * @param list
	 * @return
	 */
	//@Insert("insert into t_picture(p_imgname,p_g_id) values(#{p_imgname},#{p_g_id})")
	@Insert({"<script> insert into t_picture(p_imgname,p_g_id) values " +
 			"<foreach collection='list' item='item' index='index' separator=','>"+
            "(#{item.p_imgname},#{item.p_g_id})"+
            "</foreach> </script>"})
	public int addPicture(@Param(value="list") List<PictureBean> list);
	
	/**
	 * 删除图片
	 * @param id
	 * @return
	 */
	@Delete("delete from t_picture where p_id=#{p_id}")
	public int deletePicture(int id);

}
