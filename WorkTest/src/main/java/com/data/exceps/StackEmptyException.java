package com.data.exceps;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/2/12 10:04
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class StackEmptyException extends RuntimeException {
    public StackEmptyException(String err){
        super(err);
    }
}
