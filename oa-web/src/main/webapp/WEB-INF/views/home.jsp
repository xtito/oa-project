<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/inc.jsp"%>
<!DOCTYPE html>
<html lang="en">

<%--

  Created by User: Zy
  Created Date: 2017/7/25 11:15
--%>

<head>
    <meta charset="UTF-8">
    <%@ include file="/static/common/public-inc.jsp"%>
    <%@ include file="/static/common/js-inc.jsp"%>
    <title>OA办公平台首页</title>
</head>

<body>
    <%@ include file="fragment/header.jsp"%>

    <div id="container" class="container">
        <div class="main-con">
            <div class="con-layout">
                <div class="module col-l-5">
                    <div class="m-con">
                        <div class="m-lb-d">
                            <img src="${ctx}/static/images/lb/u15.jpg" alt="轮播" width="518" height="300" />
                        </div>
                    </div>
                </div>

                <div class="module col-l-5">
                    <div class="m-con">
                        <div class="panel-tit">
                            <h3 class="tit">部门动态</h3>
                            <div class="widget-watermark le-b"><i class="le-icon le-tit-icon icon-bullhorn"></i></div>
                        </div>

                        <div class="panel-con panel-d">
                            <div class="panel">
                                <div class="col col-l-6">
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
                                            <span class="dt-sp">机房值班：</span>
                                            <span class="dt-sp ls2">张某某</span>
                                        </div>
                                        <div class="dt-c">
                                            <span class="dt-sp">视频值班：</span>
                                            <span class="dt-sp ls2">李某某</span>
                                        </div>

                                        <div class="widget-watermark le-c"><i class="le-icon le-calendar icon-calendar"></i></div>
                                    </div>
                                </div>

                                <div class="col col-l-4">
                                    <div class="dt-d zg">
                                        <span class="dt-sp">在岗：</span>
                                        <span class="dt-num">3333</span>
                                        <span class="dt-sp">人</span>
                                        <div class="widget-watermark le-u"><i class="le-icon le-users icon-group"></i></div>
                                    </div>
                                    <div class="dt-d wc">
                                        <span class="dt-sp">外出：</span>
                                        <span class="dt-num">3</span>
                                        <span class="dt-sp">人</span>
                                        <div class="widget-watermark le-u"><i class="le-icon le-users icon-group"></i></div>
                                    </div>
                                    <div class="dt-d xj">
                                        <span class="dt-sp">休假：</span>
                                        <span class="dt-num">33</span>
                                        <span class="dt-sp">人</span>
                                        <div class="widget-watermark le-u"><i class="le-icon le-users icon-group"></i></div>
                                    </div>
                                </div>
                            </div>

                            <div class="panel">
                                <div class="col col-l">
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
                                            <span class="dt-tc">当前</span>
                                            <span class="dt-sp ls2">会议室</span>
                                        </div>
                                        <div class="dt-r col-l-4">
                                            <span class="dt-state greenyellow">空闲</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="con-layout">
                <div class="module col-l-5">
                    <div class="m-con">
                        <div class="panel-tit">
                            <h3 class="tit">待办事项</h3>
                            <div class="more"><a href="">更多</a></div>
                            <div class="widget-watermark le-b"><i class="le-icon le-tit-icon icon-list-ol"></i></div>
                        </div>

                        <div class="panel-con h282">
                            <table class="p-table table-omit">
                                <thead>
                                <tr>
                                    <th class="w110">时间</th>
                                    <th class="w100">事项类型</th>
                                    <th>工作事项</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach begin="1" end="6" varStatus="i">
                                    <tr>
                                        <td class="tc">2017-3-19</td>
                                        <c:if test="${i.index % 2 == 0}">
                                            <td class="tc fb carC">车辆管理</td>
                                        </c:if>
                                        <c:if test="${i.index % 2 != 0}">
                                            <td class="tc fb docC">公文</td>
                                        </c:if>
                                        <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="module col-l-5">
                    <div class="m-con">
                        <div class="panel-tit">
                            <h3 class="tit">热门文档资源</h3>
                            <div class="more"><a href="">更多</a></div>
                            <div class="widget-watermark le-b"><i class="le-icon le-tit-icon icon-book"></i></div>
                        </div>

                        <div class="panel-con h282">
                            <table class="p-table table-omit">
                                <thead>
                                <tr>
                                    <th class="w110">时间</th>
                                    <th class="w100">文档类型</th>
                                    <th>文档名称</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach begin="1" end="6" varStatus="i">
                                    <tr>
                                        <td class="tc">2017-3-19</td>
                                        <c:if test="${i.index % 2 == 0}">
                                            <td class="tc fb carC">车辆管理</td>
                                        </c:if>
                                        <c:if test="${i.index % 2 != 0}">
                                            <td class="tc fb docC">公文</td>
                                        </c:if>
                                        <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="fragment/footer.jsp"%>
</body>

</html>