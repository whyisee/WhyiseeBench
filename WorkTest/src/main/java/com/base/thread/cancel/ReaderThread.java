package com.base.thread.cancel;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/24 15:02
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class ReaderThread extends Thread {
    private final Socket socket;
    private final InputStream in;
    private static final int BUFSZ = 128;

    public ReaderThread(Socket socket) throws IOException{
        this.socket = socket;
        this.in = socket.getInputStream();
    }
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException ignored) {}
        finally {
            super.interrupt();
        }
    }

    public void run() {
        try {
            byte[] buf = new byte[BUFSZ];
            while (true) {
                int count = in.read(buf);
                if (count < 0){
                    break;
                }else if (count > 0) {
                    //
                }
            }
        } catch (IOException e){}
    }
}
