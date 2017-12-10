package com.oa.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 插件树 实体
 *
 * Created by [张渊]
 * 2017/12/10 16:25
 */
public class TreeNode implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;// tree id
    private String pid;// 父级 id
    private String name;// 节点名
    private String icon;// 节点图标
    private boolean open;// 是否展开节点
    private String url;// 节点链接
    private String target;// 节点打开方式
    private Integer roleId;
    private boolean isParent;// 是否是父级菜单
    private boolean checked;// 是否选中

    private List<TreeNode> children;// 子节点

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean parent) {
        isParent = parent;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
