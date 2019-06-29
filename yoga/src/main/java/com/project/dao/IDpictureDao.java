package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.bean.DPictureBean;
import com.project.bean.PictureBean;

public interface IDpictureDao {
	/**
	 * 查找动态图片
	 * @param gymId
	 * @return
	 */
	@Select("select * from t_dpicture where dp_d_id=#{dp_d_id}")
	public List<DPictureBean> findAll( String dp_d_id);
	
	/**
	 * 添加图片
	 * @param list
	 * @return
	 */
	//@Insert("insert into t_picture(p_imgname,p_g_id) values(#{p_imgname},#{p_g_id})")
	@Insert({"<script> insert into t_dpicture(dp_d_id,dp_img) values " +
 			"<foreach collection='list' item='item' index='index' separator=','>"+
            "(#{item.dp_d_id},#{item.dp_img})"+
            "</foreach> </script>"})
	public int addPicture(@Param(value="list") List<DPictureBean> list);
	
	
	
	/**
	 * 删除图片
	 * @param id
	 * @return
	 */
	@Delete("delete from t_dpicture where dp_d_id=#{dp_d_id}")
	public int deletePictures(String dp_d_id);
	

}
