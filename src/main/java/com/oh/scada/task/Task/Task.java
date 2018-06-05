package com.oh.scada.task.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class Task {
    private ArrayList<String> operateGrains;
    private ArrayList<HashMap<String,String>> operations;
    private String name;
    Task(ArrayList<String> operateGrains,ArrayList<HashMap<String,String>> operations){
        this.operateGrains = operateGrains;
        this.operations = operations;
    }

    public String getName(){
        return name;
    }

    public ArrayList<String> getOperateGrains() {
        return operateGrains;
    }

    public ArrayList<HashMap<String, String>> getOperations() {
        return operations;
    }
}
