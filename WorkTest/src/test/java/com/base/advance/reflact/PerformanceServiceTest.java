package com.base.advance.reflact;

import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/4/8 20:51
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class PerformanceServiceTest {
    @Test
    public void test(){
        PerformanceService pf=new PerformanceServiceImpl();
        pf.addTest();
    }

    @Test
    public void test2(){
        PerformanceService target=new PerformanceServiceImpl();
        PerformanceHandle handle=new PerformanceHandle(target);
        PerformanceService proxy=(PerformanceService) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),handle);

        proxy.addTest();
    }

}