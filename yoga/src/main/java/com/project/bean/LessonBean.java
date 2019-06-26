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
	private int l_s_number;
	private double l_price;
	private String l_g_id;
	private int l_weekday;
	private int l_datetime;
	private CoachBean coach;
	public CoachBean getCoach() {
		return coach;
	}
	public void setCoach(CoachBean coach) {
		this.coach = coach;
	}
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
	
	public int getL_weekday() {
		return l_weekday;
	}
	public void setL_weekday(int l_weekday) {
		this.l_weekday = l_weekday;
	}
	public int getL_datetime() {
		return l_datetime;
	}
	public void setL_datetime(int l_datetime) {
		this.l_datetime = l_datetime;
	}
	
	public int getL_s_number() {
		return l_s_number;
	}
	public void setL_s_number(int l_s_number) {
		this.l_s_number = l_s_number;
	}
	@Override
	public String toString() {
		return "LessonBean [l_id=" + l_id + ", l_time=" + l_time + ", l_c_id=" + l_c_id + ", l_descirbe=" + l_descirbe
				+ ", l_size=" + l_size + ", l_s_number=" + l_s_number + ", l_price=" + l_price + ", l_g_id=" + l_g_id
				+ ", l_weekday=" + l_weekday + ", l_datetime=" + l_datetime + "]";
	}
	
	

}
