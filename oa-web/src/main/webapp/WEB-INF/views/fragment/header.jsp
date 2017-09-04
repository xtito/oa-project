<%@ page language="java" pageEncoding="UTF-8" %>

<header id="header">
    <nav id="main_menu" class="main-menu">
        <div class="menu-con">
            <div class="nav-bar-logo">
                <a class="nav-brand" href="#">
                    <h1 style="color: #fff; padding: 16px;">OA办公平台</h1>
                    <%--<img src="${ctx}/static/images/logo1.png" alt="logo" />--%>
                </a>
            </div>

            <div class="nav-bar-m">
                <div class="nav-bar-menu">
                    <ul id="nav_menu" class="nav-bar">
                        <li class="scroll active">
                            <a href="home.jsp">首页</a>
                        </li>
                        <li class="scroll">
                            <a href="notice/noticeManager.jsp">通知公告</a>
                        </li>
                        <li class="scroll">
                            <a href="officialDoc/officialDocument.jsp">公文管理</a>
                        </li>
                        <li class="scroll">
                            <a href="person/personManager.jsp">人员管理</a>
                        </li>
                        <li class="scroll">
                            <a href="work/workingPlan.jsp">工作计划</a>
                        </li>
                        <li class="scroll">
                            <a href="car/carManager.jsp">车辆管理</a>
                        </li>
                        <li class="scroll">
                            <a href="doc/documentManager.jsp">文档管理</a>
                        </li>
                        <li class="scroll">
                            <a href="sys/sysConfig.jsp">系统设置</a>
                        </li>
                    </ul>
                </div>
                <div class="nav-bar-prompt">
                    <p class="prompt-wel">
                        <a class="le-user" title="欢迎您，${user}">欢迎您，张三张三张三张三</a>
                    </p>
                    <p class="prompt-wait"><a class="le-wait" href="#">待办事项：3</a></p>
                </div>
            </div>

        </div>
    </nav>
</header>
