package org.link.newbeemall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:Carousel
 * Author:  7ink
 * Data:    2024/5/14 8:15
 * Description:轮播图实体
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public class Carousel {

    private Integer carouselId;

    private String carouselUrl;

    private String redirectUrl;

    private Byte carouselRank;

    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    private Date updateTime;

    private  Integer createUser;;

    private  Integer updateUser;


    public Carousel() {
    }


    public Carousel(Integer carouselId, String carouselUrl, String redirectUrl, Byte carouselRank, Byte isDeleted, Date createTime, Date updateTime, Integer createUser, Integer updateUser) {
        this.carouselId = carouselId;
        this.carouselUrl = carouselUrl;
        this.redirectUrl = redirectUrl;
        this.carouselRank = carouselRank;
        this.isDeleted = isDeleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    /**
     * 获取
     * @return carouselId
     */
    public Integer getCarouselId() {
        return carouselId;
    }

    /**
     * 设置
     * @param carouselId
     */
    public void setCarouselId(Integer carouselId) {
        this.carouselId = carouselId;
    }

    /**
     * 获取
     * @return carouselUrl
     */
    public String getCarouselUrl() {
        return carouselUrl;
    }

    /**
     * 设置
     * @param carouselUrl
     */
    public void setCarouselUrl(String carouselUrl) {
        this.carouselUrl = carouselUrl == null ? null : carouselUrl.trim();
    }

    /**
     * 获取
     * @return redirectUrl
     */
    public String getRedirectUrl() {
        return redirectUrl;
    }

    /**
     * 设置
     * @param redirectUrl
     */
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl == null ? null : redirectUrl.trim();
    }

    /**
     * 获取
     * @return carouselRank
     */
    public Byte getCarouselRank() {
        return carouselRank;
    }

    /**
     * 设置
     * @param carouselRank
     */
    public void setCarouselRank(Byte carouselRank) {
        this.carouselRank = carouselRank;
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
        return "Carousel{carouselId = " + carouselId + ", carouselUrl = " + carouselUrl + ", redirectUrl = " + redirectUrl + ", carouselRank = " + carouselRank + ", isDeleted = " + isDeleted + ", createTime = " + createTime + ", updateTime = " + updateTime + ", updateUser = " + updateUser + "}";
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
}
