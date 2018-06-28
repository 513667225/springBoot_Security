package com.bzy.zhda.common.exception;

/**
 * @Auther: lkw
 * @Date: 2018/6/27 14:48
 * @Description: CUDException  执行CUD操作时的Exception
 */
public class CUDException extends Exception {

    public CUDException(String message) {
        super(message);
    }
}
