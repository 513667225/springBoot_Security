package com.bzy.zhdn.common.utils;

import com.bzy.zhdn.common.enums.CodeEnum;

import java.util.HashMap;

/**
 * @Auther: lkw
 * @Date: 2018/6/25 17:00
 * @Description: 消息体
 */
public class R extends HashMap<String, Object> {

    /**
     * @Desc: Key 枚举
     * @Auther: lkw
     * @Date: 2018/6/25 21:33
     */
    protected enum KeyEnum {
        CODE("code"), DATA("data"), MSG("msg"),;
        private String value;
        KeyEnum(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    public static R ok() {
        return new R();
    }

    public R data(Object data) {
        super.put(KeyEnum.DATA.getValue(), data);
        return this;
    }

    public R set(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static R fail(String msg) {
        return R.ok().set(KeyEnum.CODE.getValue(), CodeEnum.FAIL.getCode()).set(KeyEnum.MSG.getValue(), msg);
    }

    /**
     *
     * @Desc: fail 重载 默认msg
     * @Return: R
     * @Auther: lkw
     * @Date: 2018/6/25 21:24
     */
    public static R fail() {
        return R.ok().set(KeyEnum.CODE.getValue(), CodeEnum.FAIL.getCode()).set(KeyEnum.MSG.getValue(), CodeEnum.FAIL.getExplain());
    }

    public static R success(String msg) {
        return R.ok().set(KeyEnum.CODE.getValue(), CodeEnum.SUCCESS.getCode()).set(KeyEnum.MSG.getValue(), msg);
    }

    /**
     *
     * @Desc: success 重载 默认msg
     * @Return: R
     * @Auther: lkw
     * @Date: 2018/6/25 21:24
     */
    public static R success() {
        return R.ok().set(KeyEnum.CODE.getValue(), CodeEnum.SUCCESS.getCode()).set(KeyEnum.MSG.getValue(), CodeEnum.SUCCESS.getExplain());
    }

}
