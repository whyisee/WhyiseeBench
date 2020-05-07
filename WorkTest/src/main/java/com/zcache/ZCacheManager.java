package com.zcache;

import java.io.Serializable;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/5/27 18:26
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public interface ZCacheManager {
    String SYS_COLUMNS_CACHE = "SYS_COLUMNS_CACHE";
    String SYS_KEYS_CACHE = "SYS_KEYS_CACHE";
    String SYS_DBSRC_CACHE = "SYS_DBSRC_CACHE";

    String[] getCacheNames() throws Exception;

    ZCache[] getCaches() throws Exception;

    void addCache(String var1) throws Exception;

    ZCache getCache(String var1) throws Exception;

    void removeCache(String var1) throws Exception;

    ZCacheElement getCacheElement(String var1, Serializable var2) throws Exception;

    void putCacheElement(String var1, Serializable var2, Serializable var3) throws Exception;

    void removeCacheElement(String var1, Serializable var2) throws Exception;

    int getStatus(String var1) throws Exception;

    void shutdown() throws Exception;
}
