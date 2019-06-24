package com.project.bean;

import java.util.List;

public class PageBean {
	private int id;
	private int page;
	private int size;
	private int totalNumber;
	private int totalPage;
	private List<?> list;
	
	public PageBean() {
		
	}
	
	public PageBean(int page, int size, int totalNumber, int totalPage, List<?> list) {
		this.page = page;
		this.size = size;
		this.totalNumber = totalNumber;
		this.totalPage = totalPage;
		this.list = list;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageBean [id=" + id + ", page=" + page + ", size=" + size + ", totalNumber=" + totalNumber
				+ ", totalPage=" + totalPage + ", list=" + list + "]";
	}
	
}
