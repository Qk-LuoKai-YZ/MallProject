package org.link.newbeemall.util;

import java.util.List;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:PageResult
 * Author:  7ink
 * Data:    2024/5/14 8:56
 * Description:分页的返回结果
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public class PageResult {

    // 总记录数
    private int totalCount;

    // 每页记录数
    private int pageSize;

    // 总页数
    private int totalPage;

    // 当前页数
    private int currPage;

    // 列表数据
    private List<?> list;

    public PageResult() {
    }

    public PageResult(List<?> list,int totalCount, int pageSize, int currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);

    }

    /**
     * 获取
     * @return totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 设置
     * @param totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 获取
     * @return pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取
     * @return totalPage
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * 设置
     * @param totalPage
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 获取
     * @return currPage
     */
    public int getCurrPage() {
        return currPage;
    }

    /**
     * 设置
     * @param currPage
     */
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    /**
     * 获取
     * @return list
     */
    public List<?> getList() {
        return list;
    }

    /**
     * 设置
     * @param list
     */
    public void setList(List<?> list) {
        this.list = list;
    }

    public String toString() {
        return "PageResult{totalCount = " + totalCount + ", pageSize = " + pageSize + ", totalPage = " + totalPage + ", currPage = " + currPage + ", list = " + list + "}";
    }
}
