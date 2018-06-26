package com.oh.scada.task.operation;

import lombok.Data;

/**
 * @author daimeng
 */
@Data
public class OperationParameter {

    private String vid;

    private Object opCode;

    private OperationTypeEnum type;

}
