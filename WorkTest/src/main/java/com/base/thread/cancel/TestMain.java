package com.base.thread.cancel;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/14 15:11
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class TestMain {
    public static void main(String[] args) throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();

        try {
            SECONDS.sleep(1);
        } finally {
            System.out.println("===test===>"+generator.get());
            generator.cancel();
        }
    }
}
