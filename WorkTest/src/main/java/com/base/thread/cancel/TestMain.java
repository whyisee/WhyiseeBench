package com.base.thread.cancel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.ThreadFactory;

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
    public static void main(String[] args) throws InterruptedException, IOException {
        FileWriter fileWriter = new FileWriter("G:\\1");
        LogWriter logWriter = new LogWriter(new PrintWriter(fileWriter));
        logWriter.log("123");
        logWriter.log("123");
        //ThreadFactory
        logWriter.start();
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();

        try {
            SECONDS.sleep(1);
        } finally {
            System.out.println("===test===>"+generator.get());
            generator.cancel();
        }
        logWriter.log("123");
    }
}
