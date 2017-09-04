<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@include file="/inc.jsp" %>
<!DOCTYPE html>
<html lang="en">

<%--
  
  Created by User: Zy
  Created Date: 2017/7/28 11:23
--%>

<head>
    <meta charset="UTF-8">
    <%@ include file="/static/common/public-inc.jsp"%>
    <%@ include file="/static/common/js-inc.jsp"%>
    <title>工作计划</title>
</head>

<body>
    <%@ include file="../fragment/header.jsp"%>

    <div id="container" class="container">
        <div class="main-con">
            <div class="con-layout">
                <div class="sidebar col-l-2">
                    <div class="nav-m">
                        <div class="nav-tit">
                            <h3>工作计划</h3>
                            <div class="widget-watermark le-nav-d"><i class="le-icon f20 icon-th-list"></i></div>
                        </div>

                        <div class="nav-list">
                            <ul>
                                <li><a href="#">我的工作概要</a></li>
                                <li><a href="#">我的本周工作</a></li>
                                <li><a href="#">我的本月工作计划</a></li>
                                <li>
                                    <a href="#">本部门周计划</a>
                                    <ul class="two-ul">
                                        <li><a href="#">计划中</a></li>
                                        <li><a href="#">进行中</a></li>
                                        <li><a href="#">已完成</a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#">部门月计划</a>
                                    <ul class="two-ul">
                                        <li><a href="#">计划中</a></li>
                                        <li><a href="#">进行中</a></li>
                                        <li><a href="#">已完成</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="warp col-l-8">
                    <div class="warp-div">
                        <div class="con-layout">
                            <div class="warp-con border-none">
                                <div class="model pr col-l-5">
                                    <div class="chart-main">
                                        <div class="chart-tit">
                                            <h4>我的本周工作</h4>
                                        </div>

                                        <div class="chart-con">
                                            <img src="${ctx}/static/images/charts/bz.jpg" alt="我的本周工作" style="width: 410px;height:200px;" />
                                        </div>
                                    </div>
                                </div>

                                <div class="model pl col-l-5">
                                    <div class="chart-main">
                                        <div class="chart-tit">
                                            <h4>我的本月工作</h4>
                                        </div>

                                        <div class="chart-con">
                                            <img src="${ctx}/static/images/charts/by.jpg" alt="我的本月工作" style="width: 410px;height:200px;" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="warp-main mt24">
                            <div class="warp-tit">
                                <h3 class="tit">我的待完成工作</h3>
                                <div class="widget-watermark le-b"><i class="le-icon le-tit-icon icon-list-ol"></i></div>
                            </div>

                            <div class="warp-con">
                                <div class="model">
                                    <table class="p-table table-omit">
                                        <thead>
                                        <tr>
                                            <th class="w80">序号</th>
                                            <th class="w110">计划完成时间</th>
                                            <th class="w100">主办人</th>
                                            <th>工作事项</th>
                                            <th class="w140">状态</th>
                                            <th>备注</th>
                                            <th class="w80">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach begin="1" end="8" varStatus="i">
                                            <tr>
                                                <td class="tc">${i.index + 10}</td>
                                                <td class="tc">2017-06-16</td>
                                                <td class="tc">侯凯</td>
                                                <td class="tl">关于xx请示</td>
                                                <c:if test="${i.index == 1}">
                                                    <td class="tc">已完成（100%）</td>
                                                </c:if>
                                                <c:if test="${i.index == 2}">
                                                    <td class="tc green">进行中（80%）</td>
                                                </c:if>
                                                <c:if test="${i.index == 3}">
                                                    <td class="tc red">进行中（40%）</td>
                                                </c:if>
                                                <c:if test="${i.index != 1 && i.index != 2 && i.index != 3}">
                                                    <td class="tc"></td>
                                                </c:if>
                                                <td class="tc">结束发文审批</td>
                                                <td class="tc">
                                                    <button type="button" class="p-but w60">更新</button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <td colspan="7">
                                                <div class="paging my-paging">
                                                    <ul class="pagination">
                                                        <li class="disabled"><a href="#"><i class="le-tit-icon icon-angle-left"></i></a></li>
                                                        <li><a href="#">1</a></li>
                                                        <li><a href="#">2</a></li>
                                                        <li><a href="#">3</a></li>
                                                        <li><a href="#">4</a></li>
                                                        <li class="disabled"><a href="#">...</a></li>
                                                        <li><a href="#">51</a></li>
                                                        <li><a><i class="le-tit-icon icon-angle-right"></i></a></li>
                                                    </ul>
                                                </div>
                                            </td>
                                        </tr>
                                        </tfoot>
                                    </table>
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