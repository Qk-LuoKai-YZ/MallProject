package org.link.newbeemall.util;

import org.springframework.util.StringUtils;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:ResultGenerator
 * Author:  7ink
 * Data:    2024/5/14 10:01
 * Description:响应结果生成工具
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public class ResultGenerator {

    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    private static final String DEFAULT_FAIL_MESSAGE = "FAIL";

    private static final int RESULT_CODE_SUCCESS = 200;

    private static final int RESULT_CODE_SERVER_ERROR = 500;

    public static Result genSuccessResult(){
        Result result = new Result();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);

        return result;
    }

    public static Result genSuccessResult(String message){
        Result result = new Result();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(message);

        return result;
    }

    public static Result genSuccessResult(Object data){
        Result result = new Result();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        result.setData(data);
        return result;
    }


    public static Result genFailResult(String message){
        Result result = new Result();

        result.setResultCode(RESULT_CODE_SERVER_ERROR);

        if (!StringUtils.hasText(message)){
            result.setMessage(DEFAULT_FAIL_MESSAGE);
        }else {
            result.setMessage(message);
        }
        return result;
    }
    public static Result genErrorResult(int code,String message){
        Result result = new Result();
        result.setResultCode(code);
        result.setMessage(message);

        return result;
    }
}
