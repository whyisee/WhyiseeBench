package com.bigdata.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.junit.Before;
import org.junit.Test;
/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/11/28 16:43
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ZConfiguration {

    public static void main(String args[]){
        Configuration conf=new Configuration();
        conf.addResource("conf/configuration-1.xml");

        System.out.println("Test--------16:46--->:"+ conf.get("color"));
        //assertThat();
    }
}
