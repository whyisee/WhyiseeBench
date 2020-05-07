package com.bigdata.kafka;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/3/25 11:37
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ConsumerConfigLoader {
    private static final Logger LOGGER = LogManager.getLogger(ConsumerConfigLoader.class);

    private static final String FILE_NAME_CONSUMER_CONFIG = "kafka-consumer-config.properties";
    private static final String FILE_NAME_CONSUMER_TOPICS = "kafka-consume-topic.properties";
    private static final String BASE_PATH = "config/";
    private Properties consumerConfigProps;
    private Properties topicProps;

    private ConsumerConfigLoader() {
        consumerConfigProps = readPropertiesFile(FILE_NAME_CONSUMER_CONFIG);
        topicProps = readPropertiesFile(FILE_NAME_CONSUMER_TOPICS);
    }

    /**
     * 根据配置文件名称从类路径下加载properties文件
     *
     * @param fileName
     * @return
     */
    private Properties readPropertiesFile(String fileName) {
        Properties props = new Properties();
        InputStream in = null;
        try {
            LOGGER.info("Start to load properties from file [" + fileName + "] ...");
            //String filePath = ConsumerConfigLoader.class.getClassLoader().getResource(fileName).getPath();
            in = new FileInputStream(new File(BASE_PATH + fileName));
            props.load(in);
            LOGGER.info("Load properties from file [" + fileName + "] finished.");
        } catch (Exception e) {
            LOGGER.error("Failed to properties from file [" + fileName + "].", e);
        }
        return props;
    }

    /**
     * 加载消费者zk连接等配置信息
     *
     * @return
     */
    public Properties loadConsumerConfig() {
        return consumerConfigProps;
    }

    /**
     * 加载消费者主题信息
     *
     * @return <主题名称, 消费者数量>
     */
    public Map<String, Integer> loadTopicMap() {
        Map<String, Integer> topicMap = new HashMap<String, Integer>();
        if (null != topicProps && !topicProps.isEmpty()) {
            Set<Object> topicNameSet = topicProps.keySet();
            try {
            } catch (NumberFormatException ex) {
                LOGGER.error("The number of consumers must be an integer value.", ex);
            }
            for (Object obj : topicNameSet) {
                String topicName = (String) obj;
                topicMap.put(topicName, Integer.parseInt(topicProps.getProperty(topicName)));
            }
        }
        return topicMap;
    }

    private static class ConfigLoaderHolder {
        private static ConsumerConfigLoader instance = new ConsumerConfigLoader();
    }

    /**
     * 获取消费者配置信息加载器实例
     *
     * @return
     */
    public static ConsumerConfigLoader getInstance() {
        return ConfigLoaderHolder.instance;
    }
}
