package com.bigdata.hive;

import java.sql.*;

public class HiveTest {
    private static String driverName =
            "org.apache.hive.jdbc.HiveDriver";
    public static void main(String[] args)
            throws SQLException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Connection con = DriverManager.getConnection(
                "jdbc:hive2://hadoop01:10000", "td1", "hive");
        Statement stmt = con.createStatement();
        String tableName = " td1.tb1 ";
        String sql = "select * from td1.tb1 ";
        ResultSet res = stmt.executeQuery(sql);

        sql = "select * from " + tableName;
        res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(String.valueOf(res.getInt(1)) + "\t"
                    + res.getString(1));
        }
    }


}
