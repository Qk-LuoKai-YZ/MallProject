package org.link.newbeemall.common;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:ServiceResultEnum
 * Author:  7ink
 * Data:    2024/5/13 18:58
 * Description:服务端返回的错误数据
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public enum ServiceResultEnum {

    ERROR("error"),

    SUCCESS("success"),

    DB_ERROR("database error"),

    DATA_NOT_EXIST("未查询到该记录");
    private String result;

    ServiceResultEnum(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
