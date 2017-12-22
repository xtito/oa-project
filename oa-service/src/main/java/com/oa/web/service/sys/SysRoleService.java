package com.oa.web.service.sys;

import com.oa.bean.sys.SysRole;
import com.oa.core.base.BaseService;
import com.oa.core.bean.PageBean;
import com.oa.core.exception.ValidateException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 角色 Service
 *
 * Created by [张渊]
 * 2017/10/29 15:39
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 根据角色名查询角色
     * @param roleName 角色名称
     * @return 角色实体
     */
    SysRole getRoleByName(String roleName);

    /**
     * 查询角色列表，并分页
     * @param page 分页封装实体
     * @return 返回 分页后的 Page 实体
     */
    PageBean<SysRole> getRoleList(PageBean<SysRole> page);


    /**
     * 保存角色
     * @param role 角色实体
     * @throws ValidateException 验证失败异常
     */
    void saveRole(SysRole role) throws ValidateException;


    /**
     * 更新角色
     * @param role 要更新的数据实体
     */
    void updateRole(SysRole role) throws ValidateException;


    /**
     * 查询角色列表
     * @param page 查询参数实体
     * @param request 请求参数
     * @return 角色列表
     */
    PageBean<SysRole> getRoleList(PageBean<SysRole> page, HttpServletRequest request);


    /**
     * 查询角色列表
     */
    List<Map<String, Object>> loadRoleList();

    /**
     * 保存用户角色，为用户分配角色
     * @param userId 待分配用户的用户ID
     * @param roleId 用户选择要分配的角色ID
     */
    void saveUserRole(String userId, Object[] roleId) throws ValidateException;

    /**
     * 根据用户ID查询用户角色ID
     * @param userId 用户ID
     */
    List<String> getUserRoleIdByUserId(String userId);


    /**
     * 清空用户所有角色
     * @param userId 用户ID
     */
    void deleteUserRolesAll(String userId);
}
