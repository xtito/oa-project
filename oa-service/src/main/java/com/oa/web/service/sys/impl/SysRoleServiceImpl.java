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

}
