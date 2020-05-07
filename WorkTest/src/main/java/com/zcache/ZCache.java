package com.zcache;

import java.io.Serializable;
import java.util.List;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/5/27 17:44
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public interface ZCache {
    void put(ZCacheElement var1) throws Exception;
    void put(Serializable key ,Serializable value) throws Exception;
    ZCacheElement get(Serializable var1) throws Exception;
    boolean remove(Serializable var1) throws Exception;
    void removeAll() throws Exception;
    String getName() throws  Exception;
    int getStatus() throws Exception;
    List getKeys() throws Exception;
    int getSize() throws Exception;
/*    int getHitCount() throws Exception;
    int getMissCountExpried() throws Exception;
    int getMissCountNotFound() throws Exception;*/
}
