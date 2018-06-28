package com.bzy.zhda.common.utils;

import cn.hutool.log.Log;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * @Auther: lkw
 * @Date: 2018/6/25 17:00
 * @Description: 参数体
 */
public class P extends HashMap<String, Object>  {

    private static final Log log = LogUtils.getSystemLogger();

    public static P ok() {
        return new P();
    }

    private void validateIsEmpty(String key) throws Exception{
        if (StringUtils.isEmpty(this.get(key))) {
            String msg = "找不到key[ "+ key + " ] " + "的值";
            log.error(msg);
            throw new Exception(msg);
        }

    }

    /**
     * @remark: lkw created by time: 2018/6/27 13:34
     */
    public String getString(String key) throws Exception{
        validateIsEmpty(key);
        String value = null;
        try{
            value = String.valueOf(this.get(key));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return value;
    }

    /**
     * @remark: lkw created by time: 2018/6/27 13:34
     */
    public Integer getInteger(String key) throws Exception{
        validateIsEmpty(key);
        Integer value = null;
        try{
            value = Integer.parseInt(String.valueOf(this.get(key)));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return value;
    }

    /**
     * @remark: lkw created by time: 2018/6/27 13:34
     */
    public Long getLong(String key) throws Exception{
        validateIsEmpty(key);
        Long value = null;
        try{
            value = Long.parseLong(String.valueOf(this.get(key)));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return value;
    }

    /**
     * @remark: lkw created by time: 2018/6/27 13:34
     */
    public double getDouble(String key) throws Exception{
        validateIsEmpty(key);
        double value = 0f;
        try{
            value = Double.parseDouble(String.valueOf(this.get(key)));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return value;
    }

}
