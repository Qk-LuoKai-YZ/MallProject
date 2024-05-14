package org.link.newbeemall.entity;

/**
 * Copyright(C),2024-2024,待就业
 * FileName:AdminUser
 * Author:  7ink
 * Data:    2024/5/13 13:25
 * Description:管理员实体类
 * History:
 * <author>  <time>  <version>   <desc>
 * 作者     修改时间     版本号     描述
 */
public class AdminUser {

    private Integer adminUserId;

    private String loginUserName;

    private String loginPassword;

    private String nickName;

    private Byte locked;


    public AdminUser() {
    }

    public AdminUser(Integer adminUserId, String loginUserName, String loginPassword, String nickName, Byte locked) {
        this.adminUserId = adminUserId;
        this.loginUserName = loginUserName;
        this.loginPassword = loginPassword;
        this.nickName = nickName;
        this.locked = locked;
    }

    /**
     * 获取
     * @return adminUserId
     */
    public Integer getAdminUserId() {
        return adminUserId;
    }

    /**
     * 设置
     * @param adminUserId
     */
    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    /**
     * 获取
     * @return loginUserName
     */
    public String getLoginUserName() {
        return loginUserName;
    }

    /**
     * 设置
     * @param loginUserName
     */
    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName == null ? null : loginUserName.trim();
    }

    /**
     * 获取
     * @return loginPassword
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * 设置
     * @param loginPassword
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    /**
     * 获取
     * @return nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 获取
     * @return locked
     */
    public Byte getLocked() {
        return locked;
    }

    /**
     * 设置
     * @param locked
     */
    public void setLocked(Byte locked) {
        this.locked = locked;
    }

    public String toString() {
        return "AdminUser{adminUserId = " + adminUserId + ", loginUserName = " + loginUserName + ", loginPassword = " + loginPassword + ", nickName = " + nickName + ", locked = " + locked + "}";
    }
}
