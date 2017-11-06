package com.oa.bean.sys;

import java.io.Serializable;
import java.util.Date;

public class SysRole implements Serializable {
    /**
     * 角色主键ID
     * 表字段 : sys_role.id
     */
    private Long id;

    /**
     * 角色名称
     * 表字段 : sys_role.name
     */
    private String name;

    /**
     * 角色描述
     * 表字段 : sys_role.description
     */
    private String description;

    /**
     * 角色类型
     * 表字段 : sys_role.role_type
     */
    private String roleType;

    /**
     * 创建时间
     * 表字段 : sys_role.create_time
     */
    private Date createTime;

    /**
     * 更新时间
     * 表字段 : sys_role.update_time
     */
    private Date updateTime;

    /**
     * 默认标识（0可删1不可删除）
     * 表字段 : sys_role.def_identify
     */
    private Integer defIdentify;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType == null ? null : roleType.trim();
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

    public Integer getDefIdentify() {
        return defIdentify;
    }

    public void setDefIdentify(Integer defIdentify) {
        this.defIdentify = defIdentify;
    }

    /**
     *
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", name=" + name +
                ", description=" + description +
                ", roleType=" + roleType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", defIdentify=" + defIdentify +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}