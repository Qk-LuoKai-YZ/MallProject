package org.link.newbeemall.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:PageQueryUtil
 * Author:  7ink
 * Data:    2024/5/14 8:28
 * Description:分页查询的通用入参
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public class PageQueryUtil extends LinkedHashMap<String,Object> {

    // 页码
    private int page;

    // 每页几条
    private int limit;

    public PageQueryUtil(Map<String,Object> params) {
        this.putAll(params);

        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());

        // start用于sql语句
        this.put("start", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
