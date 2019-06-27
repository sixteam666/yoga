package com.project.bean;

/**
 * 留言
 * @author Administrator
 *
 */
public class WordsBean {
	private int w_id;
	private String w_content;
	private String w_time;
	private String w_userid;
	private String w_showid;
	
	
	public String getW_showid() {
		return w_showid;
	}
	public void setW_showid(String w_showid) {
		this.w_showid = w_showid;
	}
	public int getW_id() {
		return w_id;
	}
	public void setW_id(int w_id) {
		this.w_id = w_id;
	}
	public String getW_content() {
		return w_content;
	}
	public void setW_content(String w_content) {
		this.w_content = w_content;
	}
	public String getW_time() {
		return w_time;
	}
	public void setW_time(String w_time) {
		this.w_time = w_time;
	}
	public String getW_userid() {
		return w_userid;
	}
	public void setW_userid(String w_userid) {
		this.w_userid = w_userid;
	}
	@Override
	public String toString() {
		return "WordsBean [w_id=" + w_id + ", w_content=" + w_content + ", w_time=" + w_time + ", w_userid=" + w_userid
				+ ", w_showid=" + w_showid + "]";
	}
}
