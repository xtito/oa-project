package com.oa.web.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.oa.bean.sys.SysUser;
import com.oa.core.bean.PageBean;
import com.oa.core.exception.ValidateException;
import com.oa.core.utils.StringUtil;
import com.oa.core.utils.date.DateUtil;
import com.oa.core.utils.date.DateValida;
import com.oa.web.mapper.SysUserMapper;
import com.oa.web.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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

    @Override
    public PageBean<SysUser> getUserList(PageBean<SysUser> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<SysUser> list = this.mapper.getUserList(page);
        page.convertPage(list);
        return page;
    }

    @Override
    public PageBean<SysUser> getUserList(PageBean<SysUser> page, HttpServletRequest request) throws ValidateException {

        String userName = StringUtil.removeTrim(request.getParameter("userName"));
        String begin = StringUtil.removeTrim(request.getParameter("begin"));
        String end = StringUtil.removeTrim(request.getParameter("end"));

        if (StringUtil.isNotNull(userName)) {
            page.put("nameLike", userName);
        }

        if (StringUtil.isNotNull(begin) && StringUtil.isEmpty(end)) {
            String currentDate = DateUtil.getCurrentDateFormat();
            DateValida.dateValidate(begin, currentDate);

            page.put("begin", begin);
            page.put("end", currentDate);
        }

        if (StringUtil.isEmpty(begin) && StringUtil.isNotNull(end)) {
            String currentDate = DateUtil.getCurrentDateFormat();
            DateValida.dateValidate(currentDate, end);

            page.put("begin", currentDate);
            page.put("end", end);
        }

        if (StringUtil.isNotNull(begin) && StringUtil.isNotNull(end)) {
            DateValida.dateValidate(begin, end);

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

        this.save(user);
    }
}
