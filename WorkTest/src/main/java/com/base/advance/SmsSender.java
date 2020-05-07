package com.base.advance;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/22 11:19
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class SmsSender implements Sender {

    @Override
    public void send() {
        System.out.println("Test--------11:20--->:"+"send sms...");
    }
}
