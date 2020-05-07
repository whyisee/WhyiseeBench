package com.test;


import com.zcache.EHCacheManager;
import com.zcache.ZCache;

public class Main {
    public Main() throws Exception {
    }
    public static void main(String[] args) throws Exception {
        String testStr = "1$YX";
        System.out.println("Test--------15:02--->:" + testStr.split("\\$")[1]);

        StringBuffer sb = new StringBuffer();
        sb.append("11");
        sb.append("12");
        System.out.println("Test--------15:29--->:" + sb + asciiToString("1"));
        ZCache cache = EHCacheManager.getInstance().getCache("COM_EXCEL_COLLECT");
        cache.put("export/clreport/MaitainCallDetailTwe.xml", "-------");
        System.out.println("Test--------11:41--->:" + cache.get("export/clreport/MaitainCallDetailTwe.xml").getValue());
        cache.remove("export/clreport/MaitainCallDetailTwe.xml");
        //ObjectInputStream objectInputStream = cache.get("export/clreport/MaitainCallDetailTwe.xml").getValue();
        //System.out.println("Test--------11:41--->:"+cache.get("export/clreport/MaitainCallDetailTwe.xml").getValue());
        //
    }

    public static String asciiToString(String value) {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }


}
