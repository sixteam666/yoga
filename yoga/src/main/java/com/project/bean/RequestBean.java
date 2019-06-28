package com.project.bean;

public class RequestBean {
	private int r_id;
	private String r_reqid;
	private String r_resid;
	private String r_reqname;
	private String r_resname;
	private int r_state;
	private String reqid;
	private String reqname;
	private String headimg;
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public String getReqname() {
		return reqname;
	}
	public void setReqname(String reqname) {
		this.reqname = reqname;
	}
	public String getReqid() {
		return reqid;
	}
	public void setReqid(String reqid) {
		this.reqid = reqid;
	}
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public String getR_reqid() {
		return r_reqid;
	}
	public void setR_reqid(String r_reqid) {
		this.r_reqid = r_reqid;
	}
	public String getR_resid() {
		return r_resid;
	}
	public void setR_resid(String r_resid) {
		this.r_resid = r_resid;
	}
	public String getR_reqname() {
		return r_reqname;
	}
	public void setR_reqname(String r_reqname) {
		this.r_reqname = r_reqname;
	}
	public String getR_resname() {
		return r_resname;
	}
	public void setR_resname(String r_resname) {
		this.r_resname = r_resname;
	}
	public int getR_state() {
		return r_state;
	}
	public void setR_state(int r_state) {
		this.r_state = r_state;
	}
	@Override
	public String toString() {
		return "RequestBean [r_id=" + r_id + ", r_reqid=" + r_reqid + ", r_resid=" + r_resid + ", r_reqname="
				+ r_reqname + ", r_resname=" + r_resname + ", r_state=" + r_state + ", reqid=" + reqid + ", reqname="
				+ reqname + ", headimg=" + headimg + "]";
	}
	
	
}
