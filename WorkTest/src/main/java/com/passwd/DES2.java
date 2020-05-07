package com.passwd;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/1/8 16:14
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
import com.sun.crypto.provider.SunJCE;

import java.io.IOException;
import java.io.PrintStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Calendar;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DES2 {
    private String Algorithm = "DES";
    private SecretKey deskey;
    private Cipher cipher;
    private byte[] encryptorData;
    private byte[] decryptorData;

    public DES2(String key) {
        init(key);
    }

    public void init(String str) {
        if (str == null)  {
            str = "0123456789ABCDEF";
        }
        // System.out.println(str);
        Security.addProvider(new SunJCE());
        try {
            byte[] key = str.getBytes();
            this.deskey = new SecretKeySpec(key, "DES");

            this.cipher = Cipher.getInstance(this.Algorithm);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (NoSuchPaddingException ex) {
            ex.printStackTrace();
        }
    }

    public byte[] createEncryptor(byte[] datasource) {
        try {
            this.cipher.init(1, this.deskey);
            this.encryptorData = this.cipher.doFinal(datasource);
        } catch (InvalidKeyException ex) {
            ex.printStackTrace();
        } catch (BadPaddingException ex) {
            ex.printStackTrace();
        } catch (IllegalBlockSizeException ex) {
            ex.printStackTrace();
        }
        return this.encryptorData;
    }

    public String createEncryptor(String datasource) throws Exception {
        String result = null;
        byte[] str = createEncryptor(datasource.getBytes());
        if (str != null) {
            BASE64Encoder be = new BASE64Encoder();
            result = be.encode(str);
        }
        return result;
    }

    public byte[] createDecryptor(byte[] datasource) {
       // this.decryptorData=null;
        try {
            this.cipher.init(2, this.deskey);
            this.decryptorData = this.cipher.doFinal(datasource);
            return this.decryptorData;

        } catch (InvalidKeyException ex) {
            ex.printStackTrace();
        } catch (BadPaddingException ex) {
            ex.printStackTrace();
        } catch (IllegalBlockSizeException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String createDecryptor(String str) throws IOException {
        String result = null;
        if (str != null) {
            BASE64Decoder bd = new BASE64Decoder();
            byte[] sorData = bd.decodeBuffer(str);
            sorData = createDecryptor(sorData);
            result = new String(sorData);
        }
        this.decryptorData = null;
        return result;
    }

    public String byteToString(byte[] dataByte) {
        String returnStr = null;
        BASE64Encoder be = new BASE64Encoder();
        returnStr = be.encode(dataByte);
        return returnStr;
    }

    public byte[] stringToByte(String datasource) throws Exception {
        BASE64Decoder bd = new BASE64Decoder();
        byte[] sorData = bd.decodeBuffer(datasource);
        return sorData;
    }


    public static void main(String[] args) throws Exception {

/*        //源字符串
        String encryptorString = "123456";
        //初始化key
        DES2 des = new DES2("0123456789ABCDEF");
        //DES2 des = new DES2("12345678");

        String custLinkman="邹轲辉";
        System.out.println(System.getProperty("file.encoding"));

        System.out.println("Test--------16:42--->:"+custLinkman);

        custLinkman = des.createEncryptor(new String(custLinkman.getBytes("UTF-8"),"UTF-8"));
        System.out.println("Test--------16:42--->:"+custLinkman);

        //  DES2 des2 = new DES2("sm2lmtdssw");
        System.out.println(System.getProperty("file.encoding"));

        String  s = des.createEncryptor(encryptorString);*/
        //G1D+lvJtezA=
        //Ot64y8gwjQU+Bo9xmXtcjA==
        //生成密文
       // String s = des.createEncryptor(new String(encryptorString.getBytes(),"UTF-8"));

        //解密
/*        String sb = des.createDecryptor(s);
        String sb2 = des.createDecryptor("Ot64y8gwjQU+Bo9xmXtcjA==");
        //Ot64y8gwjQU+Bo9xmXtcjA==

        System.out.println("Test--------16:33--->:"+s);
        System.out.println("Test--------16:33--->:"+sb);
        System.out.println("Test--------16:33--->:"+ new String(sb2.getBytes("UTF-8"),"UTF-8"));
        String name="sunCK";*/
        //des = new DES2("12345678");
        //解密2
        DESCode desCode=new DESCode("0123456789ABCDEF");
        System.out.println("Test--------15:58--->:"+desCode.desEncoding("123456"));


    }
}