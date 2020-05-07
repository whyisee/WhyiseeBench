package com.ojdbc;

import com.data.ZData;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/1/7 16:41
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public interface ZColumn {
    String getColumnName();

    void setColumnName(String var1);

    int getColumnType();

    void setColumnType(int var1);

    String getColumnDesc();

    void setColumnDesc(String var1);

    int getColumnSize();

    void setColumnSize(int var1);

    int getDecimalDigits();

    void setDecimalDigits(int var1);

    boolean isKey();

    void setKey(boolean var1);

    boolean isNullable();

    void setNullable(boolean var1);

    ZData toData();
}
