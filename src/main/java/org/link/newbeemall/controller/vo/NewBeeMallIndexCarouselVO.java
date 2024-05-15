package org.link.newbeemall.controller.vo;

import java.io.Serializable;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NewBeeMallIndexCarouselVO
 * Author:  7ink
 * Data:    2024/5/15 13:20
 * Description:首页轮播图VO
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public class NewBeeMallIndexCarouselVO implements Serializable {

    private String carouselUrl;

    private String redirectUrl;


    public NewBeeMallIndexCarouselVO() {
    }

    public NewBeeMallIndexCarouselVO(String carouselUrl, String redirectUrl) {
        this.carouselUrl = carouselUrl;
        this.redirectUrl = redirectUrl;
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
        this.carouselUrl = carouselUrl;
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
        this.redirectUrl = redirectUrl;
    }

    public String toString() {
        return "NewBeeMallIndexCarouselVO{carouselUrl = " + carouselUrl + ", redirectUrl = " + redirectUrl + "}";
    }
}
