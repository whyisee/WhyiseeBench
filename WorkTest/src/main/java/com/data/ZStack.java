package com.data;

import com.data.exceps.StackEmptyException;

/**
 * use for :栈接口
 *
 * @author zoukh
 * Created in:  2020/2/12 9:59
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public interface ZStack {
    /**
     * use for : 获取栈大小
     *@author zoukh
     *@Created in:  2020/2/12 10:00
     *@Modified By:
     *@version 1.0
     *@used in: ZStack
     */
    public int getSize();

    public boolean isEmpty();

    public void push(Object e);

    public Object get() throws StackEmptyException;

    public Object getHead() throws StackEmptyException;

}
