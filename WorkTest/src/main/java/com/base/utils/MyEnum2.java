package com.base.utils;

import java.util.Set;

public class MyEnum2 {
    public enum MyEnum3 {
        BOLED, IITALIC, UNDERLINE
    }

    public void applyStyles(Set<MyEnum3> styles){
        System.out.println("===test===>"+styles);
    }
}
