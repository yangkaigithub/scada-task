package com.oh.scada.task.operation;

import lombok.Data;

/**
 * @author daimeng
 */
@Data
public class OperationParameter {

    private String vid;

    private String opCode;

    private OperationTypeEnum type;

}
