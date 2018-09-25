package com.syscxp.biz.service.activiti.businessApprove;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-12.
 * @Description: .
 */
@Component(value = "completeTaskListener")
public class CompleteTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("this is a task listener:" + delegateTask.getEventName());
    }
}
