package com.syscxp.biz.core.db.query.header;

import java.util.Collection;

public class QueryReply<T extends Collection> {
    private Long total;

    private T inventories;

    public Long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public T getInventories() {
        return inventories;
    }

    public void setInventories(T inventories) {
        this.inventories = inventories;
    }
}
