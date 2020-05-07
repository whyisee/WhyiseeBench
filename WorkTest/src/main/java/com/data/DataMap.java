package com.data;

import java.util.Arrays;
import java.util.HashMap;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/1/7 13:18
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class DataMap extends HashMap implements ZData {
    public DataMap() {
    }

    @Override
    public Object get(String name, Object obj) {
        Object value = this.get(name);
        return value == null ? obj :value;
    }

    @Override
    public String[] getNames(){
        //这个0可以随便写
        String[] names = (String[])this.keySet().toArray(new String[0]);
        Arrays.sort(names);
        return names;
    }

    @Override
    public String getString(String name){
        Object value = this.get(name);
        return value == null ? null : value.toString();
    }

    @Override
    public String getString(String name, String defaultValue) {
        Object value = this.get(name,defaultValue);
        return value.toString() ;
    }

    @Override
    public int getInt(String name) {
        return this.getInt(name,0);
    }

    @Override
    public int getInt(String name, int defaultValue) {
        String value = getString(name,"");
        return "" == value ?  defaultValue : Integer.parseInt(value);
    }

    @Override
    public double getDouble(String name){
        return this.getDouble(name,0.0D);
    }

    @Override
   public double getDouble(String name, double defaultValue){
       String value = getString(name,"");
       return "" == value ?  defaultValue : Double.parseDouble(value);
   }

    @Override
   public boolean getBoolean(String name){
       return this.getBoolean(name,false);
   }

    @Override
   public boolean getBoolean(String name, boolean defaultValue){
       String value = getString(name,"");
       return "" == value ?  defaultValue : Boolean.parseBoolean(value);
   }




}
