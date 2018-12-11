package com.xj.kafkaKrb5;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;



public class Producer {
    //����topic
    public static String TOPIC_NAME = "topic_tldZm8BX";

    public static void main(String[] args) {
    	   String str1=Thread.currentThread().getContextClassLoader().getResource("krb5.conf").getPath();
           System.out.println(str1.substring(1, str1.length()));
       
    	System.setProperty("java.security.krb5.conf",str1);
       
        
        
        
        
        //��ʼ��jaas.conf�ļ�
        String str=Thread.currentThread().getContextClassLoader().getResource("u_kfk_tldZm8BX.keytab").getPath();
        System.out.println(str.substring(1, str.length()));
        
        
        MyProperties.configureJAAS(str, "u_kfk_tldZm8BX@NEUSEER.COM");
        
        System.setProperty("javax.security.auth.useSubjectCredsOnly", "false");

        //System.setProperty("sun.security.krb5.debug","true");
        
        //��ʼ��kerberos����
        MyProperties props = MyProperties.initProducer();
        
        //kafka brokers��ַ
        props.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "mf0gybu.neuseer.com:6667");
        
    
        
       

        org.apache.kafka.clients.producer.Producer<String, String> producer = new KafkaProducer<String, String>(
                props.getProperties());

        for (int i = 0; i < 10; i++) {

            String key = "key-" + i;

            String message = "Message-" + i;

            ProducerRecord record = new ProducerRecord<String, String>(
                    TOPIC_NAME, key, message);

            producer.send(record);

            System.out.println(key + "----" + message);

        }

        producer.close();

    }
}
