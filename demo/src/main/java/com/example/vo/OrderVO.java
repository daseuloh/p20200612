package com.example.vo;

public class OrderVO {
	
	private int itemno = 0;
	private String itemname = null;
	private int itemprice =0;
	private int itemqty=0;
	private int ordercnt = 0;
	
	@Override
	public String toString() {
		return "OrderVO [itemno=" + itemno + ", itemname=" + itemname + ", itemprice=" + itemprice + ", itemqty="
				+ itemqty + ", ordercnt=" + ordercnt + "]";
	}
	
	
	public int getItemno() {
		return itemno;
	}
	public void setItemno(int itemno) {
		this.itemno = itemno;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public int getItemprice() {
		return itemprice;
	}
	public void setItemprice(int itemprice) {
		this.itemprice = itemprice;
	}
	public int getItemqty() {
		return itemqty;
	}
	public void setItemqty(int itemqty) {
		this.itemqty = itemqty;
	}
	public int getOrdercnt() {
		return ordercnt;
	}
	public void setOrdercnt(int ordercnt) {
		this.ordercnt = ordercnt;
	}



}
