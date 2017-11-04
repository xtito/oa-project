package com.oa.web.service;

import com.oa.bean.sys.SysUser;
import com.oa.web.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户 Service
 *
 * Created by [张渊]
 * 2017/10/29 15:31
 */
@Service
@Transactional
public class SysUserService {

    @Autowired
    private SysUserMapper mapper;


    public SysUser getUserByUserNameAndPwd(String username, char[] password) {
        return null;
    }

    public void updateUserById(SysUser user) {

    }
}
