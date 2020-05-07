package com.base.annotation;

import java.lang.reflect.Field;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/22 21:02
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class FruitInfoUtil {
    public static void getFruitInfo(Class<?> clazz){
        String strFruitProvicer = "供应商信息:";
        Field[] fields = clazz.getDeclaredFields();
        for (Field field :fields){
            if(field.isAnnotationPresent(FruitProvide.class)){
                FruitProvide fruitProvide = (FruitProvide)field.getAnnotation(FruitProvide.class);
                System.out.println("Test--------21:08--->:"+fruitProvide.id()+"--"+fruitProvide.name());
            }
        }

    }
}
