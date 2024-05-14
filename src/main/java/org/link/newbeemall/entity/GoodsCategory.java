package org.link.newbeemall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:GoodsCategory
 * Author:  7ink
 * Data:    2024/5/14 13:55
 * Description:商品类目表实体
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public class GoodsCategory {

    // 分类id
    private Long categoryId;

    // 分类级别(1，2，3）
    private Byte categoryLevel;

    // 父分类id
    private Long parentId;

    // 分类名称
    private String categoryName;

    // 排序值
    private Integer categoryRank;

    // 删除字段标识
    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Integer createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private Integer updateUser;


    public GoodsCategory() {
    }

    public GoodsCategory(Long categoryId, Byte categoryLevel, Long parentId, String categoryName, Integer categoryRank, Byte isDeleted, Date createTime, Integer createUser, Date updateTime, Integer updateUser) {
        this.categoryId = categoryId;
        this.categoryLevel = categoryLevel;
        this.parentId = parentId;
        this.categoryName = categoryName;
        this.categoryRank = categoryRank;
        this.isDeleted = isDeleted;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
    }

    /**
     * 获取
     * @return categoryId
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 设置
     * @param categoryId
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取
     * @return categoryLevel
     */
    public Byte getCategoryLevel() {
        return categoryLevel;
    }

    /**
     * 设置
     * @param categoryLevel
     */
    public void setCategoryLevel(Byte categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    /**
     * 获取
     * @return parentId
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取
     * @return categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置
     * @param categoryName
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 获取
     * @return categoryRank
     */
    public Integer getCategoryRank() {
        return categoryRank;
    }

    /**
     * 设置
     * @param categoryRank
     */
    public void setCategoryRank(Integer categoryRank) {
        this.categoryRank = categoryRank;
    }

    /**
     * 获取
     * @return isDeleted
     */
    public Byte getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置
     * @param isDeleted
     */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取
     * @return createUser
     */
    public Integer getCreateUser() {
        return createUser;
    }

    /**
     * 设置
     * @param createUser
     */
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取
     * @return updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取
     * @return updateUser
     */
    public Integer getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置
     * @param updateUser
     */
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public String toString() {
        return "GoodsCategory{categoryId = " + categoryId + ", categoryLevel = " + categoryLevel + ", parentId = " + parentId + ", categoryName = " + categoryName + ", categoryRank = " + categoryRank + ", isDeleted = " + isDeleted + ", createTime = " + createTime + ", createUser = " + createUser + ", updateTime = " + updateTime + ", updateUser = " + updateUser + "}";
    }
}
