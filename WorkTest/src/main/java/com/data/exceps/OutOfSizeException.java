package com.data.exceps;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/17 20:08
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class OutOfSizeException extends RuntimeException {
    public OutOfSizeException(String err){
        super(err);
    }

}
