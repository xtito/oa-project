package com.oa.bean.sys.view;

import com.oa.bean.sys.SysUser;

/**
 * Created by [张渊]
 * 2017/12/21 13:52
 */
public class UserRoleView extends SysUser {

    private String roleId;// 角色ID。 以英文,号分割
    private String roleName;// 角色名称。 以英文,号分割


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
