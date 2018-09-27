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
                        startProcess(issue,journal);
                    }
                }
            }
        }
        return "ok";
    }

    private void startProcess(Issue issue,Journal journal) {
        Map<String, Object> v = new HashMap<>();
        v.put("subject",issue.getSubject());
        v.put("description",issue.getDescription());

        List<CustomValue> customValues = Q.New(CustomValue.class)
                .eq(CustomValue_.customizedId,journal.getJournalizedId())
                .eq(CustomValue_.customizedType,"Issue")
                .list();
        for(CustomValue cv : customValues){
            switch(Integer.valueOf(cv.getCustomField().getId().toString())){
                case 1:
                    v.put("pointAAddress",cv.getValue());
                    break;
                case 2:
                    v.put("pointZAddress",cv.getValue());
                    break;
                case 3:
                    v.put("customerName",cv.getValue());
                    break;
                case 4:
                    v.put("monthlyPrice",cv.getValue());
                    break;
                case 5:
                    v.put("tunnelType",cv.getValue());
                    break;
                case 6:
                    v.put("bandwidth",cv.getValue());
                    break;
                case 7:
                    v.put("pointAType",cv.getValue());
                    break;
                case 8:
                    v.put("pointZType",cv.getValue());
                    break;
                case 9:
                    v.put("contactName",cv.getValue());
                    break;
                default:
                    break;
            }
        }


        runtimeService.startProcessInstanceById("PreSaleSupport:3:16616", v);

   }
}
