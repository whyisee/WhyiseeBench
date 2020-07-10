package com.base.thread;

import java.util.concurrent.ExecutionException;

public class TestMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyExecutor myExecutor = new MyExecutor();
        myExecutor.execute(new MyRunnable());
        MyFuture future = new MyFuture();
        future.setResult("111");
        System.out.println("===test===>"+future.get());
    }
}
