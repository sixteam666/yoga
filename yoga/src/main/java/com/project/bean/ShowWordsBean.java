package com.project.bean;

public class ShowWordsBean {
	private String headimg;
	private String name;
	private String word;
	private String time;
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "ShowWordsBean [headimg=" + headimg + ", name=" + name + ", word=" + word + ", time=" + time + "]";
	}
}
