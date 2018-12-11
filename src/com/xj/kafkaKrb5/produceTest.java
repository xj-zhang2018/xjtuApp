package com.xj.kafkaKrb5;

import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class produceTest {
	
	public static void main(String[] args) {
		

	
	System.setProperty("java.security.auth.login.config", "C:\\Users\\Administrator\\Desktop\\conf\\kafka_client_jaas.conf"); 
       System.setProperty("java.security.krb5.conf", "C:\\Users\\Administrator\\Desktop\\conf\\krb5.conf"); 
        // 环境变量添加，需要输入配置文件的路径System.out.println("===================配置文件地址"+fsPath+"\\conf\\cons_client_jaas.conf"); 
     
        Properties props = new Properties(); 
        props.put("bootstrap.servers", "mf0gybu.neuseer.com:6667"); 
        props.put("group.id", "group_tldZm8BX"); 
        props.put("enable.auto.commit", "false"); 
     
        props.put("auto.offset.reset", "earliest"); 
       
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); 
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); 
      
        
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        
        
        
        props.put("spring.kafka.properties.security.protocol", "SASL_PLAINTEXT");
        props.put("spring.kafka.properties.sasl.mechanism", "GSSAPI");
        props.put("spring.kafka.properties.sasl.mechanism.inter.broker.protocol", "GSSAPI");
        props.put("spring.kafka.properties.sasl.kerberos.service.name", "kafka");
        
        
        
        
        
        
        KafkaProducer<String, String>producer=new KafkaProducer<>(props);
        for (int i = 0; i < 10; i++) {

            String key = "key-" + i;

            String message = "Message-" + i;

            ProducerRecord record = new ProducerRecord<String, String>(
                    "topic_tldZm8BX", key, message);

            producer.send(record);

            System.out.println(key + "----" + message);

        }

        producer.close();
        
        
        
        
	
	}
	
	
}
