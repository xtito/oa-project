<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@include file="/inc.jsp" %>
<!DOCTYPE html>
<html lang="en">

<%--
  
  Created by User: Zy
  Created Date: 2017/7/27 11:22
--%>

<head>
    <meta charset="UTF-8">
    <%@ include file="/static/common/public-inc.jsp"%>
    <%@ include file="/static/common/js-inc.jsp"%>
    <title>人员管理</title>
</head>

<body>
    <%@ include file="../fragment/header.jsp"%>

    <div id="container" class="container">
        <div class="main-con">
            <div class="con-layout">
                <div class="sidebar col-l-2">
                    <div class="nav-m">
                        <div class="nav-tit">
                            <h3>人员管理</h3>
                            <div class="widget-watermark le-nav-d"><i class="le-icon f20 icon-th-list"></i></div>
                        </div>

                        <div class="nav-list">
                            <ul>
                                <li><a>概览</a></li>
                                <li><a>人员查询</a></li>
                                <li><a>休假审批</a></li>
                                <li><a>我的申请</a></li>
                                <li><a>月度查询</a></li>
                                <li><a>年度查询</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="warp col-l-8">
                    <div class="warp-div">
                        <div class="con-layout col-l-77">
                            <div class="warp-tit">
                                <h3 class="tit">摘要</h3>
                                <div class="widget-watermark le-b"><i class="le-icon le-tit-icon icon-bar-chart"></i></div>
                            </div>

                            <div class="warp-con">
                                <div class="con-layout">
                                    <div class="model col-l-6">
                                        <div class="chart-main">
                                            <div class="chart-tit">
                                                <h4>本周在岗</h4>
                                            </div>

                                            <div class="chart-con">
                                                <img src="${ctx}/static/images/charts/zg.png" alt="本周在岗" style="width: 360px;height:200px;" />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="model col-l-4">
                                        <div class="chart-main">
                                            <div class="chart-tit">
                                                <h4>在位情况</h4>
                                            </div>

                                            <div class="chart-con">
                                                <img src="${ctx}/static/images/charts/zw.jpg" style="width: 240px;height:200px;" alt="在位情况" />
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="con-layout">
                                    <div class="model col-l-6">
                                        <div class="chart-main">
                                            <div class="chart-tit">
                                                <h4>休假统计</h4>
                                            </div>

                                            <div class="chart-con">
                                                <img src="${ctx}/static/images/charts/xj.jpg" style="width: 360px;height:200px;" alt="休假统计" />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="model col-l-4">
                                        <div class="chart-main">
                                            <div class="chart-tit">
                                                <h4>外出统计</h4>
                                            </div>

                                            <div class="chart-con">
                                                <img src="${ctx}/static/images/charts/wc.jpg" style="width: 240px;height:200px;" alt="外出统计" />
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
                                        <li><a href="#">外出申请</a></li>
                                        <li><a href="#">休假申请</a></li>
                                        <li><a href="#">预约用车</a></li>
                                    </ul>
                                </div>
                            </div>

                            <div class="nav-m mt20">
                                <div class="nav-tit">
                                    <h3>今日值班</h3>
                                    <div class="widget-watermark le-nav-d"><i class="le-icon f20 icon-th-list"></i></div>
                                </div>

                                <div class="nav-list">
                                    <div class="dt-b">
                                        <div class="dt-c">
                                            <span class="dt-sp">电话值班：</span>
                                            <span class="dt-sp ls2">张三</span>
                                        </div>
                                        <div class="dt-c">
                                            <span class="dt-sp">邮件值班：</span>
                                            <span class="dt-sp ls2">李四</span>
                                        </div>
                                        <div class="dt-c">
                                            <span class="dt-sp">门卫值班：</span>
                                            <span class="dt-sp ls2">王某某</span>
                                        </div>
                                        <div class="dt-c">
                                            <span class="dt-sp">电源值班：</span>
                                            <span class="dt-sp ls2">张某某</span>
                                        </div>
                                        <div class="dt-c">
                                            <span class="dt-sp">视频值班：</span>
                                            <span class="dt-sp ls2">李某某</span>
                                        </div>
                                    </div>
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