package com.base.advance;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/22 13:47
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class SingleClass {
    private SingleClass (){}
    private static SingleClass instance = new SingleClass();
    public static SingleClass getInstance(){return instance;}
}
