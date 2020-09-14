package com.base.thread.taskexec;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/14 11:20
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class TaskExecutionWebServer {
    private static final int NTHREADS = 100;
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

    public static void main (String [] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true){
            final Socket connection = socket.accept();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    System.out.println("===test===>"+connection.getRemoteSocketAddress());
                    //connection.getInputStream();
                }
            };
            exec.execute(task);
        }
    }
}
