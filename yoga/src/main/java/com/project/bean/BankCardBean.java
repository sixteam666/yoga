package com.project.bean;

/**
 * 银行卡对象
 * @author Administrator
 *
 */
public class BankCardBean {

	private Integer id;
	private String b_carid;
	private String b_bank;
	private Double b_balance;
	private String userId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getB_carid() {
		return b_carid;
	}
	public void setB_carid(String b_carid) {
		this.b_carid = b_carid;
	}
	public String getB_bank() {
		return b_bank;
	}
	public void setB_bank(String b_bank) {
		this.b_bank = b_bank;
	}
	public Double getB_balance() {
		return b_balance;
	}
	public void setB_balance(Double b_balance) {
		this.b_balance = b_balance;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "BankCardBean [id=" + id + ", b_carid=" + b_carid + ", b_bank=" + b_bank + ", b_balance=" + b_balance
				+ ", userId=" + userId + "]";
	}
	
	
}
