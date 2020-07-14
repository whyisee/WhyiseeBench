package com.base.advance.generic;

import java.util.Date;

public class DateIntervel extends Pair<Date,Date> {
    @Override
    public void setSecond(Date second){
        if(second.compareTo(getFirst()) >=0 ){
            super.setSecond(second);
        }
    }
}
