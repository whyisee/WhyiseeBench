package com.base.thread;

public class MeasureSpec2{

    public static final int UNSPECIFIED = 0;
    public static final int EXACTLY     = 1;
    public static final int AT_MOST     = 2;

    /**
     * 测量模式
     */
    private int mode;

    /**
     * 测量大小
     */
    private int size;

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
