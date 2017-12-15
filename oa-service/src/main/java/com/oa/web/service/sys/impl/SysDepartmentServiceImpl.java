package com.oa.web.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.oa.bean.TreeNode;
import com.oa.bean.sys.SysDepartment;
import com.oa.core.bean.PageBean;
import com.oa.core.exception.ValidateException;
import com.oa.core.utils.StringUtil;
import com.oa.core.utils.date.DateUtil;
import com.oa.core.utils.date.DateValida;
import com.oa.web.mapper.SysDepartmentMapper;
import com.oa.web.service.sys.SysDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by [张渊]
 * 2017/11/25 17:02
 */
@Service("sysDepartmentService")
@Transactional
public class SysDepartmentServiceImpl implements SysDepartmentService {

    @Autowired
    private SysDepartmentMapper mapper;

    @Override
    public int save(SysDepartment entity) {
        return this.mapper.saveDepartment(entity);
    }

    @Override
    public int update(SysDepartment entity) {
        return this.mapper.updateDepartment(entity);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    @Override
    public SysDepartment getByPrimaryKey(Long id) {
        return this.mapper.getByPrimaryKey(id);
    }

    @Override
    public List<SysDepartment> getDepartmentByPid(String deptId) {
        return this.mapper.getDepartmentByPid(deptId);
    }

    @Override
    public int saveDepartment(SysDepartment dept) throws ValidateException {

        if (StringUtil.isEmpty(dept.getName())) {
            throw new ValidateException("部门名称不能为空");
        }

        if (dept.getParentId() == null) {
            throw new ValidateException("必须指定上级部门");
        }

        if (this.getDepartmentByName(dept.getName()) != null) {
            throw new ValidateException("该部门已经存在");
        }

        return this.save(dept);
    }

    @Override
    public SysDepartment getDepartmentByName(String deptName) {
        return this.mapper.getDepartmentByName(deptName);
    }

    @Override
    public PageBean<SysDepartment> getDepartmentList(PageBean<SysDepartment> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<SysDepartment> list = this.mapper.getDepartmentList(page);
        page.convertPage(list);
        return page;
    }

    @Override
    public PageBean<SysDepartment> getDepartmentList(PageBean<SysDepartment> page, HttpServletRequest request) throws ValidateException {

        String deptName = StringUtil.removeTrim(request.getParameter("deptName"));
        String begin = StringUtil.removeTrim(request.getParameter("begin"));
        String end = StringUtil.removeTrim(request.getParameter("end"));

        if (StringUtil.isNotNull(deptName)) {
            page.put("nameLike", deptName);
        }

        if (StringUtil.isNotNull(begin) && StringUtil.isEmpty(end)) {
            String currentDate = DateUtil.getCurrentDateFormat("yyyy-MM-dd");
            DateValida.dateValidate(begin, currentDate);

            page.put("begin", begin);
            page.put("end", currentDate);
        }

        if (StringUtil.isEmpty(begin) && StringUtil.isNotNull(end)) {
            String currentDate = DateUtil.getCurrentDateFormat("yyyy-MM-dd");
            DateValida.dateValidate(currentDate, end);

            page.put("begin", currentDate);
            page.put("end", end);
        }

        if (StringUtil.isNotNull(begin) && StringUtil.isNotNull(end)) {
            DateValida.dateValidate(begin, end);

            page.put("begin", begin);
            page.put("end", end);
        }

        return this.getDepartmentList(page);
    }

    /**
     * 查询一级部门
     * @return 一级部门列表
     */
    @Override
    public List<TreeNode> listRootTree() {
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        TreeNode node = new TreeNode();
        node.setId("0");
        node.setName("全部部门");
        node.setOpen(true);

        List<TreeNode> list = this.listChildTree(node.getId());

        if (CollectionUtils.isEmpty(list)) {
            node.setIsParent(false);
        } else {
            node.setIsParent(true);
            node.setChildren(list);
        }
        nodes.add(node);
        return nodes;
    }

    /**
     * 根据部门ID查询子部门
     * @return 子部门列表
     */
    @Override
    public List<TreeNode> listChildTree(String deptId) {

        List<SysDepartment> list = this.getDepartmentByPid(deptId);
        if (!CollectionUtils.isEmpty(list)) {
            List<TreeNode> nodes = new ArrayList<TreeNode>();
            for (SysDepartment dept : list) {
                TreeNode node = new TreeNode();
                node.setId(dept.getId().toString());
                node.setName(dept.getName());
                node.setPid(dept.getParentId().toString());
                node.setOpen(false);
                node.setChecked(false);
                List<SysDepartment> childList = this.getDepartmentByPid(dept.getId().toString());
                if (CollectionUtils.isEmpty(childList)) {
                    node.setIsParent(false);
                } else {
                    node.setIsParent(true);
                }
                nodes.add(node);
            }
            return nodes;
        }

        return null;
    }

    /**
     * 查询所有部门树列表
     * @return 所有部门
     */
    @Override
    public List<TreeNode> listTreeAll() {
        List<TreeNode> nodes = this.listRootTree();
        return this.listChildrenAll(nodes);
    }

    public List<TreeNode> listChildrenAll(List<TreeNode> nodes) {
        if (!CollectionUtils.isEmpty(nodes)) {
            for (TreeNode tree : nodes) {
                // 如果是父节点
                if (tree.getIsParent()) {
                    // 查询该节点下部门
                    List<SysDepartment> list = this.getDepartmentByPid(tree.getId());
                    List<TreeNode> childList = new ArrayList<TreeNode>();

                    if (!CollectionUtils.isEmpty(list)) {
                        for (SysDepartment dept : list) {
                            TreeNode deptTree = new TreeNode();
                            deptTree.setName(dept.getName());
                            deptTree.setId(dept.getId().toString());
                            deptTree.setPid(dept.getParentId().toString());
                            deptTree.setOpen(false);
                            deptTree.setChecked(false);
                            // 查询该节点下是否有节点
                            List<SysDepartment> canParent = this.getDepartmentByPid(dept.getId().toString());
                            if (canParent.isEmpty()) {
                                deptTree.setIsParent(false);
                            } else {
                                deptTree.setIsParent(true);
                            }
                            childList.add(deptTree);
                        }
                    }

                    tree.setChildren(childList);

                    if (!CollectionUtils.isEmpty(childList)) {
                        this.listChildrenAll(childList);
                    }
                }
            }
        }
        return nodes;
    }
}
