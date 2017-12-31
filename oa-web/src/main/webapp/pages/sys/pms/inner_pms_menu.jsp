<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc.jsp"%>

<ul class="layui-tab-title site-demo-title">
    <li class="tab-li ${param.active == '1' ? 'layui-this' : ''}">
        <a data-url="${ctx}/static/pages/sys/pms/user_role_list.jsp">
            <i class="ito ito-setting-role"></i>
            <span>角色分配</span>
        </a>
    </li>

    <li class="tab-li ${param.active == '2' ? 'layui-this' : ''}">
        <a data-url="${ctx}/static/pages/sys/pms/role_pms_list.jsp">
            <i class="ito ito-setting-permissions"></i>
            <span>权限分配</span>
        </a>
    </li>

    <li class="tab-li ${param.active == '3' ? 'layui-this' : ''}">
        <a class="trigger-pms-list" data-url="${ctx}/static/pages/sys/pms/sys_permission.jsp">
            <i class="ito ito-permissions-list"></i>
            <span>权限列表</span>
        </a>
    </li>

    <li class="tab-li ${param.active == '4' ? 'layui-this' : ''}">
        <a data-url="${ctx}/static/pages/sys/pms/add_permission.jsp">
            <i class="ito ito-add-permissions"></i>
            <span>添加权限</span>
        </a>
    </li>



    <c:if test="${param.active == '5'}">
        <li class="tab-li layui-this">
            <a><i class="ito ito-edit-permissions"></i><span>更新权限</span></a>
        </li>
    </c:if>
</ul>