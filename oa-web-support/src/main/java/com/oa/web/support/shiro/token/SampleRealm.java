package com.oa.web.support.shiro.token;

import com.oa.bean.sys.SysUser;
import com.oa.core.constant.Constant;
import com.oa.web.service.SysPermissionService;
import com.oa.web.service.SysRoleService;
import com.oa.web.service.SysUserService;
import com.oa.web.support.shiro.token.manager.TokenManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Set;

/**
 * shiro 认证 + 授权   重写
 * <p/>
 * Created by [张渊]
 * 2017/10/29 15:13
 */
public class SampleRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysPermissionService permissionService;

    /**
     * 用于认证的方法，主要针对用户登录
     *
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {

        // 1. 把 AuthenticationToken 转换为 UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) authToken;

        // 2. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        SysUser user = this.userService.getUserByUserNameAndPwd(upToken.getUsername(), upToken.getPassword());

        // 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if (null == user) {

            throw new AccountException("帐号或密码不正确！");

        } else if (Constant.USER_DISABLE == user.getStatus()) {
            /**
             * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
             */
            throw new DisabledAccountException("帐号已经禁止登录！");

        } else if (Constant.USER_LOCK == user.getStatus()) {

            throw new LockedAccountException("用户已被锁定！");

        } else {
            //更新登录时间 last login time
            user.setLastLoginTime(new Date());
            userService.updateUserById(user);
        }

        // 盐值.实现不同用户同样的密码结果也不同
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getLoginName());

        /**
         * 参数一：principal 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
         * 参数二：hashedCredentials 密码. 数据库查询的密码
         * 参数三：credentialsSalt 盐值.
         * 参数四：realmName 当前 realm 对象的 name. 调用父类的 getName() 方法即可
         */
        return new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, getName());
    }

    /**
     * 用于用户授权的方法
     * <p/>
     * 授权会被 shiro 回调的方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        Long userId = TokenManager.getUserId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 根据用户ID查询角色（role），放入到Authorization里。
        Set<String> roles = this.roleService.getRoleByUserId(userId);
        info.setRoles(roles);

        //根据用户ID查询权限（permission），放入到Authorization里。
        Set<String> permissions = this.permissionService.getPermissionByUserId(userId);
        info.setStringPermissions(permissions);
        return info;
    }


    /**
     * 清空当前用户权限信息
     */
    public  void clearCachedAuthorizationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 指定principalCollection 清除
     */
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }

}
