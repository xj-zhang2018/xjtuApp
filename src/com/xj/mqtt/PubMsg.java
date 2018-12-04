package com.xj.mqtt;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
 
/**
 * 鍙戯拷?锟芥暟鎹埌mqtt鏈嶅姟锟�?
聽* @author锛氭秱锟�?
聽* @date 2017锟�?8锟�?16锟�? 涓嬪崍11:15:22
 */
public class PubMsg {
	private static int qos = 2; //鍙湁锟�?锟�?
	private static String broker = "tcp://newmqtt.neuseer.com:31181";
	private static String userName = "zxj";
	private static String passWord = "junjun123";
 
	
	private static MqttClient connect(String clientId,String userName,
			String password) throws MqttException {
		MemoryPersistence persistence = new MemoryPersistence();
		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);
		connOpts.setUserName(userName);
		connOpts.setPassword(password.toCharArray());
		connOpts.setConnectionTimeout(10);
		connOpts.setKeepAliveInterval(20);
//		String[] uris = {"tcp://10.100.124.206:1883","tcp://10.100.124.207:1883"};
//		connOpts.setServerURIs(uris);  //璧峰埌璐熻浇鍧囪　鍜岄珮鍙敤鐨勪綔锟�?
		MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
		//mqttClient.setCallback(new PushCallback());
		mqttClient.connect(connOpts);
		return mqttClient;
	}
 
	private static void pub(MqttClient sampleClient, String msg,String topic) 
			throws MqttPersistenceException, MqttException {
		MqttMessage message = new MqttMessage(msg.getBytes());
		message.setQos(qos);
		message.setRetained(false);
		sampleClient.publish(topic, message);
	}
	
	
	private static void publish(String str,String clientId,String topic) throws MqttException{
		MqttClient mqttClient = connect(clientId,userName,passWord);
 
		if (mqttClient != null) {
			pub(mqttClient, str, topic);
			System.out.println("pub-->" + str);
		}
 
		if (mqttClient != null) {
			mqttClient.disconnect();
		}
	}
 
	public static void main(String[] args) throws MqttException {
		int id=0;
		while(true) {
			id++;
			publish(id+":zxj:test","client-id-0","share/edge/server/public/a");
		   if(id>10)break;
		}
	}
}
 




