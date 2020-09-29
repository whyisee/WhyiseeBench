package com.whyisee.toys;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/29 16:53
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class Utils {
    public static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }
}
