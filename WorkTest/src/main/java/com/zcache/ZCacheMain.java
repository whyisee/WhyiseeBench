package com.zcache;

import com.data.ZLinkedList;
import com.data.ZNodeImpl;
import com.data.ZSingleList;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/17 16:45
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ZCacheMain {
    public static void main(String [] args){
        // ZLinkedList zLinkedList = new ZLinkedList();
        //zLinkedList.addHeadNode("1");



        ZSingleList zSingleList = new ZSingleList();
        ZNodeImpl zNode = new ZNodeImpl();
        zNode.setData("11");
        zSingleList.addLast(zNode);

        ZNodeImpl zNode2 = new ZNodeImpl();
        zNode2.setData("22");
        zSingleList.addLast(zNode2);

        ZNodeImpl zNode3 = new ZNodeImpl();
        zNode3.setData("0000");
        zSingleList.addHead(zNode3);

        ZNodeImpl zNode4 = new ZNodeImpl();
        zNode4.setData("4444");
        //zSingleList.delHead();
        //zSingleList.dealTail();
        zSingleList.add(3,zNode4);
        //zSingleList.delBack(1);
       ;
        zSingleList.display();

        zSingleList.reverse();

        StringBuffer sb = new StringBuffer("123");
        sb.reverse();


        //System.out.println("Test--------17:52--->:"+Integer.parseInt(Integer.));
//        System.out.println("Test--------17:52--->:"+ zSingleList.get(2).getData());

        zSingleList.display();

        //System.out.println("Test--------17:52--->:"+zSingleList.getHead().getData());
        //System.out.println("Test--------17:52--->:"+zSingleList.getTail().getData());

        //System.out.println("Test--------17:52--->:"+zSingleList.getHead().getNext().getData());
        //System.out.println("Test--------17:52--->:"+zSingleList.getHead().getNext().getNext().getData());



    }
}
