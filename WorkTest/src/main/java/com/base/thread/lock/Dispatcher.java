package com.base.thread.lock;

import com.sun.prism.Image;
import net.jcip.annotations.GuardedBy;

import java.util.HashSet;
import java.util.Set;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/29 10:18
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class Dispatcher {
    @GuardedBy("this") private final Set<Taxi> taxis;
    @GuardedBy("this") private final Set<Taxi> availableTexis;

    public Dispatcher() {
        taxis = new HashSet<Taxi>();
        availableTexis = new HashSet<Taxi>();
    }

    public synchronized void notifyAvailable(Taxi taxi) {
        availableTexis.add(taxi);
    }

    public synchronized Image getImage() {
        //com.sun.prism.Image
        //Image image = new Image();
        //image.drawMarker()
        return null;
    }
}
