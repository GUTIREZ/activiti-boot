package com.syscxp.biz.web.controller.redmine;

import com.syscxp.biz.core.db.DatabaseFacade;
import com.syscxp.biz.core.db.Q;
import com.syscxp.biz.core.db.SimpleQuery;
import com.syscxp.biz.entity.redmine.*;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-21.
 * @Description: .
 */
@RestController
@RequestMapping("/dm")
public class DataMigration {
    private static final Logger logger = LoggerFactory.getLogger(DataMigration.class);

    @Autowired
    private DatabaseFacade dbf;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    protected TaskService taskService;

    @PostMapping("/singleStart")
    public String startSingleProcess(@RequestBody Map map) throws Exception {

        Issue issue = Q.New(Issue.class).eq(Issue_.id, 1388).find();
        if (issue != null) {
            logger.info(issue.getSubject());
            List<Journal> journals = Q.New(Journal.class)
                    .eq(Journal_.journalizedType, "Issue")
                    .eq(Journal_.journalizedId, issue.getId())
                    .orderBy(Journal_.createdOn, SimpleQuery.Od.ASC)
                    .list();

            for (Journal journal : journals) {
                logger.info("journal id:" + journal.getId().toString() + " -- " + journal.getNotes());
                for (JournalDetail d : journal.getJournalDetails()) {
                    if ("status_id".equals(d.getPropKey()) && "1".equals(d.getOldValue())) {
                        startProcess(journal);
                    }
                }
            }
        }
        return "ok";
    }

    private void startProcess(Journal journal) {

        List<CustomValue> customValues = Q.New(CustomValue.class)
                .eq(CustomValue_.customizedId,journal.getJournalizedId())
                .eq(CustomValue_.customizedType,"Issue")
                .list();
        logger.info(String.valueOf(customValues.size()));

        for(CustomValue cv : customValues){
            CustomField cf = dbf.findById(cv.getCustomFieldId(),CustomField.class);

            logger.info(cf.getName());
        }
        Map<String, Object> variables = new HashMap<>();

//        runtimeService.startProcessInstanceById("PreSaleSupport:3:16616", variables);

    }
}
