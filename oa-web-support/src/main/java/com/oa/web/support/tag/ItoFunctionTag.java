package com.oa.web.support.tag;

import com.oa.bean.sys.SysDepartment;
import com.oa.bean.sys.SysPermission;
import com.oa.bean.sys.SysRole;
import com.oa.bean.sys.SysUser;
import com.oa.core.constant.Constant;
import com.oa.core.utils.CollectionUtil;
import com.oa.core.utils.StringUtil;
import com.oa.core.utils.date.DateUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 前台转换
 * <p/>
 * Created by [张渊]
 * 2017/12/11 12:03
 */
public class ItoFunctionTag {

    private static List<SysDepartment> deptList;
    private static List<SysUser> userList;
    private static List<SysRole> roleList;
    private static List<SysPermission> pmsList;

    private ItoFunctionTag() {
    }


    /**
     * 通过登录名获取用户对象
     *
     * @param loginName 登录名
     * @return 用户实体
     */
    public static SysUser getNicknameByLoginName(String loginName) {
        if (StringUtil.isNotNull(loginName) && CollectionUtil.isNotEmpty(userList)) {
            for (SysUser user : userList) {
                if (user.getLoginName().equals(loginName)) {
                    return user;
                }
            }
        }
        return null;
    }


    /**
     * 根据部门ID查询部门名称
     *
     * @param id 部门ID
     * @return 部门名称
     */
    public static String getDepartmentNameById(String id) {
        if (StringUtil.isNotNull(id) && CollectionUtil.isNotEmpty(deptList)) {
            for (SysDepartment dept : deptList) {
                if (dept.getId() == StringUtil.string2Int(id)) {
                    return dept.getName();
                }
            }
        }
        return "";
    }


    /**
     * 根据权限ID查询权限名称
     *
     * @param id 权限ID
     * @return 权限名称
     */
    public static String getPermissionNameById(String id) {
        if (StringUtil.isNotNull(id) && CollectionUtil.isNotEmpty(pmsList)) {
            for (SysPermission pms : pmsList) {
                if (pms.getId() == StringUtil.string2Int(id)) {
                    return pms.getName();
                }
            }
        }
        return "";
    }


    /**
     * 将日期转换为指定格式字符串
     *
     * @param date    要转换的日期
     * @param pattern 指定日期转换格式
     * @return 转换后的日期字符串
     */
    public static String date2string(Date date, String pattern) {
        try {

            return DateUtil.format(date, pattern);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


    public static String getStatusText(Integer status) {
        if (status != null) {
            return status == Constant.USER_NORMAL ? "正常" : status == Constant.USER_DISABLE ? "禁用"
                    : status == Constant.USER_LOCK ? "锁定" : "";
        }
        return "";
    }


    public static void setDeptList(List<SysDepartment> deptList) {
        ItoFunctionTag.deptList = deptList;
    }

    public static void setUserList(List<SysUser> userList) {
        ItoFunctionTag.userList = userList;
    }

    public static void setRoleList(List<SysRole> roleList) {
        ItoFunctionTag.roleList = roleList;
    }

    public static void setPmsList(List<SysPermission> pmsList) {
        ItoFunctionTag.pmsList = pmsList;
    }
}
