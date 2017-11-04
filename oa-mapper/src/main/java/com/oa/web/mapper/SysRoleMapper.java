package com.oa.web.mapper;

import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 角色 Mapper
 *
 * Created by [张渊]
 * 2017/10/29 15:40
 */
@Component
public interface SysRoleMapper {

    Set<String> getRoleByUserId(Long userId);

}
