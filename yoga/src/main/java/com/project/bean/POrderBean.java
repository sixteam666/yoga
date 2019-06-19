package com.project.bean;

/**
 * 私教课订单
 * @author Administrator
 *
 */
public class POrderBean {
	private int po_id;
	private int po_status;
	private String po_time;
	private String po_s_id;
	private String po_c_id;
	private double po_price;
	public int getPo_id() {
		return po_id;
	}
	public void setPo_id(int po_id) {
		this.po_id = po_id;
	}
	public int getPo_status() {
		return po_status;
	}
	public void setPo_status(int po_status) {
		this.po_status = po_status;
	}
	public String getPo_time() {
		return po_time;
	}
	public void setPo_time(String po_time) {
		this.po_time = po_time;
	}
	public String getPo_s_id() {
		return po_s_id;
	}
	public void setPo_s_id(String po_s_id) {
		this.po_s_id = po_s_id;
	}
	public String getPo_c_id() {
		return po_c_id;
	}
	public void setPo_c_id(String po_c_id) {
		this.po_c_id = po_c_id;
	}
	public double getPo_price() {
		return po_price;
	}
	public void setPo_price(double po_price) {
		this.po_price = po_price;
	}
	@Override
	public String toString() {
		return "POrderBean [po_id=" + po_id + ", po_status=" + po_status + ", po_time=" + po_time + ", po_s_id="
				+ po_s_id + ", po_c_id=" + po_c_id + ", po_price=" + po_price + "]";
	}
	

}
