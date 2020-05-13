package com.whyisee.zsql.zdb.common;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/4/24 18:13
 * @version 1.0
 * @Modified By:
 * @used in: zdb
 */
public class FileIOUtils {
    public static void write(String data,String path) throws IOException {
        System.out.println("Test--------18:27--->:"+path);
        BufferedWriter out=null;
        out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path,true)));
        out.write(data);
        out.close();
    }
    public static Map readFileByLines(String name){
        File file=new File(name);
        BufferedReader reader=null;
        String trmpString=null;
        Map data=new HashMap();
        try {
            reader=new BufferedReader(new FileReader(file));
            while((trmpString=reader.readLine())!=null){
                data.put(trmpString.split(",")[0],trmpString.split(",")[1]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
