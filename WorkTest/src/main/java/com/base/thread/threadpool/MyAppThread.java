package com.base.thread.threadpool;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/24 16:56
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class MyAppThread extends Thread {
    public static final String DEFFAULT_NAME = "MyAppThread";
    private static volatile boolean debugLifecycle = false;
    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();
    private static final Logger log = Logger.getAnonymousLogger();
    //private static final Logger log = Logger.getLogger("testlog");

    public MyAppThread(Runnable r) {this(r,DEFFAULT_NAME);}
    public MyAppThread(Runnable r,String name){
        super(r ,name+"-"+created.incrementAndGet());
        setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                log.log(Level.SEVERE,"UNCAUGHT in thread "+ t.getName(),e);
            }
        });
    }
    public void run() {
        //log.info("123");
        boolean debug = debugLifecycle;
        if (debug) {
            log.setLevel(Level.ALL);
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            log.addHandler(consoleHandler);
            log.log(Level.FINE,"Created "+getName());
        }
        try {
            alive.incrementAndGet();
            super.run();
        }finally {
            alive.decrementAndGet();
            if (debug){
                log.log(Level.FINE,"Exiting "+getName());
            }
        }
    }

    public static int getThreadsCreated() { return created.get();}
    public static int getThreadsAlive() { return alive.get();}
    public static boolean getDebug() { return debugLifecycle;}
    public static void setDebug(boolean b) { debugLifecycle = b;}
}
