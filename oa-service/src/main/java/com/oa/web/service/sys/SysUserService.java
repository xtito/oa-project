package com.oa.web.service.sys;

import com.oa.bean.sys.SysUser;
import com.oa.core.base.BaseService;

/**
 * 用户 Service
 *
 * Created by [张渊]
 * 2017/10/29 15:31
 */
public interface SysUserService extends BaseService<SysUser> {

    SysUser getUserByUserNameAndPwd(String username, char[] password);

}
