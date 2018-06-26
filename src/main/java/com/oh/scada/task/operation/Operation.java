package com.oh.scada.task.operation;

import java.util.List;

/**
 * @author daimeng
 */
public interface Operation {
    public void operate(OperationParameter operationParameter);
    public void bashOperate(List<OperationParameter> operationParameters);
    public void reOperate(OperationParameter operationParameter);
    public
}
