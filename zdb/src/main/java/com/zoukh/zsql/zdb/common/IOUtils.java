package com.zoukh.zsql.zdb.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/4/24 18:05
 * @version 1.0
 * @Modified By:
 * @used in: zdb
 */
public class IOUtils {
    public static void copyBytes(InputStream in, OutputStream out,int buffSize) throws IOException {
        byte[] buf= new byte[buffSize];
        int bytesRead = in.read(buf);
        while (bytesRead >=0){
            out.write(buf,0,bytesRead);
            bytesRead=in.read(buf);
        }
    }
}
