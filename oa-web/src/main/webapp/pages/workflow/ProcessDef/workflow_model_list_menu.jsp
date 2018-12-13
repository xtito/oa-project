<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/pages/common/inc.jsp"%>

<ul class="layui-tab-title site-demo-title">
    <li class="tab-li ${param.active == '1' ? 'layui-this' : ''}">
        <a class="trigger-dept-list" data-url="${ctx}/pages/sys/dept/sys_department.jsp">
            <i class="ito ito-product-type-setting"></i>
            <span>流程模型列表</span>
        </a>
    </li>

    <li class="tab-li ${param.active == '2' ? 'layui-this' : ''}">
        <a data-url="${ctx}/pages/sys/dept/add_department.jsp">
            <i class="ito ito-add-icon"></i>
            <span>流程定义列表</span>
        </a>
    </li>

    <li class="tab-li ${param.active == '3' ? 'layui-this' : ''}">
        <a data-url="${ctx}/pages/sys/dept/add_department.jsp">
            <i class="ito ito-the-arrow-right"></i>
            <span>运行中流程</span>
        </a>
    </li>

    <li class="tab-li ${param.active == '4' ? 'layui-this' : ''}">
        <a>
            <i class="ito ito-put-in-storage"></i>
            <span>流程部署</span>
        </a>
    </li>
</ul>
