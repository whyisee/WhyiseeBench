package com.base.utils;

public enum MyEnum {
    WEEK(2) {
        @Override
        public String toString() {
            return "WEEK111";
        }
    },
    MONTH(1){

    };
    public final  int day;
    private MyEnum(int day){
        this.day=day;
    }
    enum xxx {
        a,b;enum xx {dd}
    }

}
