package com.project.bean;

/**
 * 场馆图片
 * @author Administrator
 *
 */
public class PictureBean {
	private int p_id;
	private String p_imgname;
	private String p_g_id;
	private int p_type;
	private int p_index;
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_imgname() {
		return p_imgname;
	}
	public void setP_imgname(String p_imgname) {
		this.p_imgname = p_imgname;
	}
	public String getP_g_id() {
		return p_g_id;
	}
	public void setP_g_id(String p_g_id) {
		this.p_g_id = p_g_id;
	}
	
	public int getP_type() {
		return p_type;
	}
	public void setP_type(int p_type) {
		this.p_type = p_type;
	}
	public int getP_index() {
		return p_index;
	}
	public void setP_index(int p_index) {
		this.p_index = p_index;
	}
	@Override
	public String toString() {
		return "PictureBean [p_id=" + p_id + ", p_imgname=" + p_imgname + ", p_g_id=" + p_g_id + ", p_type=" + p_type
				+ ", p_index=" + p_index + "]";
	}
	

}
