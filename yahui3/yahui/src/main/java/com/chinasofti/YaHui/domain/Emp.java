package com.chinasofti.YaHui.domain;

import java.io.Serializable;

public class Emp implements Serializable{
	private int eid;
	private String ename;
	private String epasswd;
	private String sex;
	private int epower;
	private Address a;
	private int assess;
	
	public Emp() {
		super();
	}

	public Emp(int eid, String ename, String epasswd,  String sex, int epower, Address a, int assess) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.epasswd = epasswd;
		this.sex = sex;
		this.epower = epower;
		this.a = a;
		this.assess = assess;
	}
	
	public Emp(int eid) {
		super();
		this.eid = eid;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEpasswd() {
		return epasswd;
	}

	public void setEpasswd(String epasswd) {
		this.epasswd = epasswd;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getEpower() {
		return epower;
	}

	public void setEpower(int epower) {
		this.epower = epower;
	}

	public Address getAddress() {
		return a;
	}

	public void setAddress(Address a) {
		this.a = a;
	}

	public int getAssess() {
		return assess;
	}

	public void setAssess(int assess) {
		this.assess = assess;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return eid+"\t"+ename+"\t"+sex+"\t"+epower+"\t"+this.getAddress().getAid()+"\t"+assess;
	}

}
