package com.chinasofti.YaHui.domain;

import java.io.Serializable;

public class Food implements Serializable{
	private int fId;
	private String fName;
	private double fPrice;
	private int fNum;
	private FoodType ftype;
	public Food(int fId, String fName, double fPrice, int fNum, FoodType ftype) {
		super();
		this.fId = fId;
		this.fName = fName;
		this.fPrice = fPrice;
		this.fNum = fNum;
		this.ftype = ftype;
	}
	
	public Food(int fId, String fName, double fPrice) {
		super();
		this.fId = fId;
		this.fName = fName;
		this.fPrice = fPrice;
	}

	public int getfId() {
		return fId;
	}
	public void setfId(int fId) {
		this.fId = fId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public double getfPrice() {
		return fPrice;
	}
	public void setfPrice(double fPrice) {
		this.fPrice = fPrice;
	}
	public int getfNum() {
		return fNum;
	}
	public void setfNum(int fNum) {
		this.fNum = fNum;
	}
	public FoodType getFtype() {
		return ftype;
	}
	public void setFtype(FoodType ftype) {
		this.ftype = ftype;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return fId+"\t"+fName+"\t"+fPrice+"\t"+fNum+"\t"+ftype.getTypeId();
	}

}
