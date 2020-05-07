package com.bigdata.zookeeper;

import org.apache.zookeeper.AsyncCallback;

public class IStringCallback implements AsyncCallback.StringCallback {

    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        System.out.println("test==>" + rc + path + ctx + name);
    }
}