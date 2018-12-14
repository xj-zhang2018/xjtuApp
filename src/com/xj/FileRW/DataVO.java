package com.xj.FileRW;

import java.util.Date;

public class DataVO {
	  Date date;
	  String id;
	  String x;
	  String y;
	  String z;
	public DataVO(Date date, String id, String x, String y, String z) {
		super();
		this.date = date;
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getZ() {
		return z;
	}
	public void setZ(String z) {
		this.z = z;
	}
	     
	     
}
