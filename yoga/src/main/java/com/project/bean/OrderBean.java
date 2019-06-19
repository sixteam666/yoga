package com.project.bean;

/**
 * 场馆课程订单
 * @author Administrator
 *
 */
public class OrderBean {
	private int o_id;
	private int o_status;
	private String o_time;
	private String o_s_id;
	private int o_l_id;
	private double o_price;
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
	public int getO_status() {
		return o_status;
	}
	public void setO_status(int o_status) {
		this.o_status = o_status;
	}
	public String getO_time() {
		return o_time;
	}
	public void setO_time(String o_time) {
		this.o_time = o_time;
	}
	public String getO_s_id() {
		return o_s_id;
	}
	public void setO_s_id(String o_s_id) {
		this.o_s_id = o_s_id;
	}
	public int getO_l_id() {
		return o_l_id;
	}
	public void setO_l_id(int o_l_id) {
		this.o_l_id = o_l_id;
	}
	public double getO_price() {
		return o_price;
	}
	public void setO_price(double o_price) {
		this.o_price = o_price;
	}
	@Override
	public String toString() {
		return "OrderBean [o_id=" + o_id + ", o_status=" + o_status + ", o_time=" + o_time + ", o_s_id=" + o_s_id
				+ ", o_l_id=" + o_l_id + ", o_price=" + o_price + "]";
	}
	

}
