package com.bigdata.hadoop;


import java.io.IOException;

public class HadoopInterfaceTest {

    public static void main(String args[]) throws IOException {
        HadoopGetFileUtils hadoopGetFileUtils = new HadoopGetFileUtils();
       // hadoopGetFileUtils.getFileContextUseURL("192.168.56.201","9000","/tmp/test1.txt");
        hadoopGetFileUtils.getFileContextUseFSAPI("/tmp/test1.txt");
        //hadoopGetFileUtils.copyFileToHDFSUseFSAPI("C:\\Windows\\System32\\drivers\\etc\\hosts","/tmp/hosts");
        //ss
    }
}
