package com.oa.web.service.sys;

import com.oa.web.mapper.SysPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * 权限 Service
 *
 * Created by [张渊]
 * 2017/10/29 15:41
 */
@Service
@Transactional
public class SysPermissionService {

    @Autowired
    private SysPermissionMapper mapper;


    public Set<String> getPermissionByUserId(Long userId) {
        return this.mapper.getPermissionByUserId(userId);
    }
}
