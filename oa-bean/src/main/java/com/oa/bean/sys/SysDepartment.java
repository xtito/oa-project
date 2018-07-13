package com.oa.bean.sys;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门实体
 *
 * Created by [张渊]
 * 2018/07/09 16:48
 */
public class SysDepartment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门主键ID
     * 表字段 : sys_department.id
     */
    private Long id;

    /**
     * 部门名称
     * 表字段 : sys_department.name_
     */
    private String name;

    /**
     * 部门级别
     * 表字段 : sys_department.level_
     */
    private Integer level;

    /**
     * 上级所属部门ID
     * 表字段 : sys_department.parent_id
     */
    private Long parentId;

    /**
     * 部门描述
     * 表字段 : sys_department.description
     */
    private String description;

    /**
     * 部门关联关系描述ID拼接
     * 表字段 : sys_department.correlation_code
     */
    private String correlationCode;

    /**
     * 创建时间
     * 表字段 : sys_department.create_time
     */
    private Date createTime;

    /**
     * 默认标识（0可删1不可删除）
     * 表字段 : sys_department.def_identify
     */
    private Integer defIdentify;


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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCorrelationCode() {
        return correlationCode;
    }

    public void setCorrelationCode(String correlationCode) {
        this.correlationCode = correlationCode == null ? null : correlationCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
                ", level=" + level +
                ", parentId=" + parentId +
                ", description=" + description +
                ", correlationCode=" + correlationCode +
                ", createTime=" + createTime +
                ", defIdentify=" + defIdentify +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }

}
