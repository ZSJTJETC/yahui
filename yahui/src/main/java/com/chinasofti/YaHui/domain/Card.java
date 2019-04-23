package com.chinasofti.YaHui.domain;

import java.io.Serializable;

public class Card implements Serializable{
	private int cid;
	private String username;
	private String cpasswd;
	private VIP vip;
	private double cmoney;
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCpasswd() {
		return cpasswd;
	}

	public void setCpasswd(String cpasswd) {
		this.cpasswd = cpasswd;
	}

	public VIP getVip() {
		return vip;
	}

	public void setVip(VIP vip) {
		this.vip = vip;
	}

	public double getCmoney() {
		return cmoney;
	}

	public void setCmoney(double cmoney) {
		this.cmoney = cmoney;
	}

	public Card(int cid, String username, String cpasswd, VIP vip, double cmoney) {
		super();
		this.cid = cid;
		this.username = username;
		this.cpasswd = cpasswd;
		this.vip = vip;
		this.cmoney = cmoney;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return cid+"\t"+username+"\t"+vip.getVid()+"\t"+cmoney;
	}

}
