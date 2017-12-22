package com.oa.web.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.oa.bean.sys.SysRole;
import com.oa.core.bean.PageBean;
import com.oa.core.exception.ValidateException;
import com.oa.core.utils.StringUtil;
import com.oa.web.mapper.SysRoleMapper;
import com.oa.web.service.sys.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色接口实现接口
 *
 * Created by [张渊]
 * 2017/12/15 15:10
 */
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleMapper mapper;
    private final JdbcTemplate template;

    @Autowired
    public SysRoleServiceImpl(SysRoleMapper mapper, JdbcTemplate template) {
        this.mapper = mapper;
        this.template = template;
    }


    /**
     * 根据角色名查询角色
     * @param roleName 角色名称
     * @return 角色实体
     */
    public SysRole getRoleByName(String roleName) {
        return this.mapper.getRoleByName(roleName);
    }

    /**
     * 查询角色列表，并分页
     * @param page 分页封装实体
     * @return 返回 分页后的 Page 实体
     */
    public PageBean<SysRole> getRoleList(PageBean<SysRole> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<SysRole> list = this.mapper.getRoleList(page);
        page.convertPage(list);
        return page;
    }


    /**
     * 保存角色
     * @param role 角色实体
     * @throws ValidateException 验证失败异常
     */
    public void saveRole(SysRole role) throws ValidateException {

        if (StringUtil.isEmpty(role.getName())) {
            throw new ValidateException("角色名称不能为空");
        }

        if (this.getRoleByName(role.getName()) != null) {
            throw new ValidateException("该角色已存在，请更换角色名");
        }

        this.save(role);
    }


    /**
     * 更新角色
     * @param role 要更新的数据实体
     */
    public void updateRole(SysRole role) throws ValidateException {

        if (StringUtil.isEmpty(role.getName())) {
            throw new ValidateException("角色名称不能为空");
        }

        SysRole tempRole = this.getRoleByName(role.getName());
        if (tempRole != null && !tempRole.getId().equals(role.getId())) {
            throw new ValidateException("该角色已存在，请更换角色名");
        }

        this.update(role);
    }


    /**
     * 查询角色列表
     * @param page 查询参数实体
     * @param request 请求参数
     * @return 角色列表
     */
    public PageBean<SysRole> getRoleList(PageBean<SysRole> page, HttpServletRequest request) {

        String roleName = StringUtil.removeTrim(request.getParameter("roleName"));

        if (StringUtil.isNotNull(roleName)) {
            page.put("nameLike", roleName);
        }

        return this.getRoleList(page);
    }


    /**
     * 保存角色
     * @param entity 数据实体
     * @return 保存受影响数
     */
    @Override
    public int save(SysRole entity) {
        return this.mapper.saveSysRole(entity);
    }

    /**
     * 更新角色
     * @param entity 要更新的数据实体
     * @return 更新受影响数
     */
    @Override
    public int update(SysRole entity) {
        return this.mapper.updateSysRole(entity);
    }


    /**
     * 根据主键ID删除角色
     * @param id 数据实体主键ID
     * @return 删除受影响数
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.mapper.deleteByPrimaryKey(id);
    }


    /**
     * 根据主键ID查询角色
     * @param id 数据主键ID
     * @return 角色实体
     */
    @Override
    public SysRole getByPrimaryKey(Long id) {
        return this.mapper.getByPrimaryKey(id);
    }


    /**
     * 查询所有角色
     */
    @Override
    public List<Map<String, Object>> loadRoleList() {
        String sql = "SELECT id, name_ AS text FROM sys_role";
        return this.template.queryForList(sql);
    }


    /**
     * 保存用户角色，为用户分配角色
     * @param userId 待分配用户的用户ID
     * @param roleId 用户选择要分配的角色ID
     */
    public void saveUserRole(String userId, Object[] roleId) throws ValidateException {

        if (StringUtil.isEmpty(roleId)) {
            throw new ValidateException("请至少选择一个角色！");
        }

        if (StringUtil.isEmpty(userId)) {
            throw new ValidateException("操作异常，用户ID丢失，请刷新页面重试！");
        }

        if (roleId.length > 5) {
            throw new ValidateException("最多为用户分配5个角色");
        }

        for (Object rid : roleId) {
            String sql = "INSERT INTO sys_user_role (user_id, role_id) VALUES(?, ?)";

            this.template.update(sql, userId, rid);
        }
    }


    /**
     * 获取所有角色列表
     * @return 所有角色集合
     */
    public List<SysRole> getRoleList() {
        return this.mapper.getRoleAllList();
    }



    /**
     * 根据用户ID查询用户角色ID
     * @param userId 用户ID
     */
    public List<String> getUserRoleIdByUserId(String userId) {
        String sql = "SELECT role_id FROM sys_user_role WHERE user_id = ?";
        return this.template.queryForList(sql, String.class, userId);
    }


    /**
     * 清空用户所有角色
     * @param userId 用户ID
     */
    public void deleteUserRolesAll(String userId) {
        String sql = "DELETE FROM sys_user_role WHERE user_id = ?";
        this.template.update(sql, userId);
    }

}
