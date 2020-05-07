package com.data;

import java.util.List;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/1/7 14:43
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public interface ZDataset extends List {
    int ORDER_ASCEND = 0;
    int ORDER_DESCEND = 1;
    int TYPE_STRING = 2;
    int TYPE_INTEGER = 3;
    int TYPE_DOUBLE = 4;

    String getSerializableId();

    void setSerializableId(String var1);

    String getSerializablePath();

    void setSerializablePath(String var1);

    boolean isSerializable();

    void setSerializable(boolean var1);

    boolean isBatchSerializable();

    void setBatchSerializable(boolean var1);

    int getBatchPageSize();

    void setBatchPageSize(int var1);

    int getBatchPageCount();

    void setBatchPageCount(int var1);

    Object get(int var1, String var2);

    Object get(int var1, String var2, Object var3);

    String[] getNames();

    ZData toData() throws Exception;

    int count();

    void sort(String var1, int var2);

    void sort(String var1, int var2, int var3);

    void sort(String var1, int var2, String var3, int var4);

    void sort(String var1, int var2, int var3, String var4, int var5, int var6);
}
