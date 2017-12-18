<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/static/include/inc.jsp"%>

<ul class="layui-tab-title site-demo-title">
    <li class="tab-li ${param.active == '1' ? 'layui-this' : ''}">
        <a class="trigger-role-list" data-url="${ctx}/static/pages/sys/role/sys_role.jsp">角色列表</a>
    </li>

    <li class="tab-li ${param.active == '2' ? 'layui-this' : ''}">
        <a data-url="${ctx}/static/pages/sys/role/add_role.jsp">添加角色</a>
    </li>

    <c:if test="${param.active == '3'}">
        <li class="tab-li layui-this"><a>更新角色</a></li>
    </c:if>
</ul>