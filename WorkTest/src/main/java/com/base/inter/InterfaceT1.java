package com.base.inter;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/5/7 18:00
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public interface InterfaceT1 {
    default  void sayhello(){
        saybb();
        System.out.println("Test--------18:06--->:"+"hello");
    };
    void saybyebye();

    static void saybb(){
        System.out.println("Test--------15:30--->:"+"bb");
    };
}
