package org.link.newbeemall.common;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NewBeeMallException
 * Author:  7ink
 * Data:    2024/5/14 13:34
 * Description:自定义异常信息
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public class NewBeeMallException extends RuntimeException{


    public NewBeeMallException() {
    }

    public NewBeeMallException(String message) {
        super(message);
    }

    /**
     * 抛出一个异常
     * @param message
     */
    public static void fail(String message){
        throw new NewBeeMallException(message);
    }
}
