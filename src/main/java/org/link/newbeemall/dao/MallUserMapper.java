package org.link.newbeemall.dao;

import org.apache.ibatis.annotations.Param;
import org.link.newbeemall.entity.MallUser;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:MallUserMapper
 * Author:  7ink
 * Data:    2024/5/15 19:04
 * Description:用户实体的mapper
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public interface MallUserMapper {

    /**
     * 保存一条新纪录
     * @param record
     * @return
     */
    int insertSelective(MallUser record);

    /**
     * 根据loginName查询记录
     * @param loginName
     * @return
     */
    MallUser selectByLoginName(String loginName);

    /**
     * 根据loginName和密码字段查询
     * @param loginName
     * @param password
     * @return
     */
    MallUser selectByLoginNameAndPasswd(@Param("loginName") String loginName, @Param("password") String password);

    /**
     * 根据用户id查询信息
     * @param userId
     * @return
     */
    MallUser selectByPrimaryKey(Long userId);

    /**
     * 更新用户信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MallUser record);
}
