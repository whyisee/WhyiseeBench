package com.base.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class TestMain2 {
    public static Map<String, String> map = new ConcurrentHashMap<>();
    static {
        System.out.println("===test===>"+222);
    }

    public TestMain2 (){
        System.out.println("===test===>"+111);
    }

    public static void add(String key){
        map.putIfAbsent(key, new String("1"));
        System.out.println(map.get(key));
    }
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    add("key");
                }
            }.start();
        }
    }
}
