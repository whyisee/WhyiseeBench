package com.zoukh.zsql.zdb.store.imp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/4/24 18:24
 * @version 1.0
 * @Modified By:
 * @used in: zdb
 */
class KVStoreBaseTest {
    @Autowired
    private KVStoreBase kvStoreBase;
    @Test
    public void put() throws IOException {
        //kvStoreBas
        //kvStoreBase.put("test2","1233");
        KVStoreBase kvStoreBase2=new KVStoreBase();
        kvStoreBase2.put("test2","1233");
        System.out.println("test==>"+kvStoreBase2.get("test2"));

    }
}