package com.syscxp.biz.service.activiti.businessApprove;

import com.syscxp.biz.service.activiti.VacationRequest.VacationRequestService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-12.
 * @Description: .
 */
@Component(value = "startListener")
public class StartListener implements ExecutionListener {
    @Autowired
    private IdentityService identityService;

    @Override
    public void notify(DelegateExecution execution) {
        System.out.println("this is a start listener...");

        identityService.setAuthenticatedUserId("userUuid");
    }
}
