package org.link.newbeemall.controller.admin;

import org.link.newbeemall.common.ServiceResultEnum;
import org.link.newbeemall.entity.AdminUser;
import org.link.newbeemall.service.AdminUserService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:AdminController
 * Author:  7ink
 * Data:    2024/5/13 12:48
 * Description:首页控制器
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
@Controller
@RequestMapping("/admin")
public class AdminController {


    @Resource
    private AdminUserService adminUserService;

    @GetMapping("/login")
    public  String login(){
        return "admin/login";
    }

    @GetMapping("/logout")
    public  String logout(HttpServletRequest request){
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return "admin/login";
    }

    @GetMapping({"/test"})
    public String test() {
        return "admin/test";
    }
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session){

        if (StringUtils.isEmpty(verifyCode)){
            session.setAttribute("errorMsg", "验证码不能为空");
            return "admin/login";
        }

        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "admin/login";
        }

        String kaptchaCode = session.getAttribute("verifyCode") + "";

        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)){
            session.setAttribute("errorMsg", "验证码错误");
            return "admin/login";
        }

        AdminUser adminUser = adminUserService.login(userName, password);

        if (null != adminUser){
            session.setAttribute("loginUser",adminUser.getNickName());
            session.setAttribute("loginUserId",adminUser.getAdminUserId());

            // 设置session过期时间
            //session.setMaxInactiveInterval(60 * 60 * 2);
            return "redirect:/admin/index";
        }else {
            return "admin/login";
        }

    }
    @GetMapping({"","/","index","index.html"})
    public String index(HttpServletRequest request){

        request.setAttribute("path", "index");
        return "admin/index";
    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest request){

        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");

        AdminUser adminUser = adminUserService.getUserDetailById(loginUserId);
        if (adminUser == null){
            return "admin/login";
        }

        request.setAttribute("path", "profile");
        request.setAttribute("loginUserName", adminUser.getLoginUserName());
        request.setAttribute("nickName", adminUser.getNickName());
        return "admin/profile";
    }

    @PostMapping("/profile/name")
    @ResponseBody
    public String updateName(@RequestParam("loginUserName") String loginUserName,
                             @RequestParam("nickName") String nickName,
                             HttpServletRequest request){
        if (StringUtils.isEmpty(loginUserName) || StringUtils.isEmpty(nickName)){
            return "参数不能是空";
        }

        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");

        AdminUser adminUser = adminUserService.getUserDetailById(loginUserId);
        if (adminUser == null){
            return "admin/login";
        }

        if ( adminUserService.updateName(loginUserId, loginUserName, nickName)){
            return ServiceResultEnum.SUCCESS.getResult();
        }else {
            return "修改失败";
        }
    }

    @PostMapping("/profile/password")
    @ResponseBody
    public String updatePassword(@RequestParam("originalPassword") String originalPassword,
                             @RequestParam("newPassword") String newPassword,
                             HttpServletRequest request){
        if (StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)){
            return "参数不能是空";
        }

        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");

        AdminUser adminUser = adminUserService.getUserDetailById(loginUserId);
        if (adminUser == null){
            return "admin/login";
        }

        if ( adminUserService.updatePassword(loginUserId, originalPassword, newPassword)){
            //修改成功后清空session中的数据，前端控制跳转至登录页
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
            return ServiceResultEnum.SUCCESS.getResult();
        }else {
            return "修改失败";
        }
    }
}
