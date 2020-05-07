package com.logs;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/3/27 14:59
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public final class MarketLogUtils {
    /**
     * 插入kafka的业务日志
     */

    private MarketLogUtils() {
    }

    /**
     * 格式化业务日志信息
     */
    public static LogEntity format2Log(MessageEntity msgEntity, String tacheId, String subItemId) {

        if (null != msgEntity) {
            LogEntity log = new LogEntity();

            log.setMsgInfo(msgEntity.getMsgInfo());
           // log.setLogRecordTime(String.valueOf(System.currentTimeMillis()));

            return log;
        }
        return null;
    }

    /**
     * 格式化业务日志信息
     */



}
