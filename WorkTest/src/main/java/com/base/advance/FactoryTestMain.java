package com.base.advance;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/22 11:25
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class FactoryTestMain {
    public static void main(String...agv){
        SendFactory sendFactory = new SendFactory();
        Sender sender=sendFactory.product("sms");
        sender.send();
        sender=SendFactory.productMail();
        sender.send();
        MailProductFactory mailProductFactory=new MailProductFactory();
        sender=mailProductFactory.product();
        sender.send();
        SingleClass s1= SingleClass.getInstance();
        SingleClass s2= SingleClass.getInstance();
        if(s1==s2){
            System.out.println("Test--------13:50--->:"+"单例模式");
        }else {
            System.out.println("Test--------13:50--->:"+"非单例模式");
        }

    }
}
