package com.oa.bean.sys;

import java.io.Serializable;

public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限主键ID
     * 表字段 : sys_permission.id
     */
    private Long id;

    /**
     * 权限名称
     * 表字段 : sys_permission.name
     */
    private String name;

    /**
     * 权限URL
     * 表字段 : sys_permission.url
     */
    private String url;

    /**
     * 权限描述
     * 表字段 : sys_permission.description
     */
    private String description;

    /**
     * UUID用于树分级
     * 表字段 : sys_permission.uuid
     */
    private String uuid;

    /**
     * 权限级别
     * 表字段 : sys_permission.pms_level
     */
    private Integer pmsLevel;

    /**
     * 父级权限ID，也就是父级权限的UUID
     * 表字段 : sys_permission.pid
     */
    private String pid;

    /**
     * 权限树图标路径
     * 表字段 : sys_permission.icon
     */
    private String icon;

    /**
     * 排序ID
     * 表字段 : sys_permission.order_by_id
     */
    private Integer orderById;

    /**
     * 权限类型
     * 表字段 : sys_permission.pms_type
     */
    private Integer pmsType;

    /**
     * 默认标识（0可删1不可删除）
     * 表字段 : sys_permission.def_identify
     */
    private Integer defIdentify;

    private String parentName;// 父级权限名称


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Integer getPmsLevel() {
        return pmsLevel;
    }

    public void setPmsLevel(Integer pmsLevel) {
        this.pmsLevel = pmsLevel;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getOrderById() {
        return orderById;
    }

    public void setOrderById(Integer orderById) {
        this.orderById = orderById;
    }

    public Integer getPmsType() {
        return pmsType;
    }

    public void setPmsType(Integer pmsType) {
        this.pmsType = pmsType;
    }

    public Integer getDefIdentify() {
        return defIdentify;
    }

    public void setDefIdentify(Integer defIdentify) {
        this.defIdentify = defIdentify;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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
                ", url=" + url +
                ", description=" + description +
                ", uuid=" + uuid +
                ", pmsLevel=" + pmsLevel +
                ", pid=" + pid +
                ", icon=" + icon +
                ", orderById=" + orderById +
                ", pmsType=" + pmsType +
                ", defIdentify=" + defIdentify +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}