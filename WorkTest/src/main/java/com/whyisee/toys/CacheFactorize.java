package com.whyisee.toys;

import net.jcip.annotations.ThreadSafe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ThreadSafe
public class CacheFactorize {
    private BigInteger lastNumber;
    private BigInteger[] lastFactors;
    private long hits;
    private long cacheHits;

    public long getHits() { return hits; }
    public double getCacheHitRatio(){
        return (double) cacheHits / (double)hits;
    }

    public  BigInteger[] productFactor(BigInteger input) {
        BigInteger[] factors = null;

        {
            ++hits;
            if (input.equals(lastNumber)){
                ++cacheHits;
                factors = lastFactors;
            }
        }
        if (factors == null ) {
            factors = factor(input);
            {
                lastNumber = input;
                lastFactors = factors.clone();
            }
        }



        return factors;

    }

    static Integer[] factorsOf(int val) {
        List<Integer> numArray = new ArrayList<Integer>();

        System.out.println("\nThe factors of " + val + " are:");
        for (int i = 2; i <= Math.ceil(Math.sqrt(val)); i++) {
            if (val % i == 0) {
                numArray.add(i);
                val /= i;
                System.out.print(i + ", ");
            }
        }
        numArray.add(val);
        System.out.print(val);
        return numArray.toArray(new Integer[numArray.size()]);
    }
    public static void main(String[] args){
        //factorsOf(100);

        //Scanner sc = new Scanner(System.in);
        CacheFactorize cacheFactorize = new CacheFactorize();
        BigInteger[] result= cacheFactorize.productFactor(new BigInteger("100"));



        for (int i = 0; i < result.length; i++) {
            System.out.println("===test===>"+result[i]);
        }
    }


    private static BigInteger[] factor(BigInteger num) {
        //键值对的集合Map，<因数,出现次数>--<K, V>
        int znum = num.intValue();
        BigInteger[] factors = null;
        int factorNum = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 2; i <= znum; i++) {
            while(znum % i == 0) {
                factorNum++;
                Integer v = map.get(i);
                if(v == null) {//集合里不包含,放入值并且计数为1
                    map.put(i, 1);
                }else
                    map.put(i, v + 1);//计数+1


                znum /= i;//分解
            }
        }
        factors = new BigInteger[factorNum];

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int k = entry.getKey();
            int v = entry.getValue();
            for(int i = 0; i < v; i++) {
                factors[--factorNum]=new BigInteger(Integer.toString(k));
            }
        }
/*        System.out.println("===test===>"+map);
        System.out.println("===test===>"+factors);
        for (int i = 0; i < factors.length; i++) {
            System.out.println("===test===>"+factors.length+factors[i]);
        }*/

        return factors;
    }
}
