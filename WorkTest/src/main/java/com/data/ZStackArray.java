package com.data;

import com.data.exceps.StackEmptyException;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/12 10:44
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ZStackArray implements ZStack {
    private final int LEN = 8;
    private Object[] elements;
    private int top;

    public ZStackArray(){
        top = -1;
        elements = new Object[LEN];
    }
    @Override
    public int getSize() {
        return top+1;
    }

    @Override
    public boolean isEmpty() {
        return top<0;
    }

    @Override
    public void push(Object e) {
        if (getSize()>=elements.length){
            expendSpace();
        }
        elements[++top]=e;
    }
    private void expendSpace(){
        Object[] a = new Object[elements.length*2];
        for(int i=0;i<elements.length;i++){
               a[i]=elements[i];
        }
        elements=a;
    }

    @Override
    public Object get() throws StackEmptyException {
        if(getSize()<0){
            throw new StackEmptyException("Error Stack is empty!");
        }
        Object o = elements[top];
        elements[top--]=null;
        return o;
    }

    @Override
    public Object getHead() throws StackEmptyException {
        if(getSize()<0){
            throw new StackEmptyException("Error Stack is empty!");
        }
        return elements[top];
    }
}
