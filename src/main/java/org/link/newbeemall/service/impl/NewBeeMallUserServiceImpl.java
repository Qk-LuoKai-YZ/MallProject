package org.link.newbeemall.service.impl;

import org.link.newbeemall.common.Constants;
import org.link.newbeemall.common.ServiceResultEnum;
import org.link.newbeemall.controller.vo.NewBeeMallUserVO;
import org.link.newbeemall.dao.MallUserMapper;
import org.link.newbeemall.entity.MallUser;
import org.link.newbeemall.service.NewBeeMallUserService;
import org.link.newbeemall.util.BeanUtil;
import org.link.newbeemall.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:NewBeeMallUserServiceImpl
 * Author:  7ink
 * Data:    2024/5/15 19:16
 * Description:用户登录注册接口的实现类
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */

@Service
public class NewBeeMallUserServiceImpl implements NewBeeMallUserService {

    @Autowired
    private MallUserMapper mallUserMapper;
    @Override
    public String register(String loginName, String password) {
        MallUser mallUser = mallUserMapper.selectByLoginName(loginName);
        if (mallUser != null){
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }

        MallUser mallUser1 = new MallUser();
        mallUser1.setLoginName(loginName);
        mallUser1.setNickName(loginName);
        mallUser1.setPasswordMd5(MD5Util.MD5Encode(password, "UTF-8"));

        if (mallUserMapper.insertSelective(mallUser1) > 0){
            return ServiceResultEnum.SUCCESS.getResult();
        }else {
            return ServiceResultEnum.DB_ERROR.getResult();
        }
    }

    @Override
    public String login(String loginName, String passwordMD5, HttpSession httpSession) {
        MallUser user = mallUserMapper.selectByLoginNameAndPasswd(loginName, passwordMD5);
        if (user != null && httpSession != null) {
            if (user.getLockedFlag() == 1) {
                return ServiceResultEnum.LOGIN_USER_LOCKED.getResult();
            }
            //昵称太长 影响页面展示
            if (user.getNickName() != null && user.getNickName().length() > 7) {
                String tempNickName = user.getNickName().substring(0, 7) + "..";
                user.setNickName(tempNickName);
            }

            NewBeeMallUserVO newBeeMallUserVO = new NewBeeMallUserVO();
            BeanUtil.copyProperties(user, newBeeMallUserVO);
            //设置购物车中的数量
            httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, newBeeMallUserVO);
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }
}
