package com.base.timer;

import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("===test===>"+"MyTask is running");

    }
}
