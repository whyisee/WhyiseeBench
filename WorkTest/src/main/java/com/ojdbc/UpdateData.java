package com.ojdbc;

import com.data.DataMap;
import com.data.DatasetList;
import com.data.ZData;
import com.data.ZDataset;
import com.passwd.DES2;

import java.sql.Connection;
import java.util.Iterator;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/1/9 14:37
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class UpdateData {
    public static void main(String [] args) throws Exception {
        Connection conn = null;
        JdbcUtil jdbcUtils = new JdbcUtil();
        Common comm = Common.getInstance();
        conn = JdbcUtil.getConn("ORACLE3") ;
        conn.setAutoCommit(false);
        String tableName= args[0].toUpperCase();
        String colName= args[1].toUpperCase();
        String sourceData="";
        String rsData="";
        DES2 des = new DES2("sm2lmtdw");



            ZData zData = new DataMap();
            String sql = " select cust_id, " + colName + " ,a.rowid  from  " + tableName +" a ";
            ZDataset zDataset = new DatasetList();
            zDataset = jdbcUtils.queryList(conn, sql);
            String custId = "";
            String updataSql = "";
            Iterator iterator = zDataset.iterator();
            while (iterator.hasNext()) {
                ZData element = (ZData) iterator.next();
                custId = element.getString("ROWID");
                //System.out.println("Test--------15:04--->:"+custId);
                sourceData = element.getString(colName);
                //System.out.println("Test--------15:04--->:"+colName+sourceData);
                if (colName.equals("CUST_ADDRESS")) {
                    String adr[] = sourceData.split("-");
                    sourceData = adr[2];
                    rsData = adr[0] + "-" + adr[1] + "-" + des.createEncryptor(new String(sourceData.getBytes("UTF-8"),"UTF-8"));
                } else {
                    
                    rsData = des.createEncryptor(new String(sourceData.getBytes("UTF-8"),"UTF-8"));
                }
                updataSql = " update " + tableName + " set " + colName + " = '" + rsData + "' where ROWID='" + custId + "' ";

                jdbcUtils.execUpdate(conn, updataSql);

            }


        conn.commit();
        conn.close();
       // System.out.println("Test--------17:38--->:"+"按时打算的撒的");
    }
}
