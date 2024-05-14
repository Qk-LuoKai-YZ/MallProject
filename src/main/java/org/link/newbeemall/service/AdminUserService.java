package org.link.newbeemall.service;

import org.link.newbeemall.entity.AdminUser;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:AdminUserService
 * Author:  7ink
 * Data:    2024/5/13 15:04
 * Description:管理员登录业务层代码
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public interface AdminUserService {

    AdminUser login(String userName, String password);

    /**
     * 获取用户信息
     * @param loginUserId
     * @return
     */
    AdminUser getUserDetailById(Integer loginUserId);

    /**
     * 修改当前登录用户密码
     * @param loginUserId
     * @param originalPassword
     * @param newPassword
     * @return
     */
    Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);


    /**
     * 修改当前登录用户的信息
     * @param loginUserId
     * @param loginUserName
     * @param nickName
     * @return
     */
    Boolean updateName(Integer loginUserId, String loginUserName, String nickName);
}
