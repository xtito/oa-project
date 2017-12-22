package com.oa.bean.sys.view;

import com.oa.bean.sys.SysRole;

/**
 * Created by [张渊]
 * 2017/12/22 16:56
 */
public class RolePermissionView extends SysRole {

    private String pmsId;// 权限ID
    private String pmsName;// 权限名称

    public String getPmsId() {
        return pmsId;
    }

    public void setPmsId(String pmsId) {
        this.pmsId = pmsId;
    }

    public String getPmsName() {
        return pmsName;
    }

    public void setPmsName(String pmsName) {
        this.pmsName = pmsName;
    }
}
