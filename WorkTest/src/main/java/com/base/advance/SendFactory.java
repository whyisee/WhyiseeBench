package com.base.advance;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/22 11:21
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class SendFactory {
    public Sender product(String type) {
        if ("mail".equals(type)) {
            return new MailSender();
        } else if ("sms".equals(type)) {
            return new SmsSender();
        } else {
            System.out.println("Test--------11:24--->:" + "input correct type");
            return null;
        }
    }

    public Sender productSms() {
        return new SmsSender();
    }

    public static Sender productMail() {
        return new MailSender();
    }
}
