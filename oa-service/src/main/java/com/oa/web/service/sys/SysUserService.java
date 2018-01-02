package com.oa.web.service.sys;

import com.oa.bean.sys.SysUser;
import com.oa.bean.sys.view.UserRoleView;
import com.oa.core.base.BaseService;
import com.oa.core.bean.PageBean;
import com.oa.core.exception.ValidateException;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 用户 Service
 *
 * Created by [张渊]
 * 2017/10/29 15:31
 */
public interface SysUserService extends BaseService<SysUser> {

    SysUser getUserByLoginNameAndPwd(String loginName, String password);

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


    /**
     * 根据用户ID查询角色（role）
     * @param userId 用户ID
     * @return 角色类型列表
     */
    Set<String> getRoleByUserId(Long userId);


    /**
     * 获取用户信息，关联查询角色信息
     * @param page 查询参数
     * @return 用户角色列表封装实体
     */
    PageBean<UserRoleView> getUserAndRoleList(PageBean<UserRoleView> page, HttpServletRequest request);

}
