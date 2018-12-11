package com.xj.kafkaKrb5;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;



public class Comsumer {
    private static String TOPIC_NAME = "topic_tldZm8BX";

    public static void main(String[] args) {

    	 String str1=Thread.currentThread().getContextClassLoader().getResource("krb5.conf").getPath();
         System.out.println(str1.substring(1, str1.length()));
     
  	System.setProperty("java.security.krb5.conf",str1);
     
    	
    	
    	
    	
      
        //初始化jaas.conf文件
        String str=Thread.currentThread().getContextClassLoader().getResource("u_kfk_tldZm8BX.keytab").getPath();
        System.out.println(str.substring(1, str.length()));
        MyProperties.configureJAAS(str, "u_kfk_tldZm8BX@NEUSEER.COM");

        System.setProperty("javax.security.auth.useSubjectCredsOnly", "false");

        MyProperties props = MyProperties.initKerberos();

        props.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "mf0gybu.neuseer.com:6667");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(
                props.getProperties());
        consumer.subscribe(Arrays.asList(TOPIC_NAME));
        /*
         * TopicPartition partition0= new TopicPartition(TOPIC_NAME, 0);
         *
         * TopicPartition partition1= new TopicPartition(TOPIC_NAME, 1);
         *
         * TopicPartition partition2= new TopicPartition(TOPIC_NAME, 2);
         */

        // consumer.assign(Arrays.asList(partition0,partition1, partition2));

        ConsumerRecords<String, String> records = null;
        
        while (true) {
            try {
                Thread.sleep(1000);

                System.out.println();
                records = consumer.poll(Long.MAX_VALUE);

                for (ConsumerRecord<String, String> record : records) {

                    System.out.println("Receivedmessage: (" + record.key()
                            + "," + record.value() + ") at offset "
                            + record.offset());

                }

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

      

    }
}
