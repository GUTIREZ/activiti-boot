package com.syscxp.biz.web.controller.redmine;

import com.syscxp.biz.core.db.DatabaseFacade;
import com.syscxp.biz.entity.redmine.Issue;
import com.syscxp.biz.service.redmine.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */
@RestController
@RequestMapping("/issues")
public class IssueController {
    private static final Logger logger = LoggerFactory.getLogger(IssueController.class);
    private final IssueService service;
    private final DatabaseFacade dbf;

    public IssueController(IssueService service,DatabaseFacade dbf) {
        this.service = service;
        this.dbf = dbf;
    }

    @GetMapping
    public List<Issue> findBySubject() throws Exception {

        List<Issue> issues = service.getIssueBySubject();

        return issues;
    }


}
