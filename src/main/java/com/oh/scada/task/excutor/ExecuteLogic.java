package com.oh.scada.task.excutor;

import com.oh.scada.task.Task.Task;
import com.oh.scada.task.operation.Operation;
import com.oh.scada.task.operation.OperationParameter;
import com.oh.scada.task.operation.OperationTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;


/**
 * @author daimeng
 */
public class ExecuteLogic{
    @Autowired
    Operation operation;

    private Task task;

    public ExecuteLogic(Task task){
        this.task = task;
    }

    public void execute() {
        //获取任务处理队列
        List<List<OperationParameter>> operationsList = task.getOperations();
        //任务回滚队列
        LinkedList<OperationParameter> rsysOperationsList = new LinkedList<>();
        try{
            //执行任务
            operationsList.forEach(paraOperations->{
                //获得该一步的可并行操作
                paraOperations.forEach(paraOperation->{
                    operation.operate(paraOperation);
                    OperationParameter operationParameter = new OperationParameter();
                    operationParameter.setVid(paraOperation.getVid());
                    operationParameter.setOpCode(paraOperation.getOpCode());
                    operationParameter.setType(paraOperation.getType());
                    rsysOperationsList.push(operationParameter);
                });

                //todo:
//                while(){
                    //获取操作完成状态,直至完成
//                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //回滚
            while(rsysOperationsList.size() > 0) {
                OperationParameter rsysOperation = rsysOperationsList.pop();
                OperationParameter operationParameter = new OperationParameter();
                operationParameter.setVid(rsysOperation.getVid());
                operationParameter.setOpCode(rsysOperation.getOpCode());
                operationParameter.setType(rsysOperation.getType());
                operation.reOperate(operationParameter);
            }
        }

    }
}
