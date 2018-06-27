package com.bzy.zhdn.common.exception;

import com.bzy.zhdn.common.utils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: lkw
 * @Date: 2018/6/25 17:00
 * @Description: GlobalExceptionHandler
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @remark: lkw created by time: 2018/6/25 21:36
     */
    @ExceptionHandler(Exception.class)
    public @ResponseBody R handleAndReturnData(Exception e) {
        String message = e.getMessage();
        R r =null;
        if (e instanceof CUDException){
            r = R.success(message);
        }else{
            r = R.fail(message);
        }
        return r;
    }

}
