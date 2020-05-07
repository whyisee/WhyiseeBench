package com.ojdbc;

import com.data.DataMap;
import com.data.ZData;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/1/7 16:43
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ColumnInfo implements ZColumn{
    private String columnName;
    private int columnType;
    private String columnDesc;
    private int columnSize;
    private int decimalDigits;
    private boolean key;
    private boolean nullable;

    public ColumnInfo() {
    }
    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public int getColumnType() {
        return columnType;
    }

    @Override
    public void setColumnType(int columnType) {
        this.columnType = columnType;
    }

    @Override
    public String getColumnDesc() {
        return columnDesc;
    }

    @Override
    public void setColumnDesc(String columnDesc) {
        this.columnDesc = columnDesc;
    }

    @Override
    public int getColumnSize() {
        return columnSize;
    }

    @Override
    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    @Override
    public int getDecimalDigits() {
        return decimalDigits;
    }

    @Override
    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    @Override
    public boolean isKey() {
        return key;
    }

    @Override
    public void setKey(boolean key) {
        this.key = key;
    }

    @Override
    public boolean isNullable() {
        return nullable;
    }

    @Override
    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    @Override
    public ZData toData() {
        ZData column = new DataMap();
        column.put("COLUMN_NAME", this.columnName);
        column.put("COLUMN_TYPE", String.valueOf(this.columnType));
        column.put("COLUMN_DESC", this.columnDesc);
        column.put("COLUMN_SIZE", String.valueOf(this.columnSize));
        column.put("DECIMAL_DIGITS", String.valueOf(this.decimalDigits));
        column.put("KEY", String.valueOf(this.key));
        column.put("NULLABLE", String.valueOf(this.nullable));
        return column;
    }


}
