package com.bigdata.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.util.Progressable;
import org.apache.zookeeper.common.IOUtils;

import java.io.*;
import java.net.URL;

public class HadoopGetFileUtils {
    static {

        //setURLStreamHandlerFactory()该方法每个java虚拟机只能调用一次
        System.setProperty("hadoop.home.dir", "D:\\software\\hadoop-2.8.5\\");
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());

    }
    public void getFileContextUseURL(String host,String port,String path){
        InputStream in = null;
        try {
            in = new URL("hdfs://"+host+":"+port+path).openStream();
            //读取文件
/*            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while((line =reader.readLine())!= null){
                System.out.println(line.toString());
            }*/
            //显然下面这种方法更装逼
            IOUtils.copyBytes(in,System.out,4096,false);

        } catch (IOException e) {
            e.printStackTrace();
            IOUtils.closeStream(in);
        }

    }
    public void getFileContextUseFSAPI(String path)  {
        Configuration configuration = new Configuration();
        configuration.addResource("conf/core-site.xml");

        System.out.println(configuration.get("fs.default.name"));
        InputStream in = null;
        try{
            FileSystem fileSystem = FileSystem.get(configuration);
            in = fileSystem.open(new Path(path));




            IOUtils.copyBytes(in,System.out,4096,false);
        } catch (IOException e) {
            e.printStackTrace();
            IOUtils.closeStream(in);
        }
    }

    public void copyFileToHDFSUseFSAPI(String localpath,String path){
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        configuration.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");

        configuration.addResource("conf/core-site.xml");
        try {
            FileSystem fileSystem = FileSystem.get(configuration);
            InputStream in = new BufferedInputStream(new FileInputStream(localpath));
            OutputStream out = fileSystem.create(new Path(path), new Progressable() {
                @Override
                public void progress() {
                    System.out.print(" . ");
                }
            });
            IOUtils.copyBytes(in,out,4096,true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
