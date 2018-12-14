package com.xj.kafkaKrb5;
	import org.apache.kafka.clients.consumer.ConsumerConfig;
	import org.apache.kafka.clients.consumer.ConsumerRecord;
	import org.apache.kafka.clients.consumer.ConsumerRecords;
	import org.apache.kafka.clients.consumer.KafkaConsumer;	 
	import java.util.Collections;
	import java.util.Properties; 
	public class remoteClient extends Thread { 
	    private KafkaConsumer kafkaConsumer;
	    public remoteClient( String topic ){
	        Properties prop = new Properties();
	        prop.put( ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"123.206.25.169:9092" );  //
	       prop.put(  ConsumerConfig.GROUP_ID_CONFIG,"KafkaConsumerDemo" );    //分组设置
	        prop.put(  ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true" );       //
	        prop.put(  ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000" );   //设置间隔时间
	        prop.put(  ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.IntegerDeserializer"  ); //反序列化的类
	        prop.put(  ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer" );  //
	        prop.put(  ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest" );  //设置接收消息从最前面开始
	        kafkaConsumer = new KafkaConsumer(prop);
	        kafkaConsumer.subscribe(Collections.singletonList(topic));
	       }
	    @Override
	    public void run() {
	 
	        while(true) {
//	            super.run();
	 
	            ConsumerRecords<Integer,String> consumerRecord = kafkaConsumer.poll(1000);
	            for( ConsumerRecord record :consumerRecord ){
	                System.out.println( "message receive:"+record.value() );
	            }
	 
	        }
	 
	    }
	 
	 
	    public static void main(String[] args) {
	 
	        new remoteClient("test").start();
	 
	    }
	}
	  
	 
