package com.base.thread;

public  class MeasureSpec {

    private static final int MODE_SHIFT = 30;
    private static final int MODE_MASK  = 0x3 << MODE_SHIFT;
    public static final int UNSPECIFIED = 0 << MODE_SHIFT;
    public static final int EXACTLY     = 1 << MODE_SHIFT;
    public static final int AT_MOST     = 2 << MODE_SHIFT;


    public static int makeMeasureSpec(int size, int mode) {
        if (true) {
            return size + mode;
        } else {
            return (size & ~MODE_MASK) | (mode & MODE_MASK);
        }
    }


    public static int getMode(int measureSpec) {
        return (measureSpec & MODE_MASK);
    }


    public static int getSize(int measureSpec) {
        return (measureSpec & ~MODE_MASK);
    }
}

