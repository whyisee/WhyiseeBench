package com.data;

import java.util.List;

/**
 * use for : 树节点-乞丐版
 *
 * @author zoukh
 * Created in:  2020/9/25 10:56
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class ZTreeNode <T>{

        private T data;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public ZTreeNode<T> getParentNode() {
            return parentNode;
        }

        public void setParentNode(ZTreeNode<T> parentNode) {
            this.parentNode = parentNode;
        }

        public List<ZTreeNode<T>> getChildrenNodes() {
            return childrenNodes;
        }

        public void setChildrenNodes(List<ZTreeNode<T>> childrenNodes) {
            this.childrenNodes = childrenNodes;
        }

        private ZTreeNode<T> parentNode;
        private List<ZTreeNode<T>> childrenNodes;

        public T compute() {
            return data;
        }
        //TreeMap
}
