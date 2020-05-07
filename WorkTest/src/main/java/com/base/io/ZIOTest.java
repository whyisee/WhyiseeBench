package com.base.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/22 20:04
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ZIOTest {
    public static void main(String args[]) throws IOException {
        InputStream in=System.in;
        OutputStream out =System.out;
        System.out.println("Test--------20:20--->:"+in.read());


    }
}
