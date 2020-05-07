package com.base.advance;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/22 13:37
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class MailProductFactory implements Provider {
    @Override
    public Sender product() {
        return new MailSender();
    }
}
