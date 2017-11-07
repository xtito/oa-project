package com.oa.web.service.sys.impl;

import com.oa.bean.sys.SysUser;
import com.oa.web.mapper.SysUserMapper;
import com.oa.web.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统用户管理Service实现
 *
 * Created by [张渊]
 * 2017/11/7 22:23
 */
@Service("sysUserService")
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper mapper;

    @Override
    public int save(SysUser sysUser) {
        return this.mapper.saveSysUser(sysUser);
    }

    @Override
    public int update(SysUser sysUser) {
        return this.mapper.updateSysUser(sysUser);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    @Override
    public SysUser getByPrimaryKey(Long id) {
        return this.mapper.getByPrimaryKey(id);
    }

    @Override
    public List<SysUser> getAll() {
        return this.mapper.getAll();
    }

    @Override
    public SysUser getUserByUserNameAndPwd(String username, char[] password) {
        return null;
    }
}
