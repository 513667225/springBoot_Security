package com.bzy.zhda.common.enums;
/**
 * @Auther: lkw
 * @Date: 2018/6/25 11:33
 * @Description: Log 枚举
 */
public enum LogEnum {

    MUSIC("music"),

    SYSTEM("system"),

    RUNTIME("runtime"),
    ;

    private String type;

    LogEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
