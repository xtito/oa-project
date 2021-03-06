package com.oa.web.mapper;

import com.oa.bean.sys.SysUser;
import com.oa.bean.sys.view.UserRoleView;
import com.oa.core.bean.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户 Mapper
 *
 * Created by [张渊]
 * 2017/10/29 15:32
 */
@Component
public interface SysUserMapper {

    /**
     * 根据用户ID获取角色的Set集合
     * @param userId 用户ID
     * @return 用户ID的角色集合
     */
    Set<String> getRoleByUserId(Long userId);

    /**
     * 将数据插入数据库
     * @param sysUser 数据实体
     * @return 返回插入的该数据主键ID或受影响条数
     */
    int saveSysUser(SysUser sysUser);

    /**
     * 根据数据实体，更新该实体数据对应的数据库表数据
     * @param sysUser 要更新的数据实体
     * @return 返回受影响条数
     */
    int updateSysUser(SysUser sysUser);

    /**
     * 根据数据实体主键，删除该条数据实体对应的数据库数据
     * @param id 数据实体主键ID
     * @return 受影响的条数
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据数据实体主键ID获取该数据实体
     * @param id 数据主键ID
     * @return 数据实体
     */
    SysUser getByPrimaryKey(Long id);


    /**
     * 查询用户列表
     * @param page 请求参数实体
     * @return 用户列表
     */
    List<SysUser> getUserList(PageBean<SysUser> page);

    /**
     * 根据用户登录名查询用户
     * @param loginName 用户登录名
     * @return 用户实体
     */
    SysUser getUserByLoginName(String loginName);

    /**
     * 修改用户密码
     * @param user 要修改的密码的用户实体
     * @return 受影响数
     */
    Serializable updateUserPwd(SysUser user);

    /**
     * 获取用户角色列表
     * @param page 查询参数
     * @return 用户角色列表封装集合
     */
    List<UserRoleView> getUserAndRoleList(PageBean<UserRoleView> page);

    /**
     * 通过用户名和密码查询用户
     * @param map 参数Map实体，key：loginName 登录名  key：password 密码
     * @return 用户实体信息
     */
    SysUser getUserByLoginNameAndPwd(Map<String, String> map);
}
