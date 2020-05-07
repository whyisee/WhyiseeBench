package com.office;

import com.ojdbc.Common;
import com.ojdbc.JdbcUtil;
import org.apache.poi.ss.usermodel.Sheet;

import java.awt.*;
import java.sql.Connection;
import java.util.List;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/1/29 14:59
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class DataInitIndexManage {
    public static void main(String[] args) throws Exception {
        ExcelUtils excelUtils=new ExcelUtils();
        JdbcUtil jdbcUtils = new JdbcUtil();
        Connection conn = null;
        Common comm = Common.getInstance();
        conn = JdbcUtil.getConn("ORACLE2") ;
        conn.setAutoCommit(false);
        Sheet sheet=excelUtils.getSheetByNum("D:\\桌面\\work\\脚本备份\\5-存量后台\\标签管理\\数据标签梳理_北京联通.xlsx",1);
        for (int r = 1 ;r<=672;r++) {
            conn = JdbcUtil.getConn("ORACLE2") ;
            conn.setAutoCommit(false);
            List<String> list = excelUtils.getSheetRow(sheet, r);
            //System.out.println("Test--------17:52--->:"+list);
            String v_data = " ";
            for (int i = 0; i < 24; i++) {
                v_data = v_data + "'" + list.get(i).replace("'","''") + "',";
            }
            v_data = v_data + "'2050-12-31',sysdate,'SUPERUSR','1','1',"+r+"";
            String v_sql = "insert into TD_SM_INDEX_MANAGE\n" +
                    "(\n" +
                    "index_priority\n" +
                    ",index_cname\n" +
                    ",index_name\n" +
                    ",index_begin_date\n" +
                    ",index_data_type\n" +
                    ",index_business_desc\n" +
                    ",index_technology_desc\n" +
                    ",index_create_time\n" +
                    ",index_code\n" +
                    ",index_unit\n" +
                    ",index_range_1\n" +
                    ",index_range_2\n" +
                    ",index_range_3\n" +
                    ",index_range_4\n" +
                    ",index_range_5\n" +
                    ",index_range_6\n" +
                    ",index_range_7\n" +
                    ",index_range_8\n" +
                    ",index_cycle_type\n" +
                    ",index_source_table\n" +
                    ",index_source_table_name\n" +
                    ",index_source_type\n" +
                    ",index_business_person\n" +
                    ",index_technology_person\n" +
                    ",index_end_date\n" +
                    ",index_update_time\n" +
                    ",index_update_person\n" +
                    ",index_status\n" +
                    ",index_show_status\n" +
                    ",index_show_order\n" +
                    ")\n" +
                    "values \n" +
                    "(\n";
            jdbcUtils.execUpdate(conn,v_sql+" "+v_data+" )");
            conn.commit();
            conn.close();

        }




    }
}
