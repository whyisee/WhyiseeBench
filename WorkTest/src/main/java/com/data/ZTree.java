package com.data;

import java.util.List;
import java.util.TreeMap;

/**
 * use for : 树-乞丐版
 *
 * @author zoukh
 * Created in:  2020/9/25 10:11
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class ZTree<T> {
    public ZTreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(ZTreeNode<T> root) {
        this.root = root;
    }

    private ZTreeNode<T> root;


}
