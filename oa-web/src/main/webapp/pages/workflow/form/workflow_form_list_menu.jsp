<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/pages/common/inc.jsp"%>

<ul class="layui-tab-title site-demo-title">
    <li class="tab-li ${param.active == '1' ? 'layui-this' : ''}">
        <a class="trigger-dept-list" data-url="${ctx}/pages/sys/dept/sys_department.jsp">
            <i class="ito ito-shopping-list"></i>
            <span>表单列表</span>
        </a>
    </li>

    <li class="tab-li ${param.active == '2' ? 'layui-this' : ''}">
        <a class="trigger-dept-list" data-url="${ctx}/pages/sys/dept/sys_department.jsp">
            <i class="ito ito-add-icon"></i>
            <span>添加表单</span>
        </a>
    </li>

    <li class="tab-li ${param.active == '2' ? 'layui-this' : ''}">
        <a>
            <i class="ito ito-edit"></i>
            <span>更新表单</span>
        </a>
    </li>
</ul>
