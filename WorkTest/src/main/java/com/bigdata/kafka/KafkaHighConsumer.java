package com.bigdata.kafka;

import kafka.consumer.KafkaStream;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/3/25 11:46
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class KafkaHighConsumer extends BaseConsumer {
    public KafkaHighConsumer(Properties props) {
        super(props);
    }

    /**
     * 创建并发的consumers
     *
     * @param a_numThreads
     * @throws IllegalAccessException
     * @throws Exception
     */
    public void run(Map<String, Integer> topicCountMap, Class<? extends ConsumerHighThread> consumerClass)
            throws Exception {
        // 根据主题以及消费者数量创建KAFKA消息流
        // 每个主题对应的消费者数量不应超出其分区数，否则多出的消费者会消费不到数据，造成不必要的资源浪费
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        for (Map.Entry<String, List<KafkaStream<byte[], byte[]>>> item : consumerMap.entrySet()) {
            String topic = item.getKey();
            List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
            if (null == streams) {
                Thread.sleep(300);
            }
            // 创建与消费者数等大小的线程池
            System.out.println(streams.size());
            executor = Executors.newFixedThreadPool(streams.size());
            // 每个消费者消费到的数据流交由对应的一个线程进行处理
            for (final KafkaStream<byte[], byte[]> stream : streams) {
                Class<?>[] paramTypes = { KafkaStream.class };
                Object[] params = { stream };
                Constructor<? extends ConsumerHighThread> con = consumerClass.getConstructor(paramTypes);
                ConsumerHighThread base = con.newInstance(params);
                executor.submit(base);
            }
        }
    }
}
