package com.base.thread.lock;

import net.jcip.annotations.GuardedBy;

import java.awt.*;

/**
 * use for : 出租车类,锁顺序死锁示例--不安全
 *
 * @author zoukh
 * Created in:  2020/9/29 10:16
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class Taxi {
    @GuardedBy("this") private Point location,destination;
    private final Dispatcher dispatcher;

    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public synchronized Point getLocation() {
        return location;
    }

    public synchronized void setLocation(Point location) {
        this.location = location;
        if (location.equals(destination)){
            dispatcher.notifyAvailable(this);
        }
    }
}
