package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.bean.PictureBean;

public interface IPictureDao {
	/**
	 * 查找场馆图片
	 * @param gymId
	 * @return
	 */
	@Select("select * from t_picture where p_g_id=#{p_g_id} and p_type=#{p_type}")
	public List<PictureBean> findAll(@Param(value="p_g_id") String p_g_id,@Param(value="p_type") int p_type);
	
	/**
	 * 添加图片
	 * @param list
	 * @return
	 */
	//@Insert("insert into t_picture(p_imgname,p_g_id) values(#{p_imgname},#{p_g_id})")
	@Insert({"<script> insert into t_picture(p_imgname,p_g_id,p_type,p_index) values " +
 			"<foreach collection='list' item='item' index='index' separator=','>"+
            "(#{item.p_imgname},#{item.p_g_id},#{item.p_type},#{item.p_index})"+
            "</foreach> </script>"})
	public int addPicture(@Param(value="list") List<PictureBean> list);
	
	/**
	 * 修改图片
	 * @param picture
	 * @return
	 */
	@Update("update t_picture set p_imgname=#{p_imgname} where p_id=#{p_id}")
	public int updatePicture(PictureBean picture);
	
	/**
	 * 删除图片
	 * @param id
	 * @return
	 */
	@Delete("delete from t_picture where p_id=#{p_id}")
	public int deletePicture(int id);

}
