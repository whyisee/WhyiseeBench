package com.base.advance.generic;

public class Pair<T,E> {

    private T first;
    private T second;
    private E num;

    public Pair(){first = null; second = null;}
    public Pair(T first, T second,E num ){
        this.first = first;
        this.second = second;
        this.num = num;
    }
    public <U> void display(U u){
        System.out.println("===test===>"+u);
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public E getNum() {
        return num;
    }

    public void setNum(E num) {
        this.num = num;
    }

    @Override
    public String toString(){
        return "pair:[first="+first+" ,second="+second+" ,num="+num+"]";
    }
}
