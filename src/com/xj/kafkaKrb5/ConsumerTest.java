package com.xj.kafkaKrb5;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
public class ConsumerTest {
    public static void main(String[] args) {
    	  String runTimePath = System.getProperty("user.dir");
    	   System.setProperty("java.security.auth.login.config", runTimePath+"/kafka_client_jaas.conf");
           System.setProperty("java.security.krb5.conf", runTimePath+"/krb5.conf");
      // System.setProperty("java.security.auth.login.config", "C:\\Users\\Administrator\\Desktop\\conf\\kafka_client_jaas.conf"); 
     //  System.setProperty("java.security.krb5.conf", "C:\\Users\\Administrator\\Desktop\\conf\\krb5.conf"); 
        // 环境变量添加，需要输入配置文件的路径System.out.println("===================配置文件地址"+fsPath+"\\conf\\cons_client_jaas.conf"); 
     
        Properties props = new Properties(); 
        props.put("bootstrap.servers", "mf0gybu.neuseer.com:6667"); 
        props.put("group.id", "group_tldZm8BX"); 
        props.put("enable.auto.commit", "false"); 
        props.put("auto.commit.interval.ms", "1000"); 
        props.put("auto.offset.reset", "earliest"); 
        props.put("session.timeout.ms", "30000"); 
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); 
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); 
      
        
 
        /*props.put("security.protocol", "SASL_PLAINTEXT"); 
        props.put("sasl.mechanism", "GSSAPI"); 
        props.put("sasl.kerberos.service.name", "kafka");*/
        
        
        
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.mechanism", "GSSAPI");
        props.put("sasl.mechanism.inter.broker.protocol", "GSSAPI");
        props.put("sasl.kerberos.service.name", "kafka");
        
        
        
        
        KafkaConsumer kafkaConsumer = new KafkaConsumer<>(props); 
        kafkaConsumer.subscribe(Arrays.asList("topic_tldZm8BX"));
        while (true) { 
            ConsumerRecords<String, String> records = kafkaConsumer.poll(1); 
            System.out.println(records);
             for (ConsumerRecord<String, String> record : records)
                 System.out.println("Partition: " + record.partition() + " Offset: " + record.offset() + " Value: " + record.value() + " ThreadID: " + Thread.currentThread().getId());
                
        }
    }
}