package com.xj.mqtt;






import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class dataEntity {

	//{"timeStamp":1545052174400,"datapoint":"unit_num","deviceId":"w1h1anj69d","value":100.0}
	
	Timestamp timeStamp;
	String datapoint;
	String deviceId;
	double value;
	
	
	
	public dataEntity(Timestamp timeStamp, String datapoint, String deviceId, double value) {
		super();
		this.timeStamp = timeStamp;
		this.datapoint = datapoint;
		this.deviceId = deviceId;
		this.value = value;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getDatapoint() {
		return datapoint;
	}
	public void setDatapoint(String datapoint) {
		this.datapoint = datapoint;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.datapoint+","+this.value;
	}
	
	public static void main(String[] args) {
		String str="{\"timeStamp\":1545052174400,\"datapoint\":\"unit_num\",\"deviceId\":\"w1h1anj69d\",\"value\":100.0}";
	
		String stringEntity = "{'consno':'100000030060','consaddr':'北京市海淀区中关村'}";
		JSONObject jsonObject = JSON.parseObject(str);
       // System.out.println(jsonObject.get("consno"));
       // System.out.println(jsonObject.get("consaddr"));
       /* System.out.println(jsonObject.getLong("timeStamp"));
        System.out.println(jsonObject.getString("datapoint"));
        System.out.println(jsonObject.getString("deviceId"));
        System.out.println(jsonObject.getDouble("value"));
        */
        
        
        long timestamp=1545055383139l;
       
       Timestamp t = new Timestamp(timestamp);
        System.out.println(t);
	}
	
	
	
	
	
	
	
	
}
