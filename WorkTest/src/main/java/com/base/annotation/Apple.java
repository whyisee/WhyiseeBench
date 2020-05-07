package com.base.annotation;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/22 20:56
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class Apple {
    public String getAppleProvide() {
        return appleProvide;
    }

    public void setAppleProvide(String appleProvide) {
        this.appleProvide = appleProvide;
    }

    @FruitProvide(id=1,name="苹果")

    private String appleProvide;


}
