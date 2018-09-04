package com.syscxp.biz.service.activiti.VacationRequest;

import org.activiti.engine.task.Task;

import java.util.List;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-08-28.
 * @Description: .
 */

public interface VacationRequestService {
    void startProcess();

    List<Task> getTasks();

    void completeTask(String taskId);
}
