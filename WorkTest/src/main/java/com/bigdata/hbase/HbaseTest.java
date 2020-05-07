/*
package com.bigdata.hbase;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.IOException;
public class HbaseTest {
    public static void main(String [] args) throws IOException {
    Configuration configuration = HBaseConfiguration.create();
    configuration.addResource("conf/hbase-site.xml");

        Connection conn= ConnectionFactory.createConnection(configuration);
        Table table = null;
            table = conn.getTable( TableName.valueOf("t1"));

        Get get = new Get(Bytes.toBytes("2"));
        get.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("c1"));
        Result result = table.get(get);
        byte[] val = result.getValue(Bytes.toBytes("f1"),Bytes.toBytes("c1"));
        System.out.println(Bytes.toString(val));
    }
}
*/
