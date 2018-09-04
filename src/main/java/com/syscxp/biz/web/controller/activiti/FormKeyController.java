package com.syscxp.biz.web.controller.activiti;

import com.syscxp.biz.service.activiti.VacationRequest.VacationRequestService;
import com.syscxp.biz.service.activiti.form.FormKeyService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-08-13.
 * @Description: .
 */
@RestController
@RequestMapping("/formKey")
public class FormKeyController {

    @Autowired
    private FormKeyService service;

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public void start() {
        service.startProcess();
    }
}
