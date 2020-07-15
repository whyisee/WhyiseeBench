package com.data;

public interface MyQueue<E> {
    void add(E e);
    E remove(E e);
    int size();
}
