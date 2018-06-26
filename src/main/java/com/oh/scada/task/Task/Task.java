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


public class Task {
    private List<String> operateGrains;
    private List<List<OperationParameter>> operations;
    private String name;
}
