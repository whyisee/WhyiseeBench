package com.passwd;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/10/25 15:55
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESCode {
    private static String ALGORITHM_TYPE = "DES";
    private static final byte[] hex = "0123456789ABCDEF".getBytes();
    private byte[] keyByte;

    public DESCode(String s) {
        this.keyByte = HexString2Bytes(s);
    }

    public DESCode(byte[] abyte0) {
        this.keyByte = abyte0;
    }

    public static byte[] HexString2Bytes(String s) {
        byte[] abyte0 = new byte[s.length() / 2];
        int i = 0;

        for(int j = 0; j < abyte0.length; ++j) {
            char c = s.charAt(i++);
            char c1 = s.charAt(i++);
            abyte0[j] = (byte)(parse(c) << 4 | parse(c1));
        }

        return abyte0;
    }

    public static String bytes2HexString(byte[] abyte0) {
        byte[] abyte1 = new byte[2 * abyte0.length];

        for(int i = 0; i < abyte0.length; ++i) {
            abyte1[2 * i] = hex[abyte0[i] >> 4 & 15];
            abyte1[2 * i + 1] = hex[abyte0[i] & 15];
        }

        return new String(abyte1);
    }

    private static int parse(char c) {
        if (c >= 'a') {
            return c - 97 + 10 & 15;
        } else {
            return c >= 'A' ? c - 65 + 10 & 15 : c - 48 & 15;
        }
    }

    public Cipher init(int i) throws Exception {
        SecureRandom securerandom = new SecureRandom();
        DESKeySpec deskeyspec = new DESKeySpec(this.keyByte);
        SecretKey secretkey = SecretKeyFactory.getInstance(ALGORITHM_TYPE).generateSecret(deskeyspec);
        Cipher cipher = Cipher.getInstance(ALGORITHM_TYPE);
        cipher.init(i, secretkey, securerandom);
        return cipher;
    }

    public  String desEncoding(String s) throws Exception {
        Cipher cipher = this.init(1);
        byte[] abyte0 = cipher.doFinal(s.getBytes());
        return bytes2HexString(abyte0);
    }

    public byte[] desEncoding(byte[] abyte0) throws Exception {
        Cipher cipher = this.init(1);
        byte[] abyte1 = cipher.doFinal(abyte0);
        return abyte1;
    }

    public String desDecoding(String s) throws Exception {
        Cipher cipher = this.init(2);
        byte[] abyte0 = cipher.doFinal(HexString2Bytes(s));
        return new String(abyte0);
    }

    public byte[] desDecoding(byte[] abyte0) throws Exception {
        Cipher cipher = this.init(2);
        byte[] abyte1 = cipher.doFinal(abyte0);
        return abyte1;
    }
    public static void main(String [] arg){
    }
}
