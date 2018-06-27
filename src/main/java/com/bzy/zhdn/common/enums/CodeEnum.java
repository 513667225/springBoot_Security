package com.bzy.zhdn.common.enums;
/**
 * @Auther: lkw
 * @Date: 2018/6/25 21:01
 * @Description: Code 枚举
 */
public enum CodeEnum {

    SUCCESS(200,"success"),

    FAIL(500,"fail"),
    ;

    private Integer code;

    private String explain;

    CodeEnum(Integer code, String explain) {
        this.code = code;
        this.explain = explain;
    }

    public Integer getCode() {
        return code;
    }

    public String getExplain() {
        return explain;
    }
}
