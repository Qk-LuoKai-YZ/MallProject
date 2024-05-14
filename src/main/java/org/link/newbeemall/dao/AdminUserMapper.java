package org.link.newbeemall.dao;

import org.apache.ibatis.annotations.Param;
import org.link.newbeemall.entity.AdminUser;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:AdminUserMapper
 * Author:  7ink
 * Data:    2024/5/13 13:30
 * Description:管理员的mapper接口
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public interface AdminUserMapper {

    /**
     * 登录方法
     * @param userName
     * @param password
     * @return
     */
    AdminUser login(@Param("userName") String userName, @Param("password") String password);

    /**
     * 根据id查询
     * @param loginUserId
     * @return
     */
    AdminUser selectByPrimaryKey(Integer loginUserId);


    /**
     * 更新用户信息
     * @param adminUser
     * @return
     */
    int updateByPrimaryKeySelective(AdminUser adminUser);

    /**
     * 更新用户信息
     * @param adminUser 没啥区别
     * @return
     */
    int updateByPrimaryKey(AdminUser adminUser);
}
