package com.oh.scada.task.excutor;

import com.oh.scada.task.taskManager.TaskManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author daimeng
 */
@Configuration
public class TaskExecutorConfiguration {
    @Bean(initMethod = "init")
    public TaskExecutor taskExecutor(){
        return new TaskExecutor();
    }
}
