package com.chinasofti.YaHui.domain;

import java.io.Serializable;

public class FoodType implements Serializable{
	private int typeId;
	private String typeName;
	public FoodType(int typeId, String typeName) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
	}
	public FoodType() {
		super();
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return typeId+"\t"+typeName;
	}

}
