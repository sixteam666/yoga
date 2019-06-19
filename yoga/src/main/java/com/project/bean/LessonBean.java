package com.project.bean;
/**
 * 课程对象
 * @author Administrator
 *
 */
public class LessonBean {
	private int l_id;
	private String l_time;
	private String l_c_id;
	private String l_descirbe;
	private int l_size;
	private double l_price;
	private String l_g_id;
	public int getL_id() {
		return l_id;
	}
	public void setL_id(int l_id) {
		this.l_id = l_id;
	}
	public String getL_time() {
		return l_time;
	}
	public void setL_time(String l_time) {
		this.l_time = l_time;
	}
	public String getL_c_id() {
		return l_c_id;
	}
	public void setL_c_id(String l_c_id) {
		this.l_c_id = l_c_id;
	}
	public String getL_descirbe() {
		return l_descirbe;
	}
	public void setL_descirbe(String l_descirbe) {
		this.l_descirbe = l_descirbe;
	}
	public int getL_size() {
		return l_size;
	}
	public void setL_size(int l_size) {
		this.l_size = l_size;
	}
	public double getL_price() {
		return l_price;
	}
	public void setL_price(double l_price) {
		this.l_price = l_price;
	}
	public String getL_g_id() {
		return l_g_id;
	}
	public void setL_g_id(String l_g_id) {
		this.l_g_id = l_g_id;
	}
	@Override
	public String toString() {
		return "LessonBean [l_id=" + l_id + ", l_time=" + l_time + ", l_c_id=" + l_c_id + ", l_descirbe=" + l_descirbe
				+ ", l_size=" + l_size + ", l_price=" + l_price + ", l_g_id=" + l_g_id + "]";
	}
	

}
