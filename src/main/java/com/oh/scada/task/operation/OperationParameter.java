package com.oh.scada.task.operation;

import lombok.Data;

/**
 * @author daimeng
 */
@Data
public class OperationParameter {

    private String vid;

    private String statusVid;

    private String statusOpCode;

    private Object opCode;

    private OperationTypeEnum type;

    private Integer timeOut;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getStatusVid() {
        return statusVid;
    }

    public void setStatusVid(String statusVid) {
        this.statusVid = statusVid;
    }

    public String getStatusOpCode() {
        return statusOpCode;
    }

    public void setStatusOpCode(String statusOpCode) {
        this.statusOpCode = statusOpCode;
    }

    public Object getOpCode() {
        return opCode;
    }

    public void setOpCode(Object opCode) {
        this.opCode = opCode;
    }

    public OperationTypeEnum getType() {
        return type;
    }

    public void setType(OperationTypeEnum type) {
        this.type = type;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }
}
