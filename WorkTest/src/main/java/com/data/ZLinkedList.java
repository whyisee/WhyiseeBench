package com.data;

import org.apache.poi.ss.formula.functions.T;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/10/14 17:43
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ZLinkedList {
    private Node head;

    /**
     * use for : 从头部插入节点
     *@author zoukh
     *@Created in:  2019/10/14 18:39
     *@Modified By:
     *@version 1.0
     *@used in: ZLinkedList
     * @param value
     */
    public void addHeadNode(String value){
        Node newNode=new Node(value);
        if(head==null){
            head=newNode;
            return;
        }
        newNode.next=head;
        head=newNode;

    }

    /**
     * use for : 向尾部增加节点
     *@author zoukh
     *@Created in:  2019/10/14 18:43
     *@Modified By:
     *@version 1.0
     *@used in: ZLinkedList
     */

    public void addTailNode(T value){
        Node newNode=new Node(value);
        if(head==null){
            head=newNode;
            return;
        }
        Node last=head;
        while(last.next!=null){
            last=last.next;
        }
        last.next=newNode;

    }






    public class Node<T>{
        T value;
        Node next;
        public Node(T value){
            this.value=value;
            next = null;
        }

    }

}
