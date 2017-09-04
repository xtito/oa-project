<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@include file="/inc.jsp" %>
<!DOCTYPE html>
<html lang="en">

<%--
  
  Created by User: Zy
  Created Date: 2017/7/27 11:17
--%>

<head>
    <meta charset="UTF-8">
    <%@ include file="/static/common/public-inc.jsp"%>
    <%@ include file="/static/common/js-inc.jsp"%>
    <title>公文管理</title>
</head>

<body>
    <%@ include file="../fragment/header.jsp"%>

    <div id="container" class="container">
        <div class="main-con">
            <div class="con-layout">
                <div class="sidebar col-l-2">
                    <div class="nav-m">
                        <div class="nav-tit">
                            <h3>公文管理</h3>
                            <div class="widget-watermark le-nav-d"><i class="le-icon f20 icon-th-list"></i></div>
                        </div>

                        <div class="nav-list">
                            <ul>
                                <li><a href="#">发起公文</a></li>
                                <li><a href="#">我的待办公文</a></li>
                                <li><a href="#">暂存公文</a></li>
                                <li>
                                    <a href="#">内部发文</a>
                                    <ul class="two-ul">
                                        <li><a href="#">我发起的公文</a></li>
                                        <li><a href="#">我参与的公文</a></li>
                                        <li><a href="#">我的已办结公文</a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#">外部收文</a>
                                    <ul class="two-ul">
                                        <li><a href="#">外部收文添加</a></li>
                                        <li><a href="#">我收到的公文</a></li>
                                        <li><a href="#">待传递公文</a></li>
                                        <li><a href="#">已传递公文</a></li>
                                        <li><a href="#">收文查询</a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#">公文归档</a>
                                    <ul class="two-ul">
                                        <li><a href="#">公文归档</a></li>
                                        <li><a href="#">委托设置</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="warp col-l-8">
                    <div class="warp-div">
                        <div class="warp-header">
                            <div class="s-tit">
                                <h3>查询条件</h3>
                                <div class="widget-watermark le-s"><i class="le-icon le-tit-icon icon-search"></i></div>
                            </div>

                            <div class="s-con">
                                <div class="search-d">
                                    <label class="search-lab" for="start_date">日期范围：</label>
                                    <div class="s-in">
                                        <input type="search" id="start_date" class="p-input w120" />
                                    </div>
                                    <label class="search-lab f-g" for="end_date">至</label>
                                    <div class="s-in">
                                        <input type="search" id="end_date" class="p-input w120" />
                                    </div>
                                </div>

                                <div class="search-d">
                                    <label class="search-lab" for="search_tit">标题：</label>
                                    <div class="s-in">
                                        <input type="search" id="search_tit" class="p-input w140" />
                                    </div>
                                </div>

                                <div class="search-d">
                                    <div class="search-but">
                                        <button type="button" class="p-but w60 fl">查询</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="warp-main mt24">
                            <div class="warp-tit">
                                <h3 class="tit">我的待办公文列表</h3>
                                <div class="widget-watermark le-b"><i class="le-icon le-tit-icon icon-list-alt"></i></div>
                            </div>

                            <div class="warp-con">
                                <div class="model">
                                    <table class="p-table table-omit">
                                        <thead>
                                        <tr>
                                            <th class="w80">流水号</th>
                                            <th>标题</th>
                                            <th>公文字号</th>
                                            <th class="w100">公文类型</th>
                                            <th class="w100">承办人</th>
                                            <th class="w110">最后审批时间</th>
                                            <th class="w110">当前步骤</th>
                                            <th class="w160">流程操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach begin="1" end="16" varStatus="i">
                                            <tr>
                                                <td class="tc">${i.index + 10}</td>
                                                <td class="tl">关于xx请示</td>
                                                <td class="tl">XX （2017）${i.index}号</td>
                                                <td class="tc">公文${i.index}</td>
                                                <td class="tc">侯凯</td>
                                                <td class="tc w120">2017-06-16</td>
                                                <td class="tc">结束发文审批</td>
                                                <td class="tc">
                                                    <button type="button" class="p-but w60">查看</button>
                                                    <button type="button" class="p-but w60 ml10">处置</button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <td colspan="8">
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