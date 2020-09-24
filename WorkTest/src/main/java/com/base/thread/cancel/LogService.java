package com.base.thread.cancel;

import net.jcip.annotations.GuardedBy;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/24 15:18
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class LogService {
    private final BlockingQueue<String> queue;
    private final LoggerThread loggerThread;
    private final PrintWriter writer;
    @GuardedBy("this") private boolean isShutDown;
    @GuardedBy("this") private int reservations;

    public LogService(Writer writer){
        this.writer = new PrintWriter(writer);
        this.loggerThread = new LoggerThread();
        this.queue = new LinkedBlockingQueue<String>();
    }



    public void start() {loggerThread.start();}

    public void stop() {
        synchronized (this){isShutDown = true; }
        loggerThread.interrupt();
    }

    public void log(String msg) throws InterruptedException{
        synchronized(this){
            if (isShutDown){
                throw new IllegalStateException("1");
            }
            ++reservations;
        }
        queue.put(msg);
    }

    private class LoggerThread extends Thread{
        public void run() {
            try {
                while (true){
                    try {
                        synchronized (LogService.this){
                            if (isShutDown && reservations == 0){
                                break;
                            }
                        }
                        String msg = queue.take();
                        synchronized (LogService.this){
                            --reservations;
                        }
                        writer.println(msg);
                    } catch (InterruptedException e){}
                }
            } finally {
                writer.close();
            }
        }
    }
}
