package com.chinasofti.YaHui.domain;

import java.io.Serializable;

public class VIP implements Serializable{
	private int vid;
	private String vname;
	private double vDiscount;
	public VIP(int vid, String vname, double vDiscount) {
		super();
		this.vid = vid;
		this.vname = vname;
		this.vDiscount = vDiscount;
	}
	public VIP() {
		super();
	}
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public double getvDiscount() {
		return vDiscount;
	}
	public void setvDiscount(double vDiscount) {
		this.vDiscount = vDiscount;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return vid+"\t"+vname+"\t"+vDiscount;
	}

}
