package com.zoukh.zsql.zdb.store;

import java.io.IOException;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/4/24 17:45
 * @version 1.0
 * @Modified By:
 * @used in: zdb
 */
public interface KVStore {
    //取
    public String get(String key);

    //存
    public int put(String key,String value) throws IOException;

    //判断是否存在
    public boolean isExists(String key);

    
}
