package org.link.newbeemall.controller.mall;

import cn.hutool.captcha.ShearCaptcha;
import org.link.newbeemall.common.Constants;
import org.link.newbeemall.common.ServiceResultEnum;
import org.link.newbeemall.controller.vo.NewBeeMallUserVO;
import org.link.newbeemall.entity.MallUser;
import org.link.newbeemall.service.NewBeeMallUserService;
import org.link.newbeemall.util.MD5Util;
import org.link.newbeemall.util.Result;
import org.link.newbeemall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:PersonalController
 * Author:  7ink
 * Data:    2024/5/15 18:51
 * Description:商城端登录和注册功能
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */

@Controller
public class PersonalController {

    @Resource
    private NewBeeMallUserService newBeeMallUserService;


    // 订单支付的时候需要用到--收货地址等个人信息
    /**
     * 个人信息
     * @param request
     * @param httpSession
     * @return
     */
    @GetMapping("/personal")
    public String personalPage(HttpServletRequest request,
                               HttpSession httpSession) {
        request.setAttribute("path", "personal");
        return "mall/personal";
    }


    @GetMapping("/personal/addresses")
    public String addressesPage() {
        return "mall/addresses";
    }

    /***
     * 修改个人信息
     * @param mallUser
     * @param httpSession
     * @return
     */
    @PostMapping("/personal/updateInfo")
    @ResponseBody
    public Result updateInfo(@RequestBody MallUser mallUser, HttpSession httpSession) {
        NewBeeMallUserVO mallUserTemp = newBeeMallUserService.updateUserInfo(mallUser, httpSession);
        if (mallUserTemp == null) {
            Result result = ResultGenerator.genFailResult("修改失败");
            return result;
        } else {
            //返回成功
            Result result = ResultGenerator.genSuccessResult();
            return result;
        }
    }


    // ——————————————————————————————————————————————————————————————————————————————
    /**
     * 登陆页面跳转
     * @return
     */
    @GetMapping({"/login","login.html"})
    public String loginPage(){
        return "mall/login";
    }

    /**
     * 注册页面跳转
     * @return
     */
    @GetMapping({"register","register.html"})
    public String registerPage(){

        return "mall/register";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestParam("loginName") String loginName,
                        @RequestParam("verifyCode") String verifyCode,
                        @RequestParam("password") String password,
                        HttpSession httpSession) {
        if (!StringUtils.hasText(loginName)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_NULL.getResult());
        }
        if (!StringUtils.hasText(password)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_PASSWORD_NULL.getResult());
        }
        if (!StringUtils.hasText(verifyCode)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_NULL.getResult());
        }
        ShearCaptcha shearCaptcha = (ShearCaptcha) httpSession.getAttribute(Constants.MALL_VERIFY_CODE_KEY);

        if (shearCaptcha == null || !shearCaptcha.verify(verifyCode)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_ERROR.getResult());
        }
        String loginResult = newBeeMallUserService.login(loginName, MD5Util.MD5Encode(password, "UTF-8"), httpSession);
        //登录成功
        if (ServiceResultEnum.SUCCESS.getResult().equals(loginResult)) {
            //删除session中的verifyCode
            httpSession.removeAttribute(Constants.MALL_VERIFY_CODE_KEY);
            return ResultGenerator.genSuccessResult();
        }
        //登录失败
        return ResultGenerator.genFailResult(loginResult);
    }

    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestParam("loginName") String loginName,
                           @RequestParam("verifyCode") String verifyCode,
                           @RequestParam("password") String password,
                           HttpSession httpSession) {
        if (!StringUtils.hasText(loginName)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_NULL.getResult());
        }
        if (!StringUtils.hasText(password)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_PASSWORD_NULL.getResult());
        }
        if (!StringUtils.hasText(verifyCode)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_NULL.getResult());
        }
        ShearCaptcha shearCaptcha = (ShearCaptcha) httpSession.getAttribute(Constants.MALL_VERIFY_CODE_KEY);
        if (shearCaptcha == null || !shearCaptcha.verify(verifyCode)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_ERROR.getResult());
        }
        String registerResult = newBeeMallUserService.register(loginName, password);
        //注册成功
        if (ServiceResultEnum.SUCCESS.getResult().equals(registerResult)) {
            //删除session中的verifyCode
            httpSession.removeAttribute(Constants.MALL_VERIFY_CODE_KEY);
            return ResultGenerator.genSuccessResult();
        }
        //注册失败
        return ResultGenerator.genFailResult(registerResult);
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute(Constants.MALL_USER_SESSION_KEY);
        return "mall/login";
    }
}
