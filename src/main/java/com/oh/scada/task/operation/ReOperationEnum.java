package com.oh.scada.task.operation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daimeng
 */
public class ReOperationEnum {
    public static Map<String,String> reOperationMaps = new HashMap<>();
    static{
        
        reOperationMaps.put("open","close");

    }

    public static Map<String,String> getReOperationMaps(){
        return reOperationMaps;
    }
}
