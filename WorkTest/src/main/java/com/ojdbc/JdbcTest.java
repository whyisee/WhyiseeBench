package com.ojdbc;

import com.control.InfoPrint;
import com.data.DataMap;
import com.data.DatasetList;
import com.data.ZData;
import com.data.ZDataset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/1/7 11:37
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class JdbcTest {
    public static Connection conn = null;

    public static void  main(String [] args) throws Exception {
        JdbcUtil jdbcUtils = new JdbcUtil();
        Common comm = Common.getInstance();
        conn = JdbcUtil.getConn("MYSQL1") ;
        conn.setAutoCommit(false);

        ZData zData= new DataMap() ;

        PreparedStatement ps = null ;
        String sql = " use worktest ";

        InfoPrint.log("--->sql:"+sql);



        ZDataset zDataset = new DatasetList();
        zDataset = jdbcUtils.queryList(conn,sql);
        zData = (ZData) zDataset.get(0);

        System.out.println("Test--------15:49--->:"+zData.getString("B","11"));
        //System.out.println("Test--------19:23--->:"+comm.trimSuffix("1223","23"));
        jdbcUtils.execInsert(conn,"td_b_zoukh_tmp",zData);
        conn.commit();
        conn.close();
    }
}
