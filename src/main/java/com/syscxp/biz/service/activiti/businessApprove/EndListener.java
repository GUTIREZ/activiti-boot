package com.syscxp.biz.service.activiti.businessApprove;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-12.
 * @Description: .
 */
@Component(value = "endListener")
public class EndListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution) {
        Map<String, Object> vars = execution.getVariables();

        System.out.println("this is a end listener...");
    }
}
