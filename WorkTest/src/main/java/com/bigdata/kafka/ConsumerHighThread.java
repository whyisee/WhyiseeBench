package com.bigdata.kafka;

import kafka.consumer.KafkaStream;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/3/25 11:46
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public abstract class ConsumerHighThread implements Runnable {

    private KafkaStream<byte[], byte[]> kafkaStream;

    public ConsumerHighThread(KafkaStream<byte[], byte[]> kafkaStream) {
        this.kafkaStream = kafkaStream;
    }

    public KafkaStream<byte[], byte[]> getKafkaStream() {
        return kafkaStream;
    }
}
