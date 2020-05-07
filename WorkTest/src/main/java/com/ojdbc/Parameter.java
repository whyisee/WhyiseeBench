package com.ojdbc;

import java.util.ArrayList;
import java.util.List;

public class Parameter {
    private List list = new ArrayList();

    public Parameter() throws Exception {
    }

    public Parameter(Object[] params) throws Exception {
        for(int i = 0; i < params.length; ++i) {
            this.list.add(params[i]);
        }

    }

    public Object get(int index) throws Exception {
        return this.list.get(index);
    }

    public void add(Object value) throws Exception {
        this.list.add(value);
    }

    public void add(int index, Object value) throws Exception {
        this.list.add(index, value);
    }

    public void addAll(Parameter param) throws Exception {
        for(int i = 0; i < param.size(); ++i) {
            this.list.add(param.get(i));
        }

    }

    public int size() throws Exception {
        return this.list.size();
    }

    public String toString() {
        return this.list.toString();
    }
}
