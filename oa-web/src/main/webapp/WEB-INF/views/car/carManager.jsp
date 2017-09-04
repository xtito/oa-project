<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@include file="/inc.jsp" %>
<!DOCTYPE html>
<html lang="en">

<%--
  
  Created by User: Zy
  Created Date: 2017/7/27 13:20
--%>

<head>
    <meta charset="UTF-8">
    <%@ include file="/static/common/public-inc.jsp"%>
    <%@ include file="/static/common/js-inc.jsp"%>
    <title>车辆管理</title>
</head>

<body>

    <%@ include file="../fragment/header.jsp"%>

    <div id="container" class="container">
        <div class="main-con">
            <div class="con-layout">
                <div class="sidebar col-l-2">
                    <div class="nav-m">
                        <div class="nav-tit">
                            <h3>车辆管理</h3>
                            <div class="widget-watermark le-nav-d"><i class="le-icon f20 icon-th-list"></i></div>
                        </div>

                        <div class="nav-list">
                            <ul>
                                <li><a href="#">车辆申请</a></li>
                                <li><a href="#">车辆审批</a></li>
                                <li><a href="#">车辆使用查询</a></li>
                                <li><a href="#">驾驶员维护</a></li>
                                <li><a href="#">车辆维护</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="warp col-l-8">
                    <div class="warp-div">
                        <div class="con-layout col-l-77">
                            <div class="warp-tit">
                                <h3 class="tit">车辆概览</h3>
                                <div class="widget-watermark le-b"><i class="le-icon le-tit-icon icon-truck"></i></div>
                            </div>

                            <div class="warp-con">
                                <div class="con-layout">
                                    <div class="model">
                                        <div class="dd-c c-one col-l-3">
                                            <div class="dt-l col-l-6">
                                                <span class="dt-tc">今日</span>
                                                <span class="dt-sp ls2">车辆外出</span>
                                            </div>
                                            <div class="dt-r d-p col-l-4">
                                                <span class="dt-num">8</span>
                                                <span class="dt-dw">次</span>
                                            </div>
                                        </div>
                                        <div class="dd-c c-two col-l-3">
                                            <div class="dt-l col-l-6">
                                                <span class="dt-tc">当前</span>
                                                <span class="dt-sp ls2">可用车辆</span>
                                            </div>
                                            <div class="dt-r d-p col-l-4">
                                                <span class="dt-num">3</span>
                                                <span class="dt-dw">辆</span>
                                            </div>
                                        </div>
                                        <div class="dd-c c-third col-l-3">
                                            <div class="dt-l col-l-6">
                                                <span class="dt-tc">即将</span>
                                                <span class="dt-sp ls2">使用车辆</span>
                                            </div>
                                            <div class="dt-r d-p col-l-4">
                                                <span class="dt-num">3</span>
                                                <span class="dt-dw">辆</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="con-layout">
                                    <div class="model">
                                        <div class="chart-main">
                                            <div class="chart-tit">
                                                <h4>车辆统计</h4>
                                            </div>

                                            <div class="chart-con">
                                                <img src="${ctx}/static/images/charts/cl.jpg" alt="车辆统计" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="con-layout col-r-2">
                            <div class="nav-m">
                                <div class="nav-tit">
                                    <h3>常用申请</h3>
                                    <div class="widget-watermark le-nav-d"><i class="le-icon f20 icon-th-list"></i></div>
                                </div>

                                <div class="nav-list">
                                    <ul>
                                        <li><a href="#">预约用车</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="../fragment/footer.jsp"%>
</body>

</html>