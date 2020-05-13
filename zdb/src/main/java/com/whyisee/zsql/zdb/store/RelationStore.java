package com.whyisee.zsql.zdb.store;

import java.util.List;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/4/24 17:53
 * @version 1.0
 * @Modified By:
 * @used in: zdb
 */
public interface RelationStore {

    //
    public int createTable();

    public int dropTable();


    public List select();

    public int insert();

    public int delete();

}
