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
	private String d_headimg;
	private String d_nickname;
	private Integer d_type;
	/**
	 * 判断用户关系专用字段，即当前用户是否关注了此动态的持有者
	 * 查询出动态后，需要判断发动态的用户和当前用户的关系，以便设置加关注和已关注
	 * 0 表示未关注，1表示关注，2表示自己
	 */
	private Integer follow = 0;
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
	public String getD_headimg() {
		return d_headimg;
	}
	public void setD_headimg(String d_headimg) {
		this.d_headimg = d_headimg;
	}
	public String getD_nickname() {
		return d_nickname;
	}
	public void setD_nickname(String d_nickname) {
		this.d_nickname = d_nickname;
	}
	public Integer getD_type() {
		return d_type;
	}
	public void setD_type(Integer d_type) {
		this.d_type = d_type;
	}
	public Integer getFollow() {
		return follow;
	}
	public void setFollow(Integer follow) {
		this.follow = follow;
	}
	@Override
	public String toString() {
		return "DynamicBean [d_id=" + d_id + ", d_content=" + d_content + ", d_time=" + d_time + ", d_userid="
				+ d_userid + ", d_img=" + d_img + ", d_headimg=" + d_headimg + ", d_nickname=" + d_nickname
				+ ", d_type=" + d_type + ", follow=" + follow + "]";
	}
	
}
