package com.chinasofti.YaHui.domain;

import java.io.Serializable;

public class Address implements Serializable{
	private int aid;
	private String address;
	public Address(int aid, String address) {
		super();
		this.aid = aid;
		this.address = address;
	}
	public Address() {
		super();
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return aid+"\t"+address;
	}

}
