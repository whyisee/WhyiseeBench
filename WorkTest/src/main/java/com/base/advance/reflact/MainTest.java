package com.base.advance.reflact;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/4/1 23:09
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class MainTest {
    public static void main(String args[]) throws Throwable {
        MyCar car1 = new MyCar("轿车", "奥迪", 100000);
        car1.display();

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.base.advance.reflact.MyCar");

        Constructor cons = clazz.getDeclaredConstructor((Class[]) null);
        MyCar car2 = (MyCar) cons.newInstance();

        Method setType = clazz.getMethod("setCarType", String.class);
        setType.invoke(car2, "面包");

        Method setName = clazz.getMethod("setCarName", String.class);
        setName.invoke(car2, "本田");

        Method setNumber = clazz.getMethod("setNumber", int.class);
        setNumber.invoke(car2,20000);
        car2.display();


        ClassLoader loader1 = Thread.currentThread().getContextClassLoader();
        System.out.println("Test--------23:25--->:"+loader1);
        System.out.println("Test--------23:25--->:"+loader1.getParent());
        System.out.println("Test--------23:25--->:"+loader1.getParent().getParent());

        Map<String,String> test=new HashMap<String, String>(){{put("1","1");}};
        MyCar car = new MyCar(){{}};

    }
}

