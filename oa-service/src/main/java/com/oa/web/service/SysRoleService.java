package com.oa.web.service;

import com.oa.web.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * 角色 Service
 *
 * Created by [张渊]
 * 2017/10/29 15:39
 */
@Service
@Transactional
public class SysRoleService {

    @Autowired
    private SysRoleMapper mapper;

    /**
     * 根据用户ID查询角色（role）
     * @param userId 用户ID
     * @return 角色类型列表
     */
    public Set<String> getRoleByUserId(Long userId) {
        return this.mapper.getRoleByUserId(userId);
    }


}
