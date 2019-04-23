package com.chinasofti.YaHui.domain;

import java.io.Serializable;

public class Shopping implements Serializable{
	private String fname;
	private double fprice;
	private int fsnum;
	public Shopping(String fname, double fprice, int n) {
		super();
		this.fname = fname;
		this.fprice = fprice;
		this.fsnum = fsnum;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public double getFprice() {
		return fprice;
	}
	public void setFprice(double fprice) {
		this.fprice = fprice;
	}
	public int getFsnum() {
		return fsnum;
	}
	public void setFsnum(int fsnum) {
		this.fsnum = fsnum;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return fname+"\t"+fprice+"\t"+fsnum;
	}
}
