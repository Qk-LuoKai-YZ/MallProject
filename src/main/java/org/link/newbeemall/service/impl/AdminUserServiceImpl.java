package org.link.newbeemall.service.impl;

import org.link.newbeemall.dao.AdminUserMapper;
import org.link.newbeemall.entity.AdminUser;
import org.link.newbeemall.service.AdminUserService;
import org.link.newbeemall.util.MD5Util;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:AdminUserServiceImpl
 * Author:  7ink
 * Data:    2024/5/13 15:05
 * Description:管理员登录业务层代码的实现类
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String userName, String password) {

        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");

        return adminUserMapper.login(userName, passwordMd5);
    }

    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        return adminUser;
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        if (adminUser != null) {
            String originalPasswordMd5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
            String newPasswordMd5 = MD5Util.MD5Encode(newPassword, "UTF-8");
            if (originalPasswordMd5.equals(adminUser.getLoginPassword())) {
                // 可以修改密码
                adminUser.setLoginPassword(newPasswordMd5);

                if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                    // 更新成功
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);

        if (adminUser != null) {
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);

            if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                // 更新成功
                return true;
            }
        }

        return false;
    }


}
