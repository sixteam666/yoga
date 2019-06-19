package com.project.bean;

/**
 * 场馆对象
 * @author Administrator
 *
 */
public class GymBean {
	private String g_id;
	private String g_email;
	private String g_password;
	private String g_name;
	private String g_phone;
	private String g_contactphone;
	private String g_address;
	private String g_message;
	private String g_qq;
	private String g_headimg;
	public String getG_id() {
		return g_id;
	}
	public void setG_id(String g_id) {
		this.g_id = g_id;
	}
	public String getG_email() {
		return g_email;
	}
	public void setG_email(String g_email) {
		this.g_email = g_email;
	}
	public String getG_password() {
		return g_password;
	}
	public void setG_password(String g_password) {
		this.g_password = g_password;
	}
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getG_phone() {
		return g_phone;
	}
	public void setG_phone(String g_phone) {
		this.g_phone = g_phone;
	}
	public String getG_contactphone() {
		return g_contactphone;
	}
	public void setG_contactphone(String g_contactphone) {
		this.g_contactphone = g_contactphone;
	}
	public String getG_address() {
		return g_address;
	}
	public void setG_address(String g_address) {
		this.g_address = g_address;
	}
	public String getG_message() {
		return g_message;
	}
	public void setG_message(String g_message) {
		this.g_message = g_message;
	}
	public String getG_qq() {
		return g_qq;
	}
	public void setG_qq(String g_qq) {
		this.g_qq = g_qq;
	}
	public String getG_headimg() {
		return g_headimg;
	}
	public void setG_headimg(String g_headimg) {
		this.g_headimg = g_headimg;
	}
	@Override
	public String toString() {
		return "GymBean [g_id=" + g_id + ", g_email=" + g_email + ", g_password=" + g_password + ", g_name=" + g_name
				+ ", g_phone=" + g_phone + ", g_contactphone=" + g_contactphone + ", g_address=" + g_address
				+ ", g_message=" + g_message + ", g_qq=" + g_qq + ", g_headimg=" + g_headimg + "]";
	}
	

}
