package com.oh.scada.task.Task;

import com.oh.scada.task.operation.OperationParameter;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author daimeng
 */

@Data
public class Task {
    private List<String> operateGrains;
    private List<List<OperationParameter>> operations;
    private String name;

    public List<List<OperationParameter>> getOperations() {
        return operations;
    }

    public List<String> getOperateGrains() {
        return operateGrains;
    }

    public void setOperateGrains(List<String> operateGrains) {
        this.operateGrains = operateGrains;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
