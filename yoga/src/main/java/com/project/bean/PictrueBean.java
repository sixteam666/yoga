package com.project.bean;

/**
 * 场馆图片
 * @author Administrator
 *
 */
public class PictrueBean {
	private int p_id;
	private String p_imgname;
	private String p_g_id;
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
	@Override
	public String toString() {
		return "PictrueBean [p_id=" + p_id + ", p_imgname=" + p_imgname + ", p_g_id=" + p_g_id + "]";
	}
	

}
