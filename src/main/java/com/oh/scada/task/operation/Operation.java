package com.oh.scada.task.operation;

import java.util.List;

/**
 * @author daimeng
 */
public interface Operation {
    public void run();
    public void reverseRun();
    public Integer status();
}
