package com.base.advance.reflact;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/4/8 20:20
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class MethodPerformance {
    private long begin;
    private long end;
    private String serviceMethod;
    public MethodPerformance(String serviceMethod){
        this.serviceMethod=serviceMethod;
        this.begin=System.currentTimeMillis();
    }

    public void printPerformance(){
        end=System.currentTimeMillis();
        Long useTime=end-begin;
        System.out.println("Test--------20:25--->:"+serviceMethod+"花费了:"+useTime+"毫秒");
    }

}
