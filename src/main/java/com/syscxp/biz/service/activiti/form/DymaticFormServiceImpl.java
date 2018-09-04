package com.syscxp.biz.service.activiti.form;

import com.syscxp.biz.repository.PersonRepository;
import com.syscxp.biz.service.activiti.VacationRequest.VacationRequestServiceImpl;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-08-28.
 * @Description: .
 */
@Service
public class DymaticFormServiceImpl implements DymaticFormService {
    private static final Logger logger = LoggerFactory.getLogger(VacationRequestServiceImpl.class);
    private static final String processKey = "DymaticForm";

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public ProcessInstance startProcess() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("name", "Kermit");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, variables);
        logger.info("ID: " + processInstance.getId());
        logger.info("ProcessInstanceId: " + processInstance.getProcessInstanceId());

        variables = runtimeService.getVariables(processInstance.getId());
        Set<Map.Entry<String, Object>> entrySet = variables.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            logger.info(entry.getKey() + "=" + entry.getValue());
        }
        return processInstance;
    }
}
