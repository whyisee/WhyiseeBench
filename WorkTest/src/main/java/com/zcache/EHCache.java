package com.zcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import java.io.Serializable;
import java.util.List;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/5/27 18:01
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class EHCache implements ZCache {
    private Cache cache;
    public EHCache(Cache cache) throws Exception{
        this.cache=cache;
    }

    public void put(ZCacheElement zCacheElement) throws Exception{
        this.cache.put(new Element(zCacheElement.getKey(),zCacheElement.getValue()));
    }

    public void put(Serializable key,Serializable value) throws Exception{
        ZCacheElement zCacheElement = new EHCacheElement(key,value) ;
        this.put(zCacheElement);
    }

    public ZCacheElement get(Serializable key) throws Exception{
        Element element = this.cache.get(key);
        return element == null ? null : new EHCacheElement(element);
    }

    public boolean remove(Serializable key)throws Exception{
        return this.cache.remove(key);
    }

    public void removeAll()throws Exception{
        this.cache.removeAll();
    }

    public String getName()throws Exception{
        return this.cache.getName();
    }

    public int getStatus()throws Exception{
        return this.cache.getStatus().intValue();
    }

    @Override
    public List getKeys() throws Exception {
        return this.cache.getKeys();
    }

    @Override
    public int getSize() throws Exception {
        return this.cache.getSize();
    }

/*    @Override
    public int getHitCount() throws Exception {
        return this.cache.getHitCount();
    }*/
}
