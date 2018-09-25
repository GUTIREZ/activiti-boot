package com.syscxp.biz.web;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-08-10.
 * @Description: .
 */
@Controller
@RequestMapping("/issues")
public class Index {

    @RequestMapping("/index")
    public String index() {

        return "hello.html";
    }

}
