package com.base.advance.generic;

public class NewPair <T> {
    private T first;
    private T second;

    public NewPair (){}

    public NewPair (T t){
        this.first=t;
        this.second=t;
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

    @Override
    public String toString(){
        return "NewPair:[first="+first+" ,secend="+second+"]";
    }
    public static boolean hasBull (NewPair<?> p){
        return p.getFirst()==null || p.getSecond()==null;
    }

    public static void swap(NewPair<?> p){
        swapHelper(p);
    }

    public static <T> void swapHelper(NewPair<T> p){
        T t = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }

    public static <T> NewPair<T> makeNewPair(Class<T> c) throws IllegalAccessException, InstantiationException {
        return new NewPair<>(c.newInstance());
    }
}
