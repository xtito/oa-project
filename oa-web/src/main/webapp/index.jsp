<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@include file="/inc.jsp" %>
<!DOCTYPE html>
<html lang="en">

<%--
  
  Created by User: Zy
  Created Date: 2017/8/10 8:49
--%>

<head>
    <meta charset="UTF-8">
    <%@ include file="static/common/public-inc.jsp" %>
    <title>主页</title>
    <script type="text/javascript">
        <!--
        // require.js加载完时调用main.js，main.js调用此方法
        function onRequireReady() {
            require(["domReady"], function (doc) {
            });
        }
        //-->
    </script>
</head>

<body>

<div class="zy-container">
    <div class="zy-top-bar">
        <div class="console-top-bar">
            <div class="top-bar-wrap">
                <div class="top-bar-head">
                    <a class="btn-logo" href="#">
                        <img src="${ctx}/static/images/logo/white/logo.png" />
                    </a>
                </div>

                <div class="top-bar-info">
                    <div class="info-item">
                        <ol>
                            <li><a href="#">个人门户</a></li>
                            <li><a href="#">公司门户</a></li>
                            <li><a href="#">消息中心</a></li>
                            <li><a href="#"><span class="fa fa-user"></span></a></li>
                            <li><a href="#"><span class="fa fa-power-off"></span></a></li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="zy-work-body">
        <div class="zy-work-sidebar">
            <div class="sidebar-content">
                <div class="ng-scope">
                    <div class="sidebar-inner">
                        <div class="sidebar-fold">
                            <span class="fa fa-bars fa-rotate-90"></span>
                        </div>

                        <div class="sidebar-nav active">
                            <div class="sidebar-title">
                                <div class="sidebar-title-inner">
                                    <span class="title-icon">
                                        <i class="fa icon"></i>
                                    </span>
                                    <span class="title-text">新闻管理</span>
                                </div>
                            </div>
                            <ul class="sidebar-ul">
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa icon-circle"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">新闻类别</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa icon-cloud"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">新闻列表</span>
                                    </a>
                                </li>
                            </ul>
                        </div>

                        <div class="sidebar-nav">
                            <div class="sidebar-title">
                                <div class="sidebar-title-inner">
                                    <span class="title-icon">
                                        <i class="fa icon"></i>
                                    </span>
                                    <span class="title-text">公告管理</span>
                                </div>
                            </div>
                            <ul class="sidebar-ul">
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa icon-circle"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">公告类别</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa icon-cloud"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">公告列表</span>
                                    </a>
                                </li>
                            </ul>
                        </div>

                        <div class="sidebar-nav">
                            <div class="sidebar-title">
                                <div class="sidebar-title-inner">
                                    <span class="title-icon">
                                        <i class="fa icon"></i>
                                    </span>
                                    <span class="title-text">考勤管理</span>
                                </div>
                            </div>
                            <ul class="sidebar-ul">
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa icon-circle"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">我的考勤</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa icon-cloud"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">考勤统计</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa icon-cloud"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">考勤信息管理</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa icon-cloud"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">考勤设置</span>
                                    </a>
                                </li>
                            </ul>
                        </div>

                        <div class="sidebar-nav">
                            <div class="sidebar-title">
                                <div class="sidebar-title-inner">
                                    <span class="title-icon">
                                        <i class="fa icon"></i>
                                    </span>
                                    <span class="title-text">办公用品</span>
                                </div>
                            </div>
                            <ul class="sidebar-ul">
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa icon-circle"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">我的考勤</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa icon-cloud"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">使用申请</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa icon-cloud"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">定义类别</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa icon-cloud"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">基本资料</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa icon-cloud"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">入库管理</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa icon-cloud"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">申请审批</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa icon-cloud"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">采购清单</span>
                                    </a>
                                </li>
                            </ul>
                        </div>

                        <div class="sidebar-nav">
                            <div class="sidebar-title">
                                <div class="sidebar-title-inner">
                                    <span class="title-icon">
                                        <i class="fa icon"></i>
                                    </span>
                                    <span class="title-text">人事管理</span>
                                </div>
                            </div>
                            <ul class="sidebar-ul">
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa fa-sitemap"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">人事</span>
                                    </a>
                                </li>
                            </ul>
                        </div>

                        <div class="sidebar-nav">
                            <div class="sidebar-title">
                                <div class="sidebar-title-inner">
                                    <span class="title-icon">
                                        <i class="fa icon"></i>
                                    </span>
                                    <span class="title-text">车辆管理</span>
                                </div>
                            </div>
                            <ul class="sidebar-ul">
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa fa-sitemap"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">车辆</span>
                                    </a>
                                </li>
                            </ul>
                        </div>

                        <div class="sidebar-nav">
                            <div class="sidebar-title">
                                <div class="sidebar-title-inner">
                                    <span class="title-icon">
                                        <i class="fa icon"></i>
                                    </span>
                                    <span class="title-text">部门/帐户/权限</span>
                                </div>
                            </div>
                            <ul class="sidebar-ul">
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa fa-sitemap"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">部门管理</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa fa-user"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">用户管理</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa fa-user-secret"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">角色管理</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa fa-key"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">权限管理</span>
                                    </a>
                                </li>
                            </ul>
                        </div>

                        <div class="sidebar-nav">
                            <div class="sidebar-title">
                                <div class="sidebar-title-inner">
                                    <span class="title-icon">
                                        <i class="fa icon"></i>
                                    </span>
                                    <span class="title-text">系统设置</span>
                                </div>
                            </div>
                            <ul class="sidebar-ul">
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa fa-indent"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">菜单管理</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa fa-book"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">数据字典</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="item-a">
                                        <div class="nav-icon">
                                            <span class="icon-ecs">
                                                <i class="fa fa-cog"></i>
                                            </span>
                                        </div>
                                        <span class="nav-title">系统设置</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="zy-work-min">
            <div class="main-body">
                <div class="ng-scope">
                    <div class="section-main">
                        <div class="home">
                            <div class="home-ads">
                                <div class="ads-path col-lg-12">
                                    <ol class="breadcrumb">
                                        <li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
                                        <li><i class="fa fa-laptop"></i>Dashboard</li>
                                    </ol>
                                </div>
                            </div>
                        </div>

                        <div class="main-content">
                            <button class="but but-default">按钮</button>
                            <button class="but but-primary">按钮</button>
                            <button class="but but-success">按钮</button>
                            <button class="but but-info">按钮</button>
                            <button class="but but-warning">按钮</button>
                            <button class="but but-danger">按钮</button>
                            <button class="but but-link">按钮</button>
                            <button class="but but-primary but-lg">按钮</button>
                            <button class="but but-primary but-sm">按钮</button>
                            <button class="but but-primary but-xs">按钮</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>