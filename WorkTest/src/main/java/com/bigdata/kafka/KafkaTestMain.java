package com.bigdata.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.lang.reflect.Constructor;
import java.util.Map;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/3/25 11:35
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class KafkaTestMain {
    public static void main(String args[]) throws Exception {

        KafkaHighConsumer consumer = new KafkaHighConsumer(ConsumerConfigLoader.getInstance().loadConsumerConfig());
        Map<String, Integer> allTopicMap = ConsumerConfigLoader.getInstance().loadTopicMap();

        consumer.run(allTopicMap, KafkaStreamDataHandleThread.class);

    }
}
