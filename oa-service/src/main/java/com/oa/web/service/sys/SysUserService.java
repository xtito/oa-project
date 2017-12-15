package com.oa.web.service.sys;

import com.oa.bean.sys.SysUser;
import com.oa.core.base.BaseService;
import com.oa.core.bean.PageBean;
import com.oa.core.exception.ValidateException;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 用户 Service
 *
 * Created by [张渊]
 * 2017/10/29 15:31
 */
public interface SysUserService extends BaseService<SysUser> {

    SysUser getUserByLoginNameAndPwd(String loginName, char[] password);

    // 根据账号查询用户
    SysUser getUserByLoginName(String loginName);

    // 查询用户列表
    PageBean<SysUser> getUserList(PageBean<SysUser> page);

    // 查询用户列表
    PageBean<SysUser> getUserList(PageBean<SysUser> page, HttpServletRequest request) throws ValidateException;

    // 保存用户，增加保存前验证
    void saveUser(SysUser user) throws ValidateException;

    // 修改用户密码
    Serializable updateUserPwd(SysUser user);
}
