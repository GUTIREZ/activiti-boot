package com.syscxp.biz.web.controller.activiti;

import com.syscxp.biz.service.activiti.form.DymaticFormService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-08-13.
 * @Description: .
 */
@RestController
@RequestMapping("/dymaticForm")
public class DymaticFormController {

    @Autowired
    private DymaticFormService service;

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public String start() {
        ProcessInstance pst = service.startProcess();
        return pst.getProcessInstanceId();
    }
}
