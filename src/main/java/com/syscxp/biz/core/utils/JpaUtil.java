package com.syscxp.biz.core.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-04-27.
 * @Description: .
 */
public interface JpaUtil {
    /***
     * 字符串转sort对象
     * @param sortStr
     * @return：Sort
     */
    static Sort string2Sort(String sortStr) {
        String[] sorts = StringUtil.split(sortStr, ",");

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        for (String sort : sorts) {
            if (StringUtil.startsWith(sort, "-")) {
                orders.add(new Sort.Order(Sort.Direction.DESC, StringUtil.substring(sort, 1)));
            } else {
                orders.add(new Sort.Order(Sort.Direction.ASC, sort));
            }
        }
        return new Sort(orders);
    }

    /**
     * 从请求头获取分页信息
     *
     * @param request
     * @return
     */
    static Pageable request2Pageable(HttpServletRequest request) {
        Pageable pageable;

        try {
            int page = StringUtil.isEmpty(request.getHeader("page")) ? 1 : Integer.parseInt(request.getHeader("page"));
            int size = StringUtil.isEmpty(request.getHeader("size")) ? 15 : Integer.parseInt(request.getHeader("size"));
            String sortStr = StringUtil.isEmpty(request.getHeader("sort")) ? "-createdAt" : request.getHeader("sort");
            Sort sort = JpaUtil.string2Sort(sortStr);
            pageable = new PageRequest(page, size, sort);
        } catch (Exception e) {
            throw new IllegalArgumentException("Illegal pageable condition！");
        }

        return pageable;
    }
}
