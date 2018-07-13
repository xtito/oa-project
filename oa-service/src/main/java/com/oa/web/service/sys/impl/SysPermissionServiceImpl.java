package com.oa.web.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.oa.bean.sys.SysPermission;
import com.oa.core.bean.PageBean;
import com.oa.core.exception.ValidateException;
import com.oa.core.utils.StringUtil;
import com.oa.web.mapper.SysPermissionMapper;
import com.oa.web.service.sys.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 权限 Service
 *
 * Created by [张渊]
 * 2018/07/05 15:41
 */
@Service
@Transactional
public class SysPermissionServiceImpl implements SysPermissionService {

    private final SysPermissionMapper mapper;
    private final JdbcTemplate template;

    @Autowired
    public SysPermissionServiceImpl(SysPermissionMapper mapper, JdbcTemplate template) {
        this.mapper = mapper;
        this.template = template;
    }


    /**
     * 根据用户ID查询用户权限
     * @param userId 用户ID
     * @return 用户权限列表ID
     */
    public Set<String> getPermissionByUserId(Long userId) {
        return this.mapper.getPermissionByUserId(userId);
    }


    /**
     * 根据权限权限地址查询权限
     * @param urlPath 权限地址
     * @return 权限实体
     */
    @Override
    public SysPermission getPermissionByUrl(String urlPath) {
        return this.mapper.getPermissionByUrl(urlPath);
    }


    /**
     * 根据权限名查询权限
     * @param pmsName 权限名称
     * @return 权限实体
     */
    @Override
    public SysPermission getPermissionByName(String pmsName) {
        return this.mapper.getPermissionByName(pmsName);
    }


    /**
     * 保存权限
     * @param pms 权限实体
     * @throws ValidateException 验证失败异常
     */
    @Override
    public void savePermission(SysPermission pms) throws ValidateException {

        if (StringUtil.isEmpty(pms.getName())) {
            throw new ValidateException("权限名称不能为空");
        }

        if (this.getPermissionByName(pms.getName()) != null) {
            throw new ValidateException("该权限已存在，请更换权限名");
        }

        if (this.getPermissionByUrl(pms.getUrl()) != null) {
            throw new ValidateException("该权限URL已存在，请更换权限URL");
        }

        this.save(pms);
    }


    /**
     * 查询权限列表
     * @param page 查询参数实体
     * @return 权限列表
     */
    @Override
    public PageBean<SysPermission> getPermissionList(PageBean<SysPermission> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<SysPermission> list = this.mapper.getPermissionList(page);
        page.convertPage(list);
        return page;
    }


    /**
     * 查询权限列表
     * @param page 查询参数实体
     * @return 权限列表
     */
    @Override
    public PageBean<SysPermission> getPermissionList(PageBean<SysPermission> page, HttpServletRequest request) {

        String pmsName = StringUtil.removeTrim(request.getParameter("pmsName"));

        if (StringUtil.isNotNull(pmsName)) {
            page.put("nameLike", pmsName);
        }

        return this.getPermissionList(page);
    }


    /**
     * 保存权限
     * @param entity 数据实体
     * @return 受影响数
     */
    @Override
    public int save(SysPermission entity) {
        return this.mapper.saveSysPermission(entity);
    }

    /**
     * 更新权限
     * @param entity 要更新的数据实体
     * @return 受影响数
     */
    @Override
    public int update(SysPermission entity) {
        return this.mapper.updateSysPermission(entity);
    }

    /**
     * 删除权限
     * @param id 数据实体主键ID
     * @return 受影响数
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据权限主键ID获取权限实体
     * @param id 数据主键ID
     * @return 权限实体
     */
    @Override
    public SysPermission getByPrimaryKey(Long id) {
        return this.mapper.getByPrimaryKey(id);
    }


    /**
     * 保存角色权限
     * @param roleId 角色ID
     * @param pmsId 权限ID
     */
    public void saveRolePms(String roleId, Object[] pmsId) throws ValidateException {

        if (StringUtil.isEmpty(pmsId)) {
            throw new ValidateException("请至少选择一个权限！");
        }

        if (StringUtil.isEmpty(roleId)) {
            throw new ValidateException("操作异常，角色ID丢失，请刷新页面重试！");
        }

        for (Object pid : pmsId) {
            String sql = "INSERT INTO sys_role_permission (role_id, pms_id) VALUES(?, ?)";

            this.template.update(sql, roleId, pid);
        }
    }


    /**
     * 删除指定角色所有权限
     * @param roleId 角色ID
     */
    public void deleteRolePmsAll(String roleId) {
        String sql = "DELETE FROM sys_role_permission WHERE role_id = ?";
        this.template.update(sql, roleId);
    }


    /**
     * 根据角色ID查询角色权限列表
     * @param roleId 角色ID
     * @return 角色权限列表集合
     */
    public List<String> getRolePmsIdByRoleId(String roleId) {
        String sql = "SELECT pms_id FROM sys_role_permission WHERE role_id = ?";
        return this.template.queryForList(sql, String.class, roleId);
    }


    /**
     * 获取所有权限列表
     * @return 所有权限列表集合
     */
    public List<SysPermission> getPermissionList() {
        return this.mapper.getPermissionAllList();
    }

}
