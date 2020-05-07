package com.bigdata.kafka;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.Properties;
import java.util.concurrent.ExecutorService;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/3/22 16:35
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class BaseConsumer {
    public final ConsumerConnector consumer;
    public ExecutorService executor;

    /**
     * 创建公共kafka配置信息
     *
     * @param 配置参数
     */
    public BaseConsumer(Properties consumerConfigProps) {
        consumer = Consumer.createJavaConsumerConnector(createConsumerConfig(consumerConfigProps));
    }

    public void shutdown() {
        if (consumer != null) {
            consumer.shutdown();
        }
        if (executor != null) {
            executor.shutdown();
        }
    }

    /**
     * 生成消费者配置
     *
     * @param a_zookeeper
     * @param a_groupId
     * @return
     */
    private static ConsumerConfig createConsumerConfig(Properties props) {
        return new ConsumerConfig(props);
    }
}
