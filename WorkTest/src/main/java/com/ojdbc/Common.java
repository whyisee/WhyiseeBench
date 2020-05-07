package com.ojdbc;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/1/7 11:25
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class Common {
    private static Common common;

    /**
     * get instance
     * @return Common
     */
    public static Common getInstance() {
        if (common == null) {
            common = new Common();
        }
        return common;
    }
    public String trimSuffix(String str, String suffix) {
        return str.endsWith(suffix) ? str.substring(0, str.length() - 1) : str;
    }

    public Timestamp encodeTimestamp(String timeStr) throws Exception {
        String format = this.getTimestampFormat(timeStr);
        return this.encodeTimestamp(format, timeStr);
    }

    public Timestamp encodeTimestamp(String format, String timeStr) throws Exception {
        if (null != timeStr && !"".equals(timeStr)) {
            if (format.length() != timeStr.length()) {
                format = this.getTimestampFormat(timeStr);
            }

            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return new Timestamp(sdf.parse(timeStr).getTime());
        } else {
            return null;
        }
    }
    public URL getClassResource(String file) throws Exception {
        URL url = common.getClass().getClassLoader().getResource(file);
        if (url == null) {
            System.out.println("file " + file + " not exist!");;
        }

        return url;
    }


    public String getTimestampFormat(String value) {
        switch(value.length()) {
            case 4:
                return "yyyy";
            case 5:
            case 9:
            case 11:
            case 12:
            case 14:
            case 15:
            case 17:
            case 18:
            case 20:
            default:
                return null;
            case 6:
                return "yyyyMM";
            case 7:
                return "yyyy-MM";
            case 8:
                return "yyyyMMdd";
            case 10:
                return "yyyy-MM-dd";
            case 13:
                return "yyyy-MM-dd HH";
            case 16:
                return "yyyy-MM-dd HH:mm";
            case 19:
                return "yyyy-MM-dd HH:mm:ss";
            case 21:
                return "yyyy-MM-dd HH:mm:ss.S";
        }
    }
}
