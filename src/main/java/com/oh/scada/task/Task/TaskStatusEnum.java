package com.oh.scada.task.Task;

/**
 * Created by wjy on 2018/7/16.
 */
public enum TaskStatusEnum {

    WAITING("等待分配"),
    RUNNING("正在执行"),
    FAILED("执行失败"),
    SUCCEEDED("执行成功"),
    CANCELED("已取消");


    private String desc;

    TaskStatusEnum(String desc) {
        this.desc = desc;
    }
}
