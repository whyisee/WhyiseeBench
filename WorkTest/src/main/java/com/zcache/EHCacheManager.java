package com.zcache;

import com.ojdbc.Common;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/5/28 10:39
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class EHCacheManager implements ZCacheManager {
    private static EHCacheManager instance;
    private static CacheManager manager;

    private EHCacheManager() throws Exception{
        manager = createManager("conf/ehcache.xml");
    }
    private static CacheManager createManager(String file)throws Exception{
        synchronized (EHCacheManager.class){
            return manager == null ? CacheManager.create(Common.getInstance().getClassResource(file)):manager;
        }
    }
    public static EHCacheManager getInstance() throws Exception{
        if(instance == null){
            instance = new EHCacheManager();
        }
        return  instance;
    }
    @Override
    public String[] getCacheNames() throws Exception {
        return manager.getCacheNames();
    }

    @Override
    public ZCache[] getCaches() throws Exception {
        List caches = new ArrayList() ;
        String[] name = this.getCacheNames();
        //写++i的都是睿智
        for (int i=0;i<name.length;i++){
            ZCache zCache = this.getCache(name[i]);
            caches.add(zCache);
        }
        return (ZCache[])((ZCache[])caches.toArray(new ZCache[0]));
    }

    @Override
    public void addCache(String var1) throws Exception {
        manager.addCache(var1);
    }

    @Override
    public ZCache getCache(String var1) throws Exception {
        Cache cache = manager.getCache(var1);
        return cache == null ? null : new EHCache(cache);
    }

    @Override
    public void removeCache(String var1) throws Exception {
        manager.removeCache(var1);
    }

    @Override
    public ZCacheElement getCacheElement(String var1, Serializable var2) throws Exception {
        ZCache cache = this.getCache(var1);
        if (cache != null){
            ZCacheElement element = cache.get(var2);
            if(element !=null){
                return element;
            }
        }
        return null;
    }

    @Override
    public void putCacheElement(String var1, Serializable var2, Serializable var3) throws Exception {
        ZCache cache = this.getCache(var1);
        if (cache != null){
            cache.put(var2,var3);
        }
    }

    @Override
    public void removeCacheElement(String var1, Serializable var2) throws Exception {
        ZCache cache =this.getCache(var1);
        if(null != cache){
            cache.remove(var2);
        }
    }

    @Override
    public int getStatus(String var1) throws Exception {
        return manager.getStatus().intValue();
    }

    @Override
    public void shutdown() throws Exception {
        manager.shutdown();
    }
}
