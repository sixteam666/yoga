package com.project.bean;

/**
 * 动态对象
 * @author Administrator
 *
 */
public class DynamicBean {
	private int d_id;
	private String d_content;
	private String d_time;
	private String d_userid;
	private String d_img;
	private String d_heading;
	private String d_nickname;
	private String d_type;
	public int getD_id() {
		return d_id;
	}
	public void setD_id(int d_id) {
		this.d_id = d_id;
	}
	public String getD_content() {
		return d_content;
	}
	public void setD_content(String d_content) {
		this.d_content = d_content;
	}
	public String getD_time() {
		return d_time;
	}
	public void setD_time(String d_time) {
		this.d_time = d_time;
	}
	public String getD_userid() {
		return d_userid;
	}
	public void setD_userid(String d_userid) {
		this.d_userid = d_userid;
	}
	public String getD_img() {
		return d_img;
	}
	public void setD_img(String d_img) {
		this.d_img = d_img;
	}
	public String getD_heading() {
		return d_heading;
	}
	public void setD_heading(String d_heading) {
		this.d_heading = d_heading;
	}
	public String getD_nickname() {
		return d_nickname;
	}
	public void setD_nickname(String d_nickname) {
		this.d_nickname = d_nickname;
	}
	public String getD_type() {
		return d_type;
	}
	public void setD_type(String d_type) {
		this.d_type = d_type;
	}
	@Override
	public String toString() {
		return "DynamicBean [d_id=" + d_id + ", d_content=" + d_content + ", d_time=" + d_time + ", d_userid="
				+ d_userid + ", d_img=" + d_img + ", d_heading=" + d_heading + ", d_nickname=" + d_nickname
				+ ", d_type=" + d_type + "]";
	}
	
}
