package com.oh.scada.task.operation;

/**
 * Created by wjy on 2018/7/16.
 */
public enum OperationStatusEnum {

    WAITING("等待执行"),
    RUNNING("正在执行"),
    FAILED("执行失败"),
    SUCCEEDED("执行成功");


    private String desc;

    OperationStatusEnum(String desc) {
        this.desc = desc;
    }
}
