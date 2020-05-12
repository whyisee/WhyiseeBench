package com.bigdata.zookeeper;

import com.nimbusds.jose.util.ByteUtils;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/5/12 15:48
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class ZSession {
    public static long initializeNextSession(long id){
        long nextSid = 0;
        nextSid = (System.currentTimeMillis() << 24) >> 8;
        nextSid = nextSid | (id <<56);
        return nextSid;
    }
    public static byte[] longToByte8(long sum) {
        byte[] arr = new byte[8];
        arr[0] = (byte) (sum >> 56);
        arr[1] = (byte) (sum >> 48);
        arr[2] = (byte) (sum >> 40);
        arr[3] = (byte) (sum >> 32);
        arr[4] = (byte) (sum >> 24);
        arr[5] = (byte) (sum >> 16);
        arr[6] = (byte) (sum >> 8);
        arr[7] = (byte) (sum & 0xff);
        return arr;
    }
    private static String toBinary(long n, int target) {
        String s = "";
        while (n != 0) {
            s = n % target + s;
            n = n / target;
        }
        return s;
    }
    
    public static void main(String args[]){
        long ctime = System.currentTimeMillis();
        //Integer.toBinaryString(ctime);
        //Integer.

        System.out.println("Test--------16:04--->:"+ctime);
        System.out.printf("%64s",toBinary(ctime,2));
    }
}
