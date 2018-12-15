package com.xj.mqtt;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

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
    	 System.out.println("���ӶϿ�������������"); 
    }
 
    public void deliveryComplete(IMqttDeliveryToken token) {
 //System.out.println("deliveryComplete---------" + token.isComplete());
    }
    public void messageArrived(String topic, MqttMessage message) throws Exception {
    	 System.out.println("������Ϣ���� : " + topic);  
         //System.out.println("������ϢQos : " + message.getQos());  
         System.out.println("������Ϣ���� : " +new String(message.getPayload(),"utf-8"));  
         String msg=new String(message.getPayload(),"utf-8");
         String[] args=msg.split(":");
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
        		System.out.println("������"); 
         }
    }
}