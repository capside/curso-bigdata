package com.javiermoreno.kafkatweeteater;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 *
 * @author ciberado
 */
public class TweetConsumer {

    private final ConsumerConnector consumer;
    private final String topic;

    public TweetConsumer(String topic) {
        Properties props = new Properties();
        props.put("zookeeper.connect", "localhost:2181");
        props.put("group.id", "miAplicacion");
        props.put("zookeeper.session.timeout.ms", "400");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");

        ConsumerConfig config = new ConsumerConfig(props);
        consumer = Consumer.createJavaConsumerConnector(config);
        
        this.topic = topic;
    }


    public void ejecutar() {
        Map<String, Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(topic, 1 /* número de streams deseados */);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap
                = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
        ConsumerIterator<byte[], byte[]> it = streams.get(0).iterator();
        while (it.hasNext()) {
            byte[] msg = it.next().message();
            System.out.println(new String(msg));
        }
    }

    public void apagar() {
        if (consumer != null) {
            consumer.shutdown();
        }
    }
    
    public static void main(String[] args) {
        TweetConsumer tc = new TweetConsumer("tweets");
        tc.ejecutar();
        tc.apagar();
    }

}
