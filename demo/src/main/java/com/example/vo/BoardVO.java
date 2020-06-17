package com.example.vo;

import java.util.Arrays;

public class BoardVO {
	
	private long brd_no = 0L;
	private String brd_title=null;
	private String brd_content=null;
	private byte[] brd_img=null; //BLOB
	private String brd_id=null;
	private long brd_hit = 1L; //조회수 1부터 시작
	private String brd_date=null;
	
	private String[] tmp = null; //관심분야, 겟셋 만들기
	
	
	
	
	public String[] getTmp() {
		return tmp;
	}
	public void setTmp(String[] tmp) {
		this.tmp = tmp;
	}
	public long getBrd_no() {
		return brd_no;
	}
	public void setBrd_no(long brd_no) {
		this.brd_no = brd_no;
	}
	public String getBrd_title() {
		return brd_title;
	}
	public void setBrd_title(String brd_title) {
		this.brd_title = brd_title;
	}
	public String getBrd_content() {
		return brd_content;
	}
	public void setBrd_content(String brd_content) {
		this.brd_content = brd_content;
	}
	public byte[] getBrd_img() {
		return brd_img;
	}
	public void setBrd_img(byte[] brd_img) {
		this.brd_img = brd_img;
	}
	public String getBrd_id() {
		return brd_id;
	}
	public void setBrd_id(String brd_id) {
		this.brd_id = brd_id;
	}
	public long getBrd_hit() {
		return brd_hit;
	}
	public void setBrd_hit(long brd_hit) {
		this.brd_hit = brd_hit;
	}
	public String getBrd_date() {
		return brd_date;
	}
	public void setBrd_date(String brd_date) {
		this.brd_date = brd_date;
	}
	@Override
	public String toString() {
		return "BoardVO [brd_no=" + brd_no + ", brd_title=" + brd_title + ", brd_content=" + brd_content + ", brd_img="
				+ Arrays.toString(brd_img) + ", brd_id=" + brd_id + ", brd_hit=" + brd_hit + ", brd_date=" + brd_date
				+ "]";
	}


}
