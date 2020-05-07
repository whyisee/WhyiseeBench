package com.logs;

import java.io.Serializable;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/3/27 14:59
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class MessageEntity implements Serializable {
    /**
     * @Fields serialVersionUID : 序列号
     */
    private static final long serialVersionUID = -1726172550678509683L;

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }

    private String msgInfo;

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(msgInfo);
        return sb.toString();

    }

}
