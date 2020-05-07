package com.data;

import com.data.exceps.OutOfSizeException;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/17 17:37
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */

        // 单链表反转
        // 链表中环的检测
        // 两个有序的链表合并
        // 删除链表倒数第 n 个结点 xxxx
        // 求链表的中间结点

public class ZSingleList {
    private ZNodeImpl zNode;
    private ZNodeImpl head;
    private ZNodeImpl tail;
    private int size;

    public ZSingleList(){
        size=0;
        head = new ZNodeImpl();
        tail = new ZNodeImpl();
        head.setData("00");
        head.setNext(null);
        tail.setNext(head);

    }


    /**
     * use for : 链表尾增加
     *@author zoukh
     *@Created in:  2020/2/17 21:26
     *@Modified By:
     *@version 1.0
     *@used in: ZSingleList
     */
    public void addLast(ZNodeImpl node){
        ZNodeImpl last = getTail();
        last.setNext(node);
        node.setNext(null);
        tail.setNext(node);
        size++;
    }
    /**
     * use for : 在链表头插入
     *@author zoukh
     *@Created in:  2020/2/17 20:04
     *@Modified By:
     *@version 1.0
     *@used in: ZSingleList
     */
    public void addHead(ZNodeImpl node){
        node.setNext(getHead());
        head.setNext(node);
        size++;
    }

    /**
     * use for : 链表头删除
     *@author zoukh
     *@Created in:  2020/2/17 20:04
     *@Modified By:
     *@version 1.0
     *@used in: ZSingleList
     */
    public void delHead(){
        if(size>0){
            size--;
            head.setNext(head.getNext().getNext());
        }
    }

    /**
     * use for : 链表尾删除
     *@author zoukh
     *@Created in:  2020/2/17 20:04
     *@Modified By:
     *@version 1.0
     *@used in: ZSingleList
     */
    public void delLast(){
        ZNodeImpl last = getHead();
        while(last.getNext().getNext()!=null){
            last = last.getNext();
        }
        last.setNext(null);
        tail.setNext(last);
        size--;
    }
    /**
     * use for : 获取任意位置节点
     *@author zoukh
     *@Created in:  2020/2/17 21:17
     *@Modified By:
     *@version 1.0
     *@used in: ZSingleList
     */
    public ZNodeImpl get(int index){
        if (index>=0&&index<size) {
            ZNodeImpl thisNode = getHead();
            while (thisNode != null && index > 0) {
                thisNode=thisNode.getNext();
                index--;
            }
            return thisNode;

        }else{
            throw new OutOfSizeException("Out of size!");
        }
    }

    /**
     * use for : 链表任意位置插入
     *@author zoukh
     *@Created in:  2020/2/17 20:05
     *@Modified By:
     *@version 1.0
     *@used in: ZSingleList
     */
    public void add(int index, ZNodeImpl zNode) {
        if (index == 0) {
            addHead(zNode);
        } else if(index==size){
            addLast(zNode);
        } else if (0 < index && index < size) {
            ZNodeImpl last = getHead().getNext();
            ZNodeImpl thisNode = getHead();
            while (last != null && index > 1) {
                last = last.getNext();
                thisNode =thisNode.getNext();
                index--;
            }
            thisNode.setNext(zNode);
            zNode.setNext(last);
            size++;
        } else {
            throw new OutOfSizeException("Out of size!");
        }
    }

    /**
     * use for : 任意位置删除
     *@author zoukh
     *@Created in:  2020/2/17 21:04
     *@Modified By:
     *@version 1.0
     *@used in: ZSingleList
     */
    public void del(int index){
        if (index == 0) {
            delHead();
        } else if(index==size){
            delLast();
        } else if (0 < index && index < size) {
            ZNodeImpl last = getHead().getNext();
            ZNodeImpl thisNode = getHead();
            while (last != null && index > 1) {
                last = last.getNext();
                thisNode =thisNode.getNext();
                index--;
            }
            thisNode.setNext(last);
            //zNode.setNext(last);
            size--;

        } else {
            throw new OutOfSizeException("Out of size!");
        }

    }

    /**
     * use for : 倒数位置删除?
     *@author zoukh
     *@Created in:  2020/2/17 21:13
     *@Modified By:
     *@version 1.0
     *@used in: ZSingleList
     */
    public void delBack(int index){
        del(size+1-index);
    }


    /**
     * use for : 链表反转
     *@author zoukh
     *@Created in:  2020/2/17 21:30
     *@Modified By:
     *@version 1.0
     *@used in: ZSingleList
     */
    public void reverse(){
        //System.out.println("test==>"+size);
        if(size<=1){
            return;
        } else if(size==2){
            ZNodeImpl last = getHead().getNext();
            ZNodeImpl thisNode = getHead();
            this.delLast();
            this.addHead(last);

        }else {
            ZNodeImpl temp = new ZNodeImpl();
            ZNodeImpl temp1 = getHead();
            temp.setNext(temp1);
            ZNodeImpl temp2 = getHead();
            ZNodeImpl temp3 = new ZNodeImpl();
            while (temp2.getNext()!=null){
                //System.out.println("test==>"+temp2.getNext().getData());
                temp3=temp2.getNext().getNext();
                temp2.getNext().setNext(temp.getNext());
                temp.setNext(temp2.getNext());
                //temp2=temp3;
                temp2.setNext(temp3);

            }
            head.setNext(getTail());
            tail.setNext(getHead());


        }
    }






    public void display(){
        ZNodeImpl last = getHead();
        while(last!=null){
            System.out.println("Test--------20:48--->:"+last.getData());
            last = last.getNext();
        }

    }



    public ZNodeImpl getHead(){
        return head.getNext();
    }

    public ZNodeImpl getTail(){
        return tail.getNext();
    }

    public int getSize(){
        return size;
    }





}
