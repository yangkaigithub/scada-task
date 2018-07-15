package com.oh.scada.task.excutor;

import com.oh.scada.task.Task.Task;
import com.oh.scada.task.operation.Operation;
import com.oh.scada.task.operation.OperationFactory;
import com.oh.scada.task.operation.OperationParameter;
import com.oh.scada.task.operation.OperationTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;


/**
 * @author daimeng
 */
public class ExecuteLogic{


    private Task task;

    public ExecuteLogic(Task task){
        this.task = task;
    }

    public void execute() {
        //获取任务处理的参数队列
        List<List<OperationParameter>> operationParamList = task.getOperations();
        //获取operation队列
        List<List<Operation>> operationList = OperationFactory.createOperationList(operationParamList);
        //任务回滚operation队列
        LinkedList<LinkedList<Operation>> rsysOperationsList = new LinkedList<>();


        try{
            //执行任务
            operationList.forEach(operations->{
                //获得该一步的可并行操作
                rsysOperationsList.add(new LinkedList<Operation>());
                operations.forEach(operation->{
                    rsysOperationsList.peekLast().push(operation);
                    operation.run();

                });

                //todo:
                while(true){
//                    获取操作完成状态,直至完成
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //回滚

            rsysOperationsList.forEach(rsysOperations->{
                //获得该一步的可并行操作
                rsysOperations.forEach(rsysOperation->{
                    rsysOperationsList.peekLast().push(rsysOperation);
                    rsysOperation.reverseRun();

                });
                //todo:
                while (true){
                    //                    获取操作完成状态,直至完成
                }

            });

        }

    }
}
