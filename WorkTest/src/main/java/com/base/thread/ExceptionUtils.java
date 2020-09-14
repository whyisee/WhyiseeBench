package com.base.thread;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/14 10:59
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class ExceptionUtils {
    public static RuntimeException launderThrowaber(Throwable t){
        if (t instanceof RuntimeException){
            return (RuntimeException) t;
        }else if (t instanceof Error){
            throw (Error) t;
        }else {
            throw new IllegalStateException("Not unchecked",t);
        }
    }
}
