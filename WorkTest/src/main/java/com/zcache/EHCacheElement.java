package com.zcache;

import net.sf.ehcache.Element;

import java.io.Serializable;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/5/27 17:55
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class EHCacheElement implements ZCacheElement {
    private Element element;
    public EHCacheElement(Element element){
        this.element=element;
    }
    public EHCacheElement(Serializable key,Serializable value){
        this.element=new Element(key,value);
    }

    @Override
    public Serializable getKey() throws Exception {
        return this.element.getKey();
    }

    @Override
    public Serializable getValue() throws Exception {
        return this.element.getValue();
    }
}
