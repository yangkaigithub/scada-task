package com.oh.scada.task.operation;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author tpusers
 * @date 2018/6/3
 */

@Service
public class OperationImpl implements  Operation{

    @Override
    public void operate(OperationParameter operationParameter){}
    @Override
    public void bashOperate(List<OperationParameter> operationParameters){}
    @Override
    public void reOperate(OperationParameter operationParameter){}


}
