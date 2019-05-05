package com.hb.springboot.enums;

public enum OperateUnit {

    /**
     * 被操作的单元
     */
    UNKNOWN("unknown "),
    USER("user"),
    EMPLOYEE("employee"),
    REDIS("redis");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    OperateUnit(String value){
        this.value = value;
    }
}
