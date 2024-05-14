package org.link.newbeemall.util;

import java.io.Serializable;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:Result
 * Author:  7ink
 * Data:    2024/5/14 9:57
 * Description:统一的结果返回类
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int resultCode;

    private String message;

    private T data;


    public Result() {
    }

    public Result(int resultCode, String message, T data) {
        this.resultCode = resultCode;
        this.message = message;
    }

    /**
     * 获取
     * @return resultCode
     */
    public int getResultCode() {
        return resultCode;
    }

    /**
     * 设置
     * @param resultCode
     */
    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * 获取
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取
     * @return data
     */
    public T getData() {
        return data;
    }

    /**
     * 设置
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return "Result{resultCode = " + resultCode + ", message = " + message + ", data = " + data + "}";
    }
}
