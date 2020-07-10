package com.base.auth;

public class MyClass {
    public String col1;
    private String col2;
    String col3;
    protected String col4;
    //public final String col5;


    public void func1(){

    }

    private void func2(){

    }

    void func3(){

    }

    protected void func4(){

    }

    class TClass{
        MyClass myClass = new MyClass();
        String tcol1=myClass.col1;
    }

    class ZClass extends MyClass{
        public void zfunc(){
            //this.
        }
    }
}
