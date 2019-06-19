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
	@Override
	public String toString() {
		return "DynamicBean [d_id=" + d_id + ", d_content=" + d_content + ", d_time=" + d_time + ", d_userid="
				+ d_userid + "]";
	}
	

}
