<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/static/include/inc.jsp"%>

<ul class="layui-tab-title site-demo-title">
    <li class="tab-li ${param.active == '1' ? 'layui-this' : ''}">
        <a class="trigger-dept-list" data-url="${ctx}/static/pages/sys/dept/sys_department.jsp">部门列表</a>
    </li>

    <li class="tab-li ${param.active == '2' ? 'layui-this' : ''}">
        <a data-url="${ctx}/static/pages/sys/dept/add_department.jsp">添加部门</a>
    </li>

    <c:if test="${param.active == '3'}">
        <li class="tab-li layui-this"><a>更新部门</a></li>
    </c:if>
</ul>
