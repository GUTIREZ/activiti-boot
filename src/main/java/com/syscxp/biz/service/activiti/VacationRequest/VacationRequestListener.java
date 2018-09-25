package com.syscxp.biz.service.activiti.VacationRequest;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-10.
 * @Description: .
 */
public class VacationRequestListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("this is a task listener!");
        System.out.println(delegateTask.getProcessInstanceId());
    }
}
