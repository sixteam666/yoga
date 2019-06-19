package com.project.bean;

/**
 * 学员
 * @author Administrator
 *
 */
public class StudentBean {
	private String s_id;
	private String s_name;
	private String s_password;
	private String s_phone;
	private int s_privacy;
	private String s_nickname;
	private String s_headimg;
	private double s_money;
	private String s_address;
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_password() {
		return s_password;
	}
	public void setS_password(String s_password) {
		this.s_password = s_password;
	}
	public String getS_phone() {
		return s_phone;
	}
	public void setS_phone(String s_phone) {
		this.s_phone = s_phone;
	}
	public int getS_privacy() {
		return s_privacy;
	}
	public void setS_privacy(int s_privacy) {
		this.s_privacy = s_privacy;
	}
	public String getS_nickname() {
		return s_nickname;
	}
	public void setS_nickname(String s_nickname) {
		this.s_nickname = s_nickname;
	}
	public String getS_headimg() {
		return s_headimg;
	}
	public void setS_headimg(String s_headimg) {
		this.s_headimg = s_headimg;
	}
	public double getS_money() {
		return s_money;
	}
	public void setS_money(double s_money) {
		this.s_money = s_money;
	}
	public String getS_address() {
		return s_address;
	}
	public void setS_address(String s_address) {
		this.s_address = s_address;
	}
	@Override
	public String toString() {
		return "StudentBean [s_id=" + s_id + ", s_name=" + s_name + ", s_password=" + s_password + ", s_phone="
				+ s_phone + ", s_privacy=" + s_privacy + ", s_nickname=" + s_nickname + ", s_headimg=" + s_headimg
				+ ", s_money=" + s_money + ", s_address=" + s_address + "]";
	}
	

}
