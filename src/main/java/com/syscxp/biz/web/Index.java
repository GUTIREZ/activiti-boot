package com.syscxp.biz.web;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-08-10.
 * @Description: .
 */
@Controller
public class Index {


    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessEngine processEngine;

    @GetMapping("/welcome")
    @ResponseBody
    public String welcome() {

        return "调用流程存储服务，查询部署数量："
                + repositoryService.createDeploymentQuery().count();
    }
}
