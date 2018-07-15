package com.oh.scada.task.operation;

/**
 * Created by wjy on 2018/7/15.
 */
    public class WindowOperation implements Operation{

    private OperationParameter parameter;


    public WindowOperation(OperationParameter parameter) {
        this.parameter = parameter;
    }


    @Override
    public void run() {
        System.out.println("run vid:"+parameter.getVid());
    }

    @Override
    public void reverseRun() {
        System.out.println("run reverse:");
    }

    @Override
    public Integer status() {
        return 1;
    }


}
