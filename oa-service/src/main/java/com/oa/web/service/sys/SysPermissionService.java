package com.oa.web.service.sys;

import com.oa.bean.sys.SysPermission;
import com.oa.core.base.BaseService;
import com.oa.core.bean.PageBean;
import com.oa.core.exception.ValidateException;
import com.oa.web.mapper.SysPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 权限 Service
 *
 * Created by [张渊]
 * 2017/10/29 15:41
 */
public interface SysPermissionService extends BaseService<SysPermission> {


    /**
     * 根据用户ID查询用户权限
     * @param userId 用户ID
     * @return 用户权限列表ID
     */
    Set<String> getPermissionByUserId(Long userId);


    /**
     * 根据权限权限地址查询权限
     * @param urlPath 权限地址
     * @return 权限实体
     */
    SysPermission getPermissionByUrl(String urlPath);


    /**
     * 根据权限名查询权限
     * @param pmsName 权限名称
     * @return 权限实体
     */
    SysPermission getPermissionByName(String pmsName);


    /**
     * 保存权限
     * @param pms 权限实体
     * @throws ValidateException
     */
    Serializable savePermission(SysPermission pms) throws ValidateException;


    /**
     * 查询权限列表
     * @param page 查询参数实体
     * @return 权限列表
     */
    PageBean<SysPermission> getPermissionList(PageBean<SysPermission> page);


    /**
     * 查询权限列表
     * @param page 查询参数实体
     * @param request 请求参数
     * @return 权限列表
     */
    PageBean<SysPermission> getPermissionList(PageBean<SysPermission> page, HttpServletRequest request);

}
