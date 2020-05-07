package com.bigdata.kafka;

import com.logs.MarketLogUtils;
import com.logs.MarketLogger;
import com.logs.MessageEntity;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/3/25 15:57
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class KafkaStreamDataHandleThread extends ConsumerHighThread {
   // private IMultiChannelMatchService multiChannelMatchService = new MultiChannelMatchServiceImpl();
   private static final Logger LOGGER = LogManager.getLogger(ConsumerHighThread.class);

    /**
     * @param kafkaStream
     */
    public KafkaStreamDataHandleThread(KafkaStream<byte[], byte[]> kafkaStream) {
        super(kafkaStream);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        KafkaStream<byte[], byte[]> kafkaStream = getKafkaStream();
        ConsumerIterator<byte[], byte[]> it = kafkaStream.iterator();

        MessageAndMetadata<byte[], byte[]> metaData = null;
        byte[] messageByte = null;
        String messageContent = null;
       // MessageEntity basicMsg = null;
        while (it.hasNext()) {
            metaData = it.next();

            messageByte = metaData.message();
            messageContent = new String(messageByte);
            //LOGGER.info(messageContent);
            MessageEntity basicMsg = new MessageEntity();
            basicMsg.setMsgInfo(messageContent);

            MarketLogger.log(MarketLogUtils.format2Log(basicMsg,
                    "", ""));

           // System.out.println("Test--------16:03--->:"+messageContent);
            
/*            basicMsg = MessageEntityFormatter.format(messageContent);
            if (null != basicMsg
                    && CommonUtils.isNotEmpty(basicMsg.getEventTag())
                    && CommonUtils.isNotEmpty(basicMsg.getEventId())
                    && CommonUtils.isNotEmpty(basicMsg.getSerialNumber())) {
                MarketLogger.log(MarketLogUtils.format2Log(basicMsg,
                        TacheCode.MARKET_MATCH,
                        multiChannelMatchService.match(basicMsg)));
            } else {
                MarketLogger.log(MarketLogUtils.format2Log(basicMsg,
                        TacheCode.MARKET_MATCH,
                        SubItemCode.MarketMatch.TS4001));
            }*/
        }

    }
}
