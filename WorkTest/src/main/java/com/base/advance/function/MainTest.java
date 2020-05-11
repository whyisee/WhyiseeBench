package com.base.advance.function;

import java.util.function.Function;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/5/11 14:55
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class MainTest {
    static Function<String,String> testFunction1 = new Function<String, String>() {
        @Override
        public String apply(String s) {
            return "hello1 "+s;
        }
    };

    static Function<String,String> testFunction4 = new Function<String, String>() {
        @Override
        public String apply(String s) {
            return "hello1 "+s;
        }
    };

    static Function<String,String> testFunction3 = new Function<String, String>() {
        @Override
        public String apply(String s) {
            return "hello3 "+s;
        }

        @Override
        public <V> Function<V, String> compose(Function<? super V, ? extends String> before) {
            return (V v) -> apply(testFunction1.apply(v.toString()));
            //return (V v)->before.apply(v);
        }
    };

    public static void main(String args[]){

        Function<String,String> testFunction2 = new Function<String, String>() {
            @Override
            public String apply(String s) {

                return "hello2 "+s;
            }


/*            @Override
            public <V> Function<V, String> compose(Function<? super V, ? extends String> before) {
                return null;
            }*/
        };
        System.out.println("Test--------14:55--->:"+testFunction1.apply("xx"));
        System.out.println("Test--------14:55--->:"+Function.identity());

        System.out.println("Test--------14:55--->:"+testFunction2.apply("xx"));
        System.out.println("Test--------14:55--->:"+testFunction3.compose(testFunction2).apply("xx"));
        System.out.println("Test--------14:55--->:"+testFunction2.andThen(testFunction1).apply("xx"));
        System.out.println("Test--------14:55--->:"+testFunction2.andThen(testFunction1).apply("xx"));


    }
}
