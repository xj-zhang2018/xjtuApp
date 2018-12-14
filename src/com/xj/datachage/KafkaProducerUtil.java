package com.xj.datachage;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @Author: fei2
 * @Date:2018/6/12 17:23
 * @Description: kafka 发送数据，模拟数据源
 * @Refer To:
 */

public class KafkaProducerUtil {
    private static Properties props;
    private static KafkaProducer<Integer, String> producer ;
    //mf0gybu.neuseer.com:6667
    private static String broker = "mf0gybu.neuseer.com:6667";
    static {
        props = new Properties();
        props.put("bootstrap.servers", broker);
        props.put("acks", "-1");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    
        String runTimePath = System.getProperty("user.dir");
       System.out.println("路径是"+runTimePath);
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.kerberos.service.name", "kafka");
        props.put("group.id", "group_tldZm8BX"); 
        
        
        props.put("sasl.mechanism", "GSSAPI");
        props.put("sasl.mechanism.inter.broker.protocol", "GSSAPI");
       
        
        System.setProperty("java.security.auth.login.config", runTimePath+"/kafka_client_jaas.conf");
        System.setProperty("java.security.krb5.conf", runTimePath+"/krb5.conf");
        producer = new KafkaProducer<>(props);
    }
    
    public static void sendToKafka(String topic, String value) {
        producer.send(new ProducerRecord<>(topic, value)
                /*,new DemoCallBack(System.currentTimeMillis(),1,value)*/);
       producer.flush();
       producer.close();
        System.out.println("发送完毕");
    }
     
    public static void main(String[] args) {
    	sendToKafka("topic_tldZm8BX","sommessage test");
	}
    
    
    
}