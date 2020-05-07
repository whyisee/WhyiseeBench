package com.ojdbc;

import com.data.DataMap;
import com.data.ZData;

import java.io.StringReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/1/7 16:30
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class DaoHelper {
    protected static Common common = Common.getInstance();

    public static Object[] getObjectsByInsert(Connection conn, String tableName , ZData zData) throws Exception {
        StringBuffer namestr = new StringBuffer();
        StringBuffer valuestr = new StringBuffer();
        Parameter param = new Parameter();
        ZColumn[] columns = getColumns(conn, tableName);
        String sql="";

        for(int i = 0; i < columns.length; ++i) {
            Object[] colobjs = getObjectsByColumn(columns[i], zData);
            namestr.append(colobjs[0] + ",");
            valuestr.append("?,");
            param.add(colobjs[1]);
        }
        
        sql=getInsertSql(tableName, common.trimSuffix(namestr.toString(), ","), common.trimSuffix(valuestr.toString(), ","));


        return new Object[]{sql, param};



    }

    public static Object[] getObjectsByColumn(ZColumn column,ZData data) throws Exception {
        String columnName = column.getColumnName();
        Object columnValue = data.get(columnName);
        if (isDatetimeColumn(column.getColumnType())) {
            if (columnValue != null) {
                if ("".equals(columnValue)) {
                    columnValue = null;
                } else {
                    columnValue = common.encodeTimestamp(columnValue.toString());
                }
            }
        } else if (column.getColumnType() == -1 && columnValue != null && !"".equals(columnValue)) {
            columnValue = new ColumnObject(columnName, columnValue, -1);
        }

        return new Object[]{columnName, columnValue};
    }

    public static boolean isDatetimeColumn(int columnType) throws Exception {
        return columnType == 91 || columnType == 92 || columnType == 93;
    }

    public static String getInsertSql(String table_name, String namestr, String valuestr) {
        StringBuffer str = new StringBuffer();
        str.append("insert into " + table_name);
        str.append("(" + namestr + ")");
        str.append(" values ");
        str.append("(" + valuestr + ")");
        return str.toString();
    }

    public static ZColumn[] getColumns(Connection conn,String tableName ) throws SQLException {

        ZData columns =   getColumnsByData(conn, tableName);
        List keys = Arrays.asList(getPrimaryKeys(conn, tableName));
        String[] colnames = columns.getNames();
        ZColumn[] ZColumns = new ZColumn[colnames.length];

        for(int i = 0; i < colnames.length; ++i) {
            ZColumn column = (ZColumn)columns.get(colnames[i]);
            column.setKey(keys.contains(column.getColumnName()));
            ZColumns[i] = column;
        }

        return ZColumns;

    }
    public static ZData getColumnsByData(Connection conn,String tableName) throws SQLException {
        ZData columns = new DataMap();
        PreparedStatement statement = conn.prepareStatement("select * from " + tableName.toUpperCase() + " where 1 = 0");
        ResultSetMetaData metaData = statement.executeQuery().getMetaData();

        for(int i = 1; i <= metaData.getColumnCount(); ++i) {
            ZColumn column = new ColumnInfo();
            column.setColumnName(metaData.getColumnName(i).toUpperCase());
            column.setColumnType(metaData.getColumnType(i));
            column.setColumnDesc(metaData.getColumnLabel(i));
            column.setColumnSize(metaData.getColumnDisplaySize(i));
            column.setDecimalDigits(metaData.getScale(i));
            column.setNullable(metaData.isNullable(i) != 0);
            columns.put(column.getColumnName(), column);
        }

        statement.close();
        return  columns;

    }

    public static String[] getPrimaryKeys(Connection conn,String tableName) throws SQLException {
        ZData columns = getColumnsByData(conn, tableName);
        List keys = new ArrayList();
        ResultSet rs = conn.getMetaData().getPrimaryKeys((String)null, "%", tableName.toUpperCase());

        while(rs.next()) {
            String column_name = rs.getString("COLUMN_NAME").toUpperCase();
            if (columns.containsKey(column_name) && !keys.contains(column_name)) {
                keys.add(column_name);
            }
        }

        rs.close();
        String[] primaryKeys = (String[])((String[])keys.toArray(new String[0]));
        return primaryKeys;

    }
    public static PreparedStatement setParameters(PreparedStatement statment, Parameter param) throws Exception {
        PreparedStatement rsstatment=statment;
        for(int i = 0; i < param.size(); ++i) {

            if (param.get(i) == null) {

                rsstatment.setNull(i + 1, 12);
            } else {

                Object value = param.get(i);
                if (value instanceof ColumnObject) {
                    ColumnObject columnObject = (ColumnObject)value;
                    if (columnObject.getType() == -1) {
                        rsstatment.setCharacterStream(i + 1, new StringReader(columnObject.getValue().toString()), columnObject.getValue().toString().length());
                    }
                } else {
                    rsstatment.setObject(i + 1, value);
                }
            }
        }

        return  rsstatment;

    }
}
