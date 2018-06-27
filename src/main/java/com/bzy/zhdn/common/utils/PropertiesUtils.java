package com.bzy.zhdn.common.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.setting.dialect.Props;

import java.io.FileNotFoundException;

/**
 * @Auther: lkw
 * @Date: 2018/6/25 21:37
 * @Description: PropertiesUtils
 */
public class PropertiesUtils {

    private static final Log log = LogUtils.getRuntimeLogger();

    private static final String confPath = "classpath:conf/sys_config.properties";

    /**
     * @Desc: 从 confPath 中获取Value
     * @Param: Key
     * @Return: Value
     * @Auther: lkw
     * @Date: 2018/6/25 22:11
     */
    public static String valueOfKey(final String key) throws Exception {
        Props props = null;
        try{
            props = new Props(confPath);
        }catch (Exception e){
            String msg =  e.getMessage();
            log.error(msg);
            throw new FileNotFoundException(msg);
        }
        String value = props.getStr(key);
        if (StrUtil.isEmpty(value)) {
            String msg =  "Resource of path [" + confPath + "] not exist key: " + key;
            log.warn(msg);
            throw new Exception(msg);
        }
        return value;
    }

}
