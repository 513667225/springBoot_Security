package com.bzy.zhdn.common.utils;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.bzy.zhdn.common.enums.LogEnum;

/**
 * @Auther: lkw
 * @Date: 2018/6/25 11:39
 * @Description: LogUtils 为非SpringBean提供Log
 */
public class LogUtils {


   /**
    * @remark: lkw created by time: 2018/6/25 22:08
    */
    public static Log getMusicLogger() {
        return LogFactory.get(LogEnum.MUSIC.getType());
    }

    /**
     * @remark: lkw created by time: 2018/6/25 22:09
     */
    public static Log getSystemLogger() {
        return LogFactory.get(LogEnum.SYSTEM.getType());
    }

    /**
     * @remark: lkw created by time: 2018/6/25 22:09
     */
    public static Log getRuntimeLogger() {
        return LogFactory.get(LogEnum.RUNTIME.getType());
    }

}
