package org.link.newbeemall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NewBeeMallUserService
 * Author:  7ink
 * Data:    2024/5/15 19:15
 * Description:用户登录注册的业务层代码
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public interface NewBeeMallUserService {

    /**
     * 注册
     * @param loginName
     * @param password
     * @return
     */
    String register(String loginName, String password);


    /**
     * 登录
     * @param loginName
     * @param passwordMD5
     * @param httpSession
     * @return
     */
    String login(String loginName, String passwordMD5, HttpSession httpSession);
}
