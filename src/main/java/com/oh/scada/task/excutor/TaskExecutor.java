package com.oh.scada.task.excutor;

import com.oh.scada.task.Task.Task;
import com.oh.scada.task.taskManager.TaskManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author wjy
 * @date 2018/6/3
 */
public class TaskExecutor {
    @Autowired
    TaskManager taskManager;

    private ExecutorService threadPool;

    public void init(){
        threadPool = Executors.newFixedThreadPool(10);
    }

    public void bootTask(Task task){
        ExecuteLogic executeLogic = new ExecuteLogic(task);
        threadPool.execute(executeLogic::execute);
    }

}

