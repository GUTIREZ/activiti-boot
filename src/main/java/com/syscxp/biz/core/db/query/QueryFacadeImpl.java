package com.syscxp.biz.core.db.query;

//import com.syscxp.biz.core.api.APINoSee;
//import com.syscxp.biz.core.query.header.*;
//import com.syscxp.biz.core.search.header.Inventory;
//import com.syscxp.biz.core.utils.TypeUtils;
//import com.syscxp.biz.core.utils.zstack.BeanUtils;
//import com.syscxp.biz.core.utils.zstack.db.FieldUtils;
import com.syscxp.biz.core.api.APINoSee;
import com.syscxp.biz.core.db.query.header.*;
import com.syscxp.biz.core.db.search.header.Inventory;
import com.syscxp.biz.core.utils.TypeUtils;
import com.syscxp.biz.core.utils.zstack.BeanUtils;
import com.syscxp.biz.core.utils.zstack.db.FieldUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-06-20.
 * @Description: .
 */
@Component
public class QueryFacadeImpl implements QueryFacade {
    private static final Logger logger = LoggerFactory.getLogger(QueryFacadeImpl.class);
    private Map<String, QueryBuilderFactory> builerFactories = new HashMap<>();
    private String queryBuilderType = MysqlQueryBuilderFactory.type.toString();

    @Autowired
    private QueryBuilder queryBuilder;

    private void validateConditions(List<QueryCondition> conditions) {
        for (QueryCondition cond : conditions) {
            // will throw out IllegalArgumentException if op is invalid
            QueryOp.valueOf(cond.getOp());
        }
    }

    @Override
    public <T> List<T> query(QueryMessage msg, Class<T> inventoryClass) {
        validateConditions(msg.getConditions());
        return queryBuilder.query(msg, inventoryClass);
    }

    @Override
    public long count(QueryMessage msg, Class inventoryClass) {
        validateConditions(msg.getConditions());
        return queryBuilder.count(msg, inventoryClass);
    }


    private QueryBuilderFactory getFactory(String type) {
        QueryBuilderFactory factory = builerFactories.get(type);
        if (factory == null) {
            throw new RuntimeException(String.format("unable to find QueryBuilderFactory with type[%s]", type));
        }
        return factory;
    }

    private void checkBoxTypeInInventory() {
        List<Class> inventoryClasses = BeanUtils.scanClass("com.syscxp.biz", Inventory.class);
        List<String> errors = new ArrayList<>();
        for (Class clz : inventoryClasses) {
            boolean error = false;
            StringBuilder sb = new StringBuilder(String.format("inventory class[%s] contains below primitive fields:",
                    clz.getName()));
            for (Field f : FieldUtils.getAllFields(clz)) {
                if (f.isAnnotationPresent(APINoSee.class)) {
                    continue;
                }

                if (TypeUtils.isPrimitiveType(f.getType())) {
                    error = true;
                    sb.append(String.format("\n%s[%s]", f.getName(), f.getType().getName()));
                }
            }

            if (error) {
                errors.add(sb.toString());
            }
        }

        if (!errors.isEmpty()) {
            throw new RuntimeException(String.format("detected some inventory class using primitive type." +
                            " Please change those primitive type field to corresponding box type:\n %s",
                    StringUtils.join(errors, "\n\n")));
        }
    }

    @PostConstruct
    public boolean start() {
        checkBoxTypeInInventory();
        return true;
    }

    @PreDestroy
    public boolean stop() {
        return true;
    }

    public void setQueryBuilderType(String queryBuilderType) {
        this.queryBuilderType = queryBuilderType;
    }

    private void handle(APIGenerateQueryableFieldsMsg msg) {
        QueryBuilderFactory factory = getFactory(queryBuilderType);
        QueryBuilder builder = factory.createQueryBuilder();
        Map<String, List<String>> ret = builder.populateQueryableFields();

        if (APIGenerateQueryableFieldsMsg.PYTHON_FORMAT.equals(msg.getFormat())) {
            QueryableFieldsPythonWriter writer = new QueryableFieldsPythonWriter(msg.getOutputFolder(), ret);
            writer.write();
        } else {
            throw new RuntimeException(String.format("unknown mediaType[%s]", msg.getFormat()));
        }

        // APIGenerateQueryableFieldsEvent evt = new APIGenerateQueryableFieldsEvent(msg.getId());
        // bus.publish(evt);
    }


    private Map<Class, Method> replySetter = new HashMap<>();
    private Map<Class, AutoQuery> autoQueryMap = new HashMap<>();

    private void handle(QueryMessage msg) {
        AutoQuery at = autoQueryMap.get(msg.getClass());
        if (at == null) {
            at = msg.getClass().getAnnotation(AutoQuery.class);
            if (at == null) {
                throw new RuntimeException(
                        String.format("message[%s] is not annotated by @AutoQuery", msg.getClass())
                );
            }
            autoQueryMap.put(msg.getClass(), at);
        }

        Class replyClass = at.replyClass();
        Class inventoryClass = at.inventoryClass();
        try {
            QueryReply reply = (QueryReply) replyClass.newInstance();
            Method setter = replySetter.get(inventoryClass);
            if (setter == null) {
                setter = replyClass.getDeclaredMethod("setInventories", List.class);
                if (setter == null) {
                    throw new RuntimeException(
                            String.format("query reply[%s] has no method setInventories()", replyClass.getName())
                    );
                }
                setter.setAccessible(true);
                replySetter.put(inventoryClass, setter);
            }

            if (msg.isCount()) {
                long count = count(msg, inventoryClass);
                reply.setTotal(count);
                //bus.reply(msg, reply);
            } else {
                List invs = query(msg, inventoryClass);
                setter.invoke(reply, invs);
                //TODO: merge this into mysql query builder
                if (msg.isReplyWithCount()) {
                    long count = count(msg, inventoryClass);
                    reply.setTotal(count);
                }
                //bus.reply(msg, reply);
            }
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public QueryReply handle(QueryMessage msg, Class inventoryClass) {
        QueryReply reply = new QueryReply();
        try {

            if (msg.isCount()) {
                long count = count(msg, inventoryClass);
                reply.setTotal(count);
            } else {
                List invs = query(msg, inventoryClass);
                reply.setInventories(invs);
                if (msg.isReplyWithCount()) {
                    long count = count(msg, inventoryClass);
                    reply.setTotal(count);
                }
            }
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            throw new RuntimeException(e);
        }

        return reply;
    }
}
