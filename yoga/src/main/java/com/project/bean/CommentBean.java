package com.project.bean;

/**
 * 评论对象
 * @author Administrator
 *
 */
public class CommentBean {
	private int pl_id;
	private String pl_content;
	private String pl_level;
	private String pl_c_id;
	private String pl_s_id;
	private String pl_time;
	private String pl_s_nickname;
	private String pl_s_headimg;
	public int getPl_id() {
		return pl_id;
	}
	public void setPl_id(int pl_id) {
		this.pl_id = pl_id;
	}
	public String getPl_content() {
		return pl_content;
	}
	public void setPl_content(String pl_content) {
		this.pl_content = pl_content;
	}
	public String getPl_level() {
		return pl_level;
	}
	public void setPl_level(String pl_level) {
		this.pl_level = pl_level;
	}
	public String getPl_c_id() {
		return pl_c_id;
	}
	public void setPl_c_id(String pl_c_id) {
		this.pl_c_id = pl_c_id;
	}
	public String getPl_s_id() {
		return pl_s_id;
	}
	public void setPl_s_id(String pl_s_id) {
		this.pl_s_id = pl_s_id;
	}
	public String getPl_time() {
		return pl_time;
	}
	public void setPl_time(String pl_time) {
		this.pl_time = pl_time;
	}
	public String getPl_s_nickname() {
		return pl_s_nickname;
	}
	public void setPl_s_nickname(String pl_s_nickname) {
		this.pl_s_nickname = pl_s_nickname;
	}
	public String getPl_s_headimg() {
		return pl_s_headimg;
	}
	public void setPl_s_headimg(String pl_s_headimg) {
		this.pl_s_headimg = pl_s_headimg;
	}
	@Override
	public String toString() {
		return "CommentBean [pl_id=" + pl_id + ", pl_content=" + pl_content + ", pl_level=" + pl_level + ", pl_c_id="
				+ pl_c_id + ", pl_s_id=" + pl_s_id + ", pl_time=" + pl_time + ", pl_s_nickname=" + pl_s_nickname
				+ ", pl_s_headimg=" + pl_s_headimg + "]";
	}

}
