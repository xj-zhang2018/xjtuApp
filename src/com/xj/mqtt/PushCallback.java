package com.xj.mqtt;

import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.List;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xj.persistence.Dao;
import com.xj.persistence.Parameter;

class PushCallback implements MqttCallback {
	private String threadId;
	MqttClient client;
	Dao db;
	
    public PushCallback(MqttClient client) {
    	db = Dao.getInstance();
    	this.client=client;
	}


	public void connectionLost(Throwable cause) {
    	 System.out.println("断开连接"); 
    }
 
    public void deliveryComplete(IMqttDeliveryToken token) {
 //System.out.println("deliveryComplete---------" + token.isComplete());
    }
    public void messageArrived(String topic, MqttMessage message) throws Exception {
    	 System.out.println("topic: " + topic);  
         //System.out.println("Qos : " + message.getQos());  
         
         String msg=new String(message.getPayload(),"utf-8");
        
         System.out.println("接收到数据是"+msg);
         
         if(!msg.equals("close")) {
         JSONObject jsonObject = JSON.parseObject(msg);
         long timeStamp=jsonObject.getLongValue("timeStamp");
         Timestamp time = new Timestamp(timeStamp);
         
         
         String datapoint=jsonObject.getString("datapoint");
         String deviceId=jsonObject.getString("deviceId");
         double value=jsonObject.getDouble("value");
        
         String sql="insert into dataEntity(timeStamp,datapoint,deviceId,value) value (?,?,?,?)";
        dataEntity data=new dataEntity(time, datapoint, deviceId, value);
        db.insertoneRecord(sql, data);
         }
         
         
         
         
         
         
         
    /*     String[] args=msg.split(":");
         if(args.length==2)
          {
        String sql = "insert into entity (id,name) value (?,?)";
         List<Parameter>param=new ArrayList<>();
         //param.add(new Parameter(Integer.parseInt(args[0]),args[1]));
         //db.insertBatch(sql, param);
         }else {//�ַ���ð�Ÿ�������������
        	if(msg!=null&&!msg.equals("close"))
        		this.client.disconnect();
        	else 
        		System.out.println("空数据"); 
         }*/
    }
}