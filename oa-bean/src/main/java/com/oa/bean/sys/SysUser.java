package com.oa.bean.sys;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * <p/>
 * Created by [张渊]
 * 2017/8/10 12:05
 */
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;// 用户主键ID
    private String loginName;// 登录名
    private String nickname;// 用户昵称
    private String email;// Email地址
    private String password;// 用户密码
    private String phone;// 手机号
    private String departmentId;// 所属部门ID
    private int status;// 用户状态（0禁止登录，1正常，2锁定）
    private Date lockTime;// 锁定时间
    private Date createTime;// 创建时间
    private Date updateTime;// 更新时间
    private Date lastLoginTime;// 最后登录时间
    private String userType;// 用户类型ID
    private String description;// 用户描述
    private int defIdentify;// 默认标识（0可删1不可删除）

    private String deptName;// 部门名称

    public SysUser() {
    }

    public SysUser(SysUser user) {
        this.id = user.id;
        this.loginName = user.loginName;
        this.nickname = user.nickname;
        this.email = user.email;
        this.password = user.password;
        this.phone = user.phone;
        this.departmentId = user.departmentId;
        this.lockTime = user.lockTime;
        this.createTime = user.createTime;
        this.updateTime = user.updateTime;
        this.lastLoginTime = user.lastLoginTime;
        this.userType = user.userType;
        this.defIdentify = user.defIdentify;
        this.description = user.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getDefIdentify() {
        return defIdentify;
    }

    public void setDefIdentify(int defIdentify) {
        this.defIdentify = defIdentify;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
