package com.syscxp.biz.service.activiti.VacationRequest;

import com.alibaba.fastjson.JSON;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-08-13.
 * @Description: .
 */

@Service
public class VacationRequestServiceImpl implements VacationRequestService {

    private static final Logger logger = LoggerFactory.getLogger(VacationRequestServiceImpl.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Override
    public void startProcess() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employeeName", "Kermit");
        variables.put("numberOfDays", new Integer(4));
        variables.put("vacationMotivation", "I'm really tired!");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);
        ProcessInstance s1 = runtimeService.startProcessInstanceByKey("a","b");

        logger.info(JSON.toJSONString(processInstance));
        logger.info("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());
    }

    @Override
    public List<Task> getTasks() {
        return taskService.createTaskQuery()
                .processDefinitionKey("vacationRequest")
                .list();
    }

    @Override
    public void completeTask(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("vacationApproved", "false");
        taskVariables.put("managerMotivation", "We have a tight deadline!");
        taskService.complete(task.getId(), taskVariables);


    }
}