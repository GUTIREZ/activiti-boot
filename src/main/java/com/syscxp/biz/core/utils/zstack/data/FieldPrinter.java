package com.syscxp.biz.core.utils.zstack.data;

public interface FieldPrinter {
    String print(Object obj);
	
	String print(Object obj, boolean recursive);
}
