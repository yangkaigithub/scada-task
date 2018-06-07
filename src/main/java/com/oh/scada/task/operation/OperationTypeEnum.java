package com.oh.scada.task.operation;

/**
 * @author daimeng
 */

public enum OperationTypeEnum {
    WAITTIME(1,"等待操作"),
    NORM(2,"普通操作");

    private Integer code;
    private String desc;

    OperationTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getName(){
        return name();
    }



}
