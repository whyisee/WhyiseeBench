package com.data;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/1/7 15:30
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class DatasetResult extends DatasetList implements  ZDataset{
    @Override
    public String[] getNames() {
        return (String[])((String[])this.names.toArray(new String[0]));
    }

    public void setNames(List names) {
        this.names = names;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private List names = new ArrayList();
    private int count;

    public DatasetResult(ResultSet rs) throws Exception {
        while (rs.next()){
            ResultSetMetaData rsmd =rs.getMetaData();
            ZData zdata = new DataMap();

            for (int i = 1 ; i<=rsmd.getColumnCount();i++){
                String name = rsmd.getColumnName(i).toUpperCase();

                //clob 类型
               // type == 2004 ? rs.getBlob(name) : rs.getString(name);
                zdata.put(name ,rsmd.getColumnType(i) == 2004 ? rs.getBlob(name) : rs.getString(name) );
                if (rs.isFirst()) {
                    this.names.add(name);
                }

            }
            this.add(zdata);
        }
        this.count = this.size();
    }

    public void clear() {
        super.clear();
        this.names = new ArrayList();
        this.count = 0;
    }
}
