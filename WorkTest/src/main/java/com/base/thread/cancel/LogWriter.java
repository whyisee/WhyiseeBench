package com.base.thread.cancel;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/24 15:31
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class LogWriter {
    private final BlockingQueue<String> queue;
    private final LoggerThread logger;
    private static final int CAPACITY = 128;
    public LogWriter(Writer writer){
        this.queue = new LinkedBlockingQueue<String>(CAPACITY);
        this.logger = new LoggerThread(writer);
    }

    public void start() { logger.start();}
    public void log(String msg) throws  InterruptedException{
        queue.put(msg);
    }

    private class LoggerThread extends Thread{
        private final PrintWriter writer;
        LoggerThread(Writer writer){
            this.writer = new PrintWriter(writer);
        }

        public void run() {
            try {
                while (true) {
                    System.out.println("===test===>"+queue.take());
                    writer.println(queue.take());
                }
            }catch (InterruptedException ignored){

            }finally {
                writer.close();
            }
        }
    }
}
