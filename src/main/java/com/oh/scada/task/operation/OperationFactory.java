package com.oh.scada.task.operation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wjy on 2018/7/15.
 */
public class OperationFactory {

    public  static Operation createOperation(OperationParameter parameter){



        //根据不不同的parameter  创建不同的operation

        return new WindowOperation(parameter);

    }


    public static List<List<Operation>> createOperationList(List<List<OperationParameter>> operationParamList) {
        LinkedList<List<Operation>> operationList = new LinkedList<>();

        for (List<OperationParameter> operationsParams:operationParamList) {
            ArrayList<Operation> operations = new ArrayList<>();
            for (OperationParameter operationParam:operationsParams) {
                operations.add(createOperation(operationParam));
            }
            operationList.add(operations);
        }
        return operationList;
    }
}
