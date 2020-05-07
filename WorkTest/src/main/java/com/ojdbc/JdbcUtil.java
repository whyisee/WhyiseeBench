package com.ojdbc;

import com.control.InfoPrint;
import com.data.DatasetList;
import com.data.DatasetResult;
import com.data.ZData;
import com.data.ZDataset;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/1/7 11:28
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class JdbcUtil {
    protected static Common common = Common.getInstance();
    public static String fsPath = System.getProperty("user.dir");

    private static Properties prop = new Properties();

    public static void readConf() throws Exception {
        try {
            //读取配置信息：
            String fs = "/conf/dbinfo.prop";
            InputStream in = JdbcUtil.class.getResourceAsStream(fs);
            prop.load(in);
            in.close();
        } catch (FileNotFoundException e) {
            InfoPrint.error("--->获取数据库链接时异常");
            InfoPrint.error(e.toString());
            InfoPrint.error(e.getStackTrace());
        } catch (IOException e) {
            InfoPrint.error("--->获取数据库链接时异常");
            InfoPrint.error(e.toString());
            InfoPrint.error(e.getStackTrace());
        }
    }

    public static Connection getConn(String db_type) throws Exception {
        Connection conn = null;
        try {
            if (db_type != null) {
                readConf();
                InfoPrint.log("--->getConn创建数据库链接: " + prop.getProperty(db_type + "_URL") + ","
                        + prop.getProperty(db_type + "_USERNAME") + ","
                        + prop.getProperty(db_type + "_PASSWORD"));

                Class.forName(prop.getProperty(db_type + "_DRIVER"));
                conn = DriverManager.getConnection(prop.getProperty(db_type + "_URL"),
                        prop.getProperty(db_type + "_USERNAME"),
                        prop.getProperty(db_type + "_PASSWORD"));

            } else {
                InfoPrint.error("[ERROR] get provinceId or subSysCode error");
            }
        } catch (Exception e) {
            InfoPrint.error("--->getConn(String db_type)异常");
            InfoPrint.error(e.toString());
            InfoPrint.error(e.getStackTrace());
            throw new Exception(e.toString());
        }
        //con.setAutoCommit(false);
        return conn;
    }

    
    /**
     * use for : 增删改查-select
     *@author zoukh
     *@Created in:  2019/1/7 16:03
     *@Modified By:
     *@version 1.0
     *@used in: JdbcUtil
     */

    public ZDataset queryList(Connection conn, String sql) throws Exception {
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery(sql);
        ZDataset result = new DatasetResult(rs);
        rs.getStatement().close();
        return result;
    }

    /**
     * use for : 增删改查-update
     *@author zoukh
     *@Created in:  2019/1/7 16:10
     *@Modified By:
     *@version 1.0
     *@used in: JdbcUtil
     */

    public int execUpdate(Connection conn, String sql) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(sql);
        System.out.println("Test--------15:56--->:"+sql);
        int result = statement.executeUpdate(sql);
        return result;
    }


    
    /**
     * use for : 增删改查-insert
     *@author zoukh
     *@Created in:  2019/1/7 16:25
     *@Modified By:
     *@version 1.0
     *@used in: JdbcUtil
     */
    public boolean execInsert(Connection conn , String tableName , ZData data) throws Exception {
        Object[] insobjs = DaoHelper.getObjectsByInsert(conn, tableName, data);
        String sql = (String)insobjs[0];
        Parameter param = (Parameter)insobjs[1];
        PreparedStatement statement = conn.prepareStatement((String)insobjs[0]);
        //statement.get
        statement=DaoHelper.setParameters(statement,(Parameter)insobjs[1]);
        System.out.println("Test--------19:54--->:"+sql);
        System.out.println("Test--------19:54--->:"+param);

        boolean result = statement.execute();

       // int result = this.execUpdate(conn, );
        return result ;
    }

}
