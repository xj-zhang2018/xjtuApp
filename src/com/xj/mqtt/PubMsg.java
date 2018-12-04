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
 * 发�?�数据到mqtt服务�?
 * @author：涂�?
 * @date 2017�?8�?16�? 下午11:15:22
 */
public class PubMsg {
	private static int qos = 2; //只有�?�?
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
//		connOpts.setServerURIs(uris);  //起到负载均衡和高可用的作�?
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
			publish(id+":zxj","client-id-0","share/edge/server/public/a");
		   if(id>1000)break;
		}
	}
}
 




