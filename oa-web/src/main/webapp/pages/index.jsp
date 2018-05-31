<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@include file="/pages/common/inc.jsp" %>
<!DOCTYPE html>
<html lang="en">

<%--
  
  Created by User: Zy
  Created Date: 2017/8/10 8:49
--%>

<head>
    <meta charset="UTF-8">
    <%@ include file="/pages/common/public-inc.jsp" %>
    <title>主页</title>
    <script type="text/javascript">
        <!--
        // require.js加载完时调用main.js，main.js调用此方法
        function onRequireReady() {
            require(["domReady"], function (doc) {
                require(["jquery", "module-common"], function ($, commonJs) {
                    commonJs.loadContent(ctx + "/pages/include/home.jsp");
                });
            });
        }
        //-->
    </script>
</head>

<body class="layui-layout-body">

<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">
            <a class="btn-logo" href="#">
                <i class="ito ito-logo2 ito-logo-icon"></i>
            </a>
        </div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="javascript:;" target="rightFrame">控制台</a></li>
            <li class="layui-nav-item"><a href="javascript:;" target="rightFrame">商品管理</a></li>
            <li class="layui-nav-item"><a href="javascript:;" target="rightFrame">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" target="rightFrame">邮件管理</a></dd>
                    <dd><a href="javascript:;" target="rightFrame">消息管理</a></dd>
                    <dd><a href="javascript:;" target="rightFrame">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="${ctx}/mvc/login/mgr/loginOut">退了</a></li>
        </ul>
    </div>

    <%-- trigger-* 样式(必须)，用于在他处触发后模拟触发事件的元素标识 --%>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-this">
                    <a class="trigger-home" href="javascript:;" data-url="${ctx}/include/home.jsp">概览</a>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">所有商品</a>
                    <dl class="layui-nav-child">
                        <dd><a target="rightFrame" href="${ctx}/pages/include/home.jsp">列表一</a></dd>
                        <dd><a target="rightFrame" href="${ctx}/pages/sys/user/sys_user.jsp">列表二</a></dd>
                        <dd><a target="rightFrame" href="javascript:;">列表三</a></dd>
                        <dd><a target="rightFrame" href="javascript:;">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">解决方案</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="javascript:;">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:;">云市场</a></li>
                <li class="layui-nav-item"><a href="javascript:;">发布商品</a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">部门/帐户/权限</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" data-url="${ctx}/pages/sys/dept/sys_department.jsp">
                                <i class="ito ito-icon ito-department"></i>
                                <span>部门管理</span>
                            </a>
                        </dd>
                        <dd>
                            <a href="javascript:;" data-url="${ctx}/pages/sys/user/sys_user.jsp">
                                <i class="ito ito-icon ito-user"></i>
                                <span>用户管理</span>
                            </a>
                        </dd>
                        <dd>
                            <a href="javascript:;" data-url="${ctx}/pages/sys/role/sys_role.jsp">
                                <i class="ito ito-icon ito-role"></i>
                                <span>角色管理</span>
                            </a>
                        </dd>
                        <dd>
                            <a href="javascript:;" data-url="${ctx}/pages/sys/pms/user_role_list.jsp">
                                <i class="ito ito-icon ito-permissions"></i>
                                <span>权限管理</span>
                            </a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">系统设置</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;">
                                <i class="ito ito-icon ito-menu-setting"></i>
                                <span>菜单管理</span>
                            </a>
                        </dd>
                        <dd>
                            <a href="javascript:;" data-url="${ctx}/modeler.html">
                                <i class="ito ito-icon ito-menu-setting"></i>
                                <span>流程管理</span>
                            </a>
                        </dd>
                        <dd>
                            <a href="javascript:;">
                                <i class="ito ito-icon ito-dict"></i>
                                <span>数据字典</span>
                            </a>
                        </dd>
                        <dd>
                            <a href="javascript:;">
                                <i class="ito ito-icon ito-setting"></i>
                                <span>系统设置</span>
                            </a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <!-- 内容主体区域 -->
    <div id="main_body" class="main-body"></div>


</div>

</body>

</html>