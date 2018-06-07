package com.oh.scada.task.taskManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @author daimeng
 */
@Configuration
public class TaskManagerConfiguration {
    @Bean(initMethod = "init")
    public TaskManager taskManager(){
        return new TaskManager();
    }
}
