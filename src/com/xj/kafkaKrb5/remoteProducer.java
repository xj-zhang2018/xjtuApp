package com.xj.kafkaKrb5;

import kafka.Kafka;
import net.sf.json.JSONObject;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.xj.FileRW.DataVO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class remoteProducer extends Thread{

   private  KafkaProducer<Integer,String> producer;

   private  String topic;

   public remoteProducer(String topic){

       Properties properties = new Properties();
       properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"123.206.25.169:9092" );  //设置kafka的连接和端口
       properties.put( ProducerConfig.CLIENT_ID_CONFIG,"KafkaProducerDemo" );  //
       properties.put( ProducerConfig.ACKS_CONFIG,"-1" );
       properties.put(  ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.IntegerSerializer" );
       properties.put(  ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer" );
       producer = new KafkaProducer<Integer,String>( properties );
       this.topic = topic;

   }


   @Override
   public void run(){

       int num = 0;
       SimpleDateFormat dateFormat = new  SimpleDateFormat("yyyy/MM/dd HH:mm");
		  
       try { 
           BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\工业app大赛\\test.csv"));//换成你的文件名
           reader.readLine();//第一行信息，为标题信息，不用，如果需要，注释掉
           String line = null; 
           while((line=reader.readLine())!=null){ 
               String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
               if(item.length==5) {
                Date date = dateFormat.parse(item[0]);
                DataVO vo=new DataVO(date, item[1], item[2],item[3],item[4]);
                JSONObject message= JSONObject.fromObject(vo);
                System.out.println(message.toString());
                producer.send( new ProducerRecord<Integer,String>(topic,message.toString()));
                System.out.println("已发送");
                Thread.sleep(1000);
           } 
               }
       } catch (Exception e) { 
           e.printStackTrace(); 
       } 
   
       

   }



   public static void main(String[] args) {
	  new remoteProducer("test").start();
	 
   }

}