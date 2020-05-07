package com.data;

import java.util.Map;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/1/7 13:07
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public interface ZData extends Map {

    Object get(String name, Object obj);

    String[] getNames();

    String getString(String var1);

    String getString(String var1, String var2);

    int getInt(String var1);

    int getInt(String var1, int var2);

    double getDouble(String var1);

    double getDouble(String var1, double var2);

    boolean getBoolean(String var1);

    boolean getBoolean(String var1, boolean var2);
}
