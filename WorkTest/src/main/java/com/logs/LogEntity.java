package com.logs;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/3/27 14:42
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class LogEntity {
    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }

    private String msgInfo;

    private String format(String logRecordTime) {
        try {
            long mills = Long.parseLong(logRecordTime);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            return sdf.format(new Date(mills));
        } catch (Exception e) {

        }
        return logRecordTime;
    }
    public String toString() {
        StringBuffer logBuf = new StringBuffer();

        logBuf.append(msgInfo);
        return logBuf.toString();
    }

}
