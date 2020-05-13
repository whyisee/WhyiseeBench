package com.bigdata.zookeeper.jute;

import org.apache.avro.util.ByteBufferInputStream;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.zookeeper.server.persistence.FileTxnLog;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/5/12 10:40
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class MainTest {
    public static void main(String args[]) throws IOException {
        // ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // BinaryOutputArchive boa = BinaryOutputArchive.getArchive(baos);
        // new MockReqHeader(1231223,"ping").serialize(boa,"header");
        // ByteBuffer bb = ByteBuffer.wrap(baos.toByteArray());
        // DataOutputStream out=new DataOutputStream(new FileOutputStream("F:\\git\\WhyiseeBench\\WorkTest\\data\\test.dat"));
        // out.write(bb.array());
        // out.close();
        FileInputStream in = new FileInputStream("F:\\git\\WhyiseeBench\\WorkTest\\data\\test.dat");
        //ByteBufferin.read();
        //ByteBuffer bb = ByteBuffer.wrap(in.);
        BufferedReader br = new BufferedReader(new FileReader("F:\\git\\WhyiseeBench\\WorkTest\\data\\test.dat"));
        //ByteArrayInputStream = new ByteArrayInputStream();
        byte[] tempbytes = new byte[100];
        int byteread = 0;
        while ((byteread = in.read(tempbytes))!=-1){
            System.out.write(tempbytes, 0, byteread);

        }

        System.out.println("Test--------10:45--->:"+new String(tempbytes));

        ByteArrayInputStream bbis = new ByteArrayInputStream( tempbytes);
        BinaryInputArchive bbia = BinaryInputArchive.getArchive(bbis);
        MockReqHeader head2 = new MockReqHeader();
        head2.deserialize(bbia,"header");
        System.out.println("Test--------10:51--->:"+head2.toString());
        bbis.close();
        in.close();
        //FileTxnLog
        //baos.close();


    }
}
