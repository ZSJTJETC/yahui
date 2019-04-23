package com.chinasofti.YaHui.domain;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	private int oID;
	private Date oDate;
	private double oMoney;
	private Emp emp;
	private Address address;
	public Order(int oID, Date oDate, double oMoney, Emp emp, Address address) {
		super();
		this.oID = oID;
		this.oDate = oDate;
		this.oMoney = oMoney;
		this.emp = emp;
		this.address = address;
	}
	public int getoID() {
		return oID;
	}
	public void setoId(int oId) {
		this.oID = oId;
	}
	public Date getoDate() {
		return oDate;
	}
	public void setoDate(Date oDate) {
		this.oDate = oDate;
	}
	public double getoMoney() {
		return oMoney;
	}
	public void setoMoney(double oMoney) {
		this.oMoney = oMoney;
	}
	public Emp getEmp() {
		return emp;
	}
	public void setEmp(Emp emp) {
		this.emp = emp;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return oID+"\t"+oDate+"\t"+oMoney+"\t"+emp.getEid()+"\t"+address.getAid();
	}

}
