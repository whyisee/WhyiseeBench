package com.base.thread;

import net.sf.ehcache.util.ProductInfo;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/14 10:06
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class Preloader {
    private final FutureTask<ProductInfo> future =
            new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
                @Override
                public ProductInfo call() throws Exception {
                    //return loadProductInfo;
                    return null;
                }
            });
}
