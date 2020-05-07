package com.base.annotation;

import java.lang.annotation.*;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/22 20:53
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitProvide {

    public int id() default -1;

    public String name() default "";

}
