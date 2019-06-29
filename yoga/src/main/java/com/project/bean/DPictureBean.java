package com.project.bean;

public class DPictureBean {
	private int dp_id;
	private int dp_d_id;
	private String dp_img;
	public int getDp_id() {
		return dp_id;
	}
	public void setDp_id(int dp_id) {
		this.dp_id = dp_id;
	}
	public int getDp_d_id() {
		return dp_d_id;
	}
	public void setDp_d_id(int dp_d_id) {
		this.dp_d_id = dp_d_id;
	}
	public String getDp_img() {
		return dp_img;
	}
	public void setDp_img(String dp_img) {
		this.dp_img = dp_img;
	}
	@Override
	public String toString() {
		return "DPictureBean [dp_id=" + dp_id + ", dp_d_id=" + dp_d_id + ", dp_img=" + dp_img + "]";
	}
	

}
