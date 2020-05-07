package com.whyisee.spark.start;

import org.apache.spark.api.java.function.Function;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/5/7 15:30
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ZContains implements Function<String,Boolean>{
    private String query;
    public ZContains(String query){this.query=query;}
    public Boolean call(String x){return x.contains(query);}

}
