package com.base.thread.lock;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/25 17:23
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class TransferMoney {
    private static final Object tieLock = new Object();

    public void transferMoney(final Account fromAcct,final Account toAcct,final double amount) throws Exception {
        class Helper {
            public void transfer() throws Exception {
                if (fromAcct.compareTo(amount) <0){
                    throw new Exception();
                }else{
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                }
            }

        }

        int fromHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);

        if (fromHash < toHash){
            synchronized (fromAcct){
                synchronized (toAcct){
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized(toAcct){
                synchronized(fromAcct){
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock){
                synchronized (fromAcct){
                    synchronized (toAcct){
                        new Helper().transfer();
                    }
                }
            }
        }
    }
}
