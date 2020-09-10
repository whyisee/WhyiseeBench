package com.base.thread;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class OneValueCacheTest {
    public void  test() {
        OneValueCache oneValueCache = new OneValueCache(new BigInteger("1"),new BigInteger[]{new BigInteger("1")});
        BigInteger[] bigIntegers = oneValueCache.getFactors(new BigInteger("1"));
    }

}