package com.syscxp.biz.core.db.query.header;

import com.syscxp.biz.core.api.validator.EnumValue;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class QueryMessage {
    private List<QueryCondition> conditions;
    @Range(min = 0, max = 500)
    private Integer limit = 200;
    @NotNull(message = "start不能为空")
    private Integer start;
    private boolean count;
    private String groupBy;
    private boolean replyWithCount;
    private String sortBy;
    @EnumValue(enumClass = SortDirection.class, enumMethod = "isValid")
    private String sortDirection = "asc";
    private List<String> fields;

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public List<QueryCondition> getConditions() {
        if (conditions == null) {
            conditions = new ArrayList<>();
        }
        return conditions;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public boolean isReplyWithCount() {
        return replyWithCount;
    }

    public void setReplyWithCount(boolean replyWithCount) {
        this.replyWithCount = replyWithCount;
    }

    public void setConditions(List<QueryCondition> conditions) {
        this.conditions = conditions;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public void addQueryCondition(String name, String op, String... vals) {
        QueryCondition qc = new QueryCondition();
        qc.setName(name);
        qc.setOp(op);
        if (vals.length == 1) {
            qc.setValue(vals[0]);
        } else {
            qc.setValues(vals);
        }
        getConditions().add(qc);
    }

    public void addQueryCondition(String name, QueryOp op, String... vals) {
        addQueryCondition(name, op.toString(), vals);
    }

    public void addField(String name) {
        if (fields == null) {
            fields = new ArrayList<String>();
        }
        fields.add(name);
    }

    public boolean isFieldQuery() {
        return fields != null && !fields.isEmpty();
    }

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    public enum SortDirection {
        /**
         * 正序
         */
        asc,
        /**
         * 倒序
         */
        desc;

        /**
         * 判断参数合法性
         */
        public static boolean isValid(String name) {
            for (SortDirection sortDirection : SortDirection.values()) {
                if (sortDirection.name().equals(name)) {
                    return true;
                }
            }
            return false;
        }
    }
}
