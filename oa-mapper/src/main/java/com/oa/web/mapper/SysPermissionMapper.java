package com.oa.web.mapper;

import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 权限 Mapper
 *
 * Created by [张渊]
 * 2017/10/29 15:42
 */
@Component
public interface SysPermissionMapper {


    Set<String> getPermissionByUserId(Long userId);

}
