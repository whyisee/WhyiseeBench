package com.bigdata.zookeeper.jute;

import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;

import java.io.IOException;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/5/12 10:20
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */

/**
 * use for :
 *@author zoukh
 *@Created in:  2020/5/12 10:29
 *@Modified By:
 *@version 1.0
 *@used in: MockReqHeader
 */
public class MockReqHeader implements Record {



    private long sessionId;
    private String type;

    public MockReqHeader (){}
    public MockReqHeader (long sessionId,String type){
        this.sessionId = sessionId;
        this.type = type;
    }

    @Override
    public void serialize(OutputArchive outputArchive, String s) throws IOException {
        outputArchive.startRecord(this,s);
        outputArchive.writeLong(sessionId,"sessionId");
        outputArchive.writeString(type,"type");
        outputArchive.endRecord(this,s);
    }



    @Override
    public void deserialize(InputArchive inputArchive, String s) throws IOException {
        inputArchive.startRecord(s);
        sessionId = inputArchive.readLong("session");
        type = inputArchive.readString("type");
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString(){
        return "sessionId:"+sessionId+" type:"+type;
    }
}
