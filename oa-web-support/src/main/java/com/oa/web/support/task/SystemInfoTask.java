package com.oa.web.support.task;

import com.oa.bean.sys.SysDepartment;
import com.oa.bean.sys.SysPermission;
import com.oa.bean.sys.SysRole;
import com.oa.bean.sys.SysUser;
import com.oa.core.LoggerUtil;
import com.oa.core.utils.CollectionUtil;
import com.oa.web.support.content.AppContext;
import com.oa.core.utils.StringUtil;
import com.oa.core.utils.date.DateUtil;
import com.oa.web.support.tag.ItoFunctionTag;
import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 同步部门、用户、角色、权限
 * <p/>
 * Created by [张渊]
 * 2017/12/11 13:27
 */
public class SystemInfoTask implements Runnable {

    private static Logger logger = LoggerUtil.getLogger(SystemInfoTask.class);

    private static JdbcTemplate dao;

    private static List<SysDepartment> deptList;
    private static List<SysUser> userList;
    private static List<SysRole> roleList;
    private static List<SysPermission> pmsList;


    @Override
    public void run() {

        try {

            dao = (JdbcTemplate) AppContext.getBean("jdbcTemplate");
            sync();

        } catch (Exception e) {
            logger.error("同步部门、用户、角色、权限发生异常 ", e);
        }
    }

    /**
     * 同步用户、角色、部门、权限
     */
    private void sync() {
        loadSysInfo();

        ItoFunctionTag.setDeptList(deptList);
        ItoFunctionTag.setUserList(userList);
        ItoFunctionTag.setRoleList(roleList);
        ItoFunctionTag.setPmsList(pmsList);
    }

    private void loadSysInfo() {
        listDept();
        listUser();
        listRole();
        listPms();
    }

    private void listDept() {
        String sql = "SELECT * FROM sys_department";
        List<Map<String, Object>> maps = dao.queryForList(sql);
        if (CollectionUtil.isNotEmpty(maps)) {
            List<SysDepartment> list = new ArrayList<SysDepartment>();
            for (Map<String, Object> map : maps) {
                SysDepartment dept = new SysDepartment();
                dept.setId(Long.valueOf(StringUtil.removeTrim(map.get("id"))));
                dept.setName(StringUtil.removeTrim(map.get("name_")));
                dept.setLevel(StringUtil.parseInteger(map.get("level_")));
                dept.setParentId(StringUtil.parseLong(map.get("parent_id")));
                dept.setDescription(StringUtil.removeTrim(map.get("description")));
                dept.setDescription(StringUtil.removeTrim(map.get("correlation_code")));
                dept.setCreateTime(DateUtil.parse(StringUtil.removeTrim(map.get("create_time"))));
                dept.setDefIdentify(StringUtil.parseInteger(map.get("def_identify")));
                list.add(dept);
            }

            deptList = list;
        }
    }

    private void listUser() {
        String sql = "SELECT * FROM sys_user";
        List<Map<String, Object>> maps = dao.queryForList(sql);
        if (CollectionUtil.isNotEmpty(maps)) {
            List<SysUser> list = new ArrayList<SysUser>();
            for (Map<String, Object> map : maps) {
                SysUser user = new SysUser();
                user.setId(Long.valueOf(map.get("id").toString()));
                list.add(user);
            }

            userList = list;
        }
    }

    private void listRole() {
        String sql = "SELECT * FROM sys_role";
        List<Map<String, Object>> maps = dao.queryForList(sql);
        if (CollectionUtil.isNotEmpty(maps)) {
            List<SysRole> list = new ArrayList<SysRole>();
            for (Map<String, Object> map : maps) {
                SysRole role = new SysRole();
                role.setId(Long.valueOf(map.get("id").toString()));
                list.add(role);
            }

            roleList = list;
        }
    }

    private void listPms() {
        String sql = "SELECT * FROM sys_permission";
        List<Map<String, Object>> maps = dao.queryForList(sql);
        if (CollectionUtil.isNotEmpty(maps)) {
            List<SysPermission> list = new ArrayList<SysPermission>();
            for (Map<String, Object> map : maps) {
                SysPermission pms = new SysPermission();
                pms.setId(Long.valueOf(map.get("id").toString()));
                list.add(pms);
            }

            pmsList = list;
        }
    }

}
