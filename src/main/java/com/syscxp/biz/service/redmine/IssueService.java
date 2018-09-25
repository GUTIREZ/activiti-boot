package com.syscxp.biz.service.redmine;

import com.syscxp.biz.core.db.DatabaseFacade;
import com.syscxp.biz.core.db.Q;
import com.syscxp.biz.entity.redmine.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */
@Service
public class IssueService {
    @Autowired
    private DatabaseFacade dbf;

    public List<Issue> getIssueBySubject() {
        List<Issue> issues = Q.New(Issue.class).list();

        return issues;
    }

    public Issue getIssueById(long id) {
        Issue issue = dbf.findById(id, Issue.class);
        return issue;
    }
}
