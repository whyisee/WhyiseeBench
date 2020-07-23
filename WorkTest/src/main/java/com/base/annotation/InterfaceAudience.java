package com.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@InterfaceAudience.Public
public class InterfaceAudience {
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Public {};

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LimitedRrivate {
        String[] value();
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Private {};

    private InterfaceAudience(){}


}
