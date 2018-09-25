package com.syscxp.biz.core.utils.zstack.data;

import java.util.List;

public interface ArraySpliter {
	<T> List<T[]> split(T[] source, int lengthOfSubArray);
}
