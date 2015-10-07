package com.javiermoreno.kafkatweeteater;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 *
 * @author ciberado
 */
public class TweetProducer {

    public static void main(String[] args) throws IOException {

        Properties props = new Properties();
        props.put("metadata.broker.list", "localhost:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");

        ProducerConfig config = new ProducerConfig(props);

        Producer<String, String> producer = new Producer<>(config);

        try (BufferedReader in = new BufferedReader(new FileReader("../tweets.json"))) {
            String line;
            do  {
                line = in.readLine();
                if (line != null && line.isEmpty() == false) {
                    KeyedMessage<String, String> data = new KeyedMessage<>("tweets", line);
                    producer.send(data);
                }
            } while (line != null);
        }
        producer.close();
    }

}
