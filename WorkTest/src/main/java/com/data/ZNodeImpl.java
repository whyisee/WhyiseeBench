package com.data;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/17 16:54
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class ZNodeImpl implements ZNode {

    private  Object object;
    private ZNodeImpl next;

     public ZNodeImpl(){
         this(null,null);
     }
     public ZNodeImpl(Object object,ZNodeImpl next){
         this.object=object;
         this.next=next;
     }

     public ZNodeImpl getNext(){
         return next;
     }

     public void setNext(ZNodeImpl next){
         this.next=next;
     }

    @Override
    public Object getData() {
        return object;
    }

    @Override
    public void setData(Object obj) {
        this.object=obj;
    }
}
