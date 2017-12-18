<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/static/include/inc.jsp"%>

<ul class="layui-tab-title site-demo-title">
    <li class="tab-li ${param.active == '1' ? 'layui-this' : ''}">
        <a class="trigger-user-list" data-url="${ctx}/static/pages/sys/user/sys_user.jsp">用户列表</a>
    </li>

    <li class="tab-li ${param.active == '2' ? 'layui-this' : ''}">
        <a class="trigger-user-online" data-url="">在线用户</a>
    </li>

    <li class="tab-li ${param.active == '3' ? 'layui-this' : ''}">
        <a data-url="${ctx}/static/pages/sys/user/add_user.jsp">添加用户</a>
    </li>

    <c:if test="${param.active == '4'}">
        <li class="tab-li layui-this"><a>更新用户</a></li>
    </c:if>
</ul>