package com.whyisee.zsql.zdb.store.imp;

import com.whyisee.zsql.zdb.common.FileIOUtils;
import com.whyisee.zsql.zdb.store.KVStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/4/24 18:03
 * @version 1.0
 * @Modified By:
 * @used in: zdb
 */
@Service
public class KVStoreBase implements KVStore {
    @Value("${db.data.kvpath}")
    private static String fileWritePath="F:\\git\\WhyiseeBench\\zdb\\data\\kvstore\\default.db";

    private static Map dbdata;

    //加载数据库
    static {
        System.out.println("test==>"+fileWritePath);
        //fileWritePath="F:\\JAVA\\zdb\\data\\kvstore\\default.db";
        dbdata=FileIOUtils.readFileByLines(fileWritePath);
    }

    @Override
    public String get(String key) {
       return dbdata.get(key).toString();
    }

    @Override
    public int put(String key, String value) throws IOException {
        //fileWritePath="F:\\JAVA\\zdb\\data\\kvstore\\default.db";

        //判断是否已存在
        //先忽略重复问题,应为加载的时候就会以最新的数据为准

        FileIOUtils.write(key+","+value+"\n",fileWritePath);
        dbdata.put(key,value);
        return 1;
    }

    @Override
    public boolean isExists(String key) {

        return dbdata.containsKey(key);
    }
}
