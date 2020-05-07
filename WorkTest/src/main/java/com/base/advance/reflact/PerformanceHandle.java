package com.base.advance.reflact;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/4/8 18:40
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class PerformanceHandle implements InvocationHandler {
    private Object target;
    public PerformanceHandle(Object target){
        this.target=target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        PerformanceMonitor.begin(target.getClass().getName()+"."+method.getName());
        Object obj=method.invoke(target,args);
        PerformanceMonitor.end();
        return null;
    }
}
