package com.zcache;

import java.io.Serializable;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/5/27 17:47
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public interface ZCacheElement {
    Serializable getKey() throws Exception;
    Serializable getValue() throws Exception;
}
