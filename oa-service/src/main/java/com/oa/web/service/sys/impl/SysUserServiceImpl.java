package com.oa.web.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.oa.bean.sys.SysUser;
import com.oa.core.bean.PageBean;
import com.oa.core.exception.ValidateException;
import com.oa.core.utils.StringUtil;
import com.oa.core.utils.algorithm.AlgorithmsUtil;
import com.oa.core.utils.date.DateUtil;
import com.oa.core.utils.date.DateValida;
import com.oa.web.mapper.SysUserMapper;
import com.oa.web.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 系统用户管理Service实现
 *
 * Created by [张渊]
 * 2017/11/7 22:23
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper mapper;

    @Autowired
    public SysUserServiceImpl(SysUserMapper mapper) {
        this.mapper = mapper;
    }

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
    public SysUser getUserByLoginNameAndPwd(String username, char[] password) {
        return null;
    }

    /**
     * 根据用户登录名查询用户
     * @param loginName 用户登录名
     * @return 用户实体
     */
    @Override
    public SysUser getUserByLoginName(String loginName) {
        return this.mapper.getUserByLoginName(loginName);
    }

    @Override
    public PageBean<SysUser> getUserList(PageBean<SysUser> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<SysUser> list = this.mapper.getUserList(page);
        page.convertPage(list);
        return page;
    }

    @Override
    public PageBean<SysUser> getUserList(PageBean<SysUser> page, HttpServletRequest request) throws ValidateException {

        String userStatus = StringUtil.removeTrim(request.getParameter("userStatus"));
        String loginName = StringUtil.removeTrim(request.getParameter("loginName"));
        String email = StringUtil.removeTrim(request.getParameter("email"));
        String phone = StringUtil.removeTrim(request.getParameter("phone"));
        String nickname = StringUtil.removeTrim(request.getParameter("nickname"));
        String begin = StringUtil.removeTrim(request.getParameter("begin"));
        String end = StringUtil.removeTrim(request.getParameter("end"));

        if (StringUtil.isNotNull(userStatus)) {
            page.put("userStatus", userStatus);
        }

        if (StringUtil.isNotNull(loginName)) {
            page.put("loginNameLike", loginName);
        }

        if (StringUtil.isNotNull(email)) {
            page.put("email", email);
        }

        if (StringUtil.isNotNull(phone)) {
            page.put("phone", phone);
        }

        if (StringUtil.isNotNull(nickname)) {
            page.put("nicknameLike", nickname);
        }

        if (StringUtil.isNotNull(begin) && StringUtil.isNotNull(end)) {
            DateValida.startDateOrEndDateValida(begin, end);

            page.put("begin", begin);
            page.put("end", end);
        }

        return this.getUserList(page);
    }

    @Override
    public void saveUser(SysUser user) throws ValidateException {

        if (StringUtil.isEmpty(user.getLoginName())) {
            throw new ValidateException("用户登录名不能为空");
        }

        if (this.getUserByLoginName(user.getLoginName()) != null) {
            throw new ValidateException("该用户已存在，请更换用户名");
        }

        user.setPassword(AlgorithmsUtil.encrypt(user.getPassword(), user.getLoginName()));
        this.save(user);
    }


    /**
     * 根据用户
     * @param user 要修改的密码的用户实体
     * @return 受影响数
     */
    @Override
    public Serializable updateUserPwd(SysUser user) {
        user = this.getByPrimaryKey(user.getId());
        user.setPassword(AlgorithmsUtil.encrypt(user.getPassword(), user.getLoginName()));
        return this.mapper.updateUserPwd(user);
    }


    /**
     * 根据用户ID查询角色（role）
     * @param userId 用户ID
     * @return 角色类型列表
     */
    public Set<String> getRoleByUserId(Long userId) {
        return this.mapper.getRoleByUserId(userId);
    }
}
