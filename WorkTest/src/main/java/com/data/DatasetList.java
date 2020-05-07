package com.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/1/7 14:46
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class DatasetList extends ArrayList implements ZDataset {
    private String serializableId;
    private String serializablePath;
    private boolean serializable;
    private boolean batchSerializable;
    private int batchPageSize;
    private int batchPageCount;
    private Map transactKeys;

    public DatasetList() {
    }

    @Override
    public String getSerializableId() {
        return this.serializableId;
    }

    @Override
    public void setSerializableId(String serializableId) {
        this.serializableId=serializableId;
    }

    @Override
    public String getSerializablePath() {
        return this.serializablePath;
    }

    @Override
    public void setSerializablePath(String serializablePath) {
        this.serializablePath=serializablePath;
    }

    @Override
    public boolean isSerializable() {
        return this.serializable;
    }

    @Override
    public void setSerializable(boolean serializable) {
        this.serializable=serializable;
    }

    @Override
    public boolean isBatchSerializable() {
        return this.batchSerializable;
    }

    @Override
    public void setBatchSerializable(boolean batchSerializable) {
        this.batchSerializable=batchSerializable;
    }

    @Override
    public int getBatchPageSize() {
        return this.batchPageSize;
    }

    @Override
    public void setBatchPageSize(int batchPageSize) {
        this.batchPageSize=batchPageSize;
    }

    @Override
    public int getBatchPageCount() {
        return this.batchPageCount;
    }

    @Override
    public void setBatchPageCount(int batchPageCount) {
        this.batchPageCount=batchPageCount;
    }

    @Override
    public Object get(int index, String name) {
        Object value = (ZData)get(index);
        return value == null ? null :((ZData) value).get(name);
    }

    @Override
    public Object get(int index, String name, Object defaultValue) {
        Object value = (ZData)get(index);
        return value == null ? defaultValue :((ZData) value).get(name);

    }

    @Override
    public String[] getNames() {
        return this.size() > 0 ? ((ZData)this.get(0)).getNames(): null;
    }

    /**
     * use for : 类型转换
     *@author zoukh
     *@Created in:  2019/1/7 15:14
     *@Modified By:会遍历整个list ,
     *@version 1.0
     *@used in: DatasetList
     */
    @Override
    public ZData toData() throws Exception {
        ZData zData = new DataMap();
        Iterator iterator = this.iterator();

        while (iterator.hasNext()){
           ZData element = (ZData)iterator.next();
           Iterator it = element.keySet().iterator();

           while (it.hasNext()){
               String key = it.next().toString();

               if(zData.containsKey(key)){
                 //  List list = (List)zData.get(key);
                 //  list.add(element.get(key));
                   continue;
               }else {
                   List list = (List)zData.get(key);
                   list.add(element.get(key));
                   zData.put(key,list);
               }
           }
        }
        return  zData;
    }

    @Override
    public int count() {
        return this.size();
    }

    /**
     * use for : 排序
     *@author zoukh
     *@Created in:  2019/1/7 15:16
     *@Modified By:暂时不用,后期实现
     *@version 1.0
     *@used in: DatasetList
     */
    @Override
    public void sort(String var1, int var2) {
    }

    @Override
    public void sort(String var1, int var2, int var3) {

    }

    @Override
    public void sort(String var1, int var2, String var3, int var4) {

    }

    @Override
    public void sort(String var1, int var2, int var3, String var4, int var5, int var6) {

    }
}
