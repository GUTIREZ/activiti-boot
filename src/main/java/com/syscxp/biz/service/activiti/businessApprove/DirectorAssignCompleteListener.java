package com.syscxp.biz.service.activiti.businessApprove;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-13.
 * @Description: .
 */
@Component(value = "directorAssignCompleteListener")
public class DirectorAssignCompleteListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        String assignee = (String) delegateTask.getVariable("assignee");

        delegateTask.setAssignee(assignee);
        System.out.println("指派给：" + assignee);
    }
}
