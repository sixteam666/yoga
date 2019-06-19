package com.project.bean;

/*
 * 教练对象
 */
public class CoachBean {
	private String c_id;
	private String c_name;
	private String c_password;
	private String c_phone;
	private int c_privacy;
	private String c_nickname;
	private String c_headimg;
	private double c_money;
	private String c_address;
	private String c_style;
	private String c_access;
	private String c_price;
	private String c_g_id;
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_password() {
		return c_password;
	}
	public void setC_password(String c_password) {
		this.c_password = c_password;
	}
	public String getC_phone() {
		return c_phone;
	}
	public void setC_phone(String c_phone) {
		this.c_phone = c_phone;
	}
	public int getC_privacy() {
		return c_privacy;
	}
	public void setC_privacy(int c_privacy) {
		this.c_privacy = c_privacy;
	}
	public String getC_nickname() {
		return c_nickname;
	}
	public void setC_nickname(String c_nickname) {
		this.c_nickname = c_nickname;
	}
	public String getC_headimg() {
		return c_headimg;
	}
	public void setC_headimg(String c_headimg) {
		this.c_headimg = c_headimg;
	}
	public double getC_money() {
		return c_money;
	}
	public void setC_money(double c_money) {
		this.c_money = c_money;
	}
	public String getC_address() {
		return c_address;
	}
	public void setC_address(String c_address) {
		this.c_address = c_address;
	}
	public String getC_style() {
		return c_style;
	}
	public void setC_style(String c_style) {
		this.c_style = c_style;
	}
	public String getC_access() {
		return c_access;
	}
	public void setC_access(String c_access) {
		this.c_access = c_access;
	}
	public String getC_price() {
		return c_price;
	}
	public void setC_price(String c_price) {
		this.c_price = c_price;
	}
	public String getC_g_id() {
		return c_g_id;
	}
	public void setC_g_id(String c_g_id) {
		this.c_g_id = c_g_id;
	}
	@Override
	public String toString() {
		return "CoachBean [c_id=" + c_id + ", c_name=" + c_name + ", c_password=" + c_password + ", c_phone=" + c_phone
				+ ", c_privacy=" + c_privacy + ", c_nickname=" + c_nickname + ", c_headimg=" + c_headimg + ", c_money="
				+ c_money + ", c_address=" + c_address + ", c_style=" + c_style + ", c_access=" + c_access
				+ ", c_price=" + c_price + ", c_g_id=" + c_g_id + "]";
	}
	

}
