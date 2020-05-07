package com.ojdbc;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/1/7 17:52
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ColumnObject {
    private String name;
    private Object value;
    private int type;

    public ColumnObject(String name, Object value, int type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public Object getValue() {
        return this.value;
    }

    public int getType() {
        return this.type;
    }
}

