<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@include file="/inc.jsp" %>
<!DOCTYPE html>
<html lang="en">

<%--
  
  Created by User: Zy
  Created Date: 2017/7/28 11:17
--%>

<head>
    <meta charset="UTF-8">
    <%@ include file="/static/common/public-inc.jsp"%>
    <%@ include file="/static/common/js-inc.jsp"%>
    <title>通知公告</title>
    <script type="text/javascript">
        function addUI() {
            window.location.href = "${ctx}/pages/notice/addNotice.jsp";
        }
    </script>

</head>

<body>
    <%@ include file="../fragment/header.jsp"%>

    <div id="container" class="container">
        <div class="main-con">
            <div class="con-layout">
                <div class="sidebar col-l-2">
                    <div class="nav-m">
                        <div class="nav-tit">
                            <h3>通知公告</h3>
                            <div class="widget-watermark le-nav-d"><i class="le-icon f20 icon-th-list"></i></div>
                        </div>

                        <div class="nav-list">
                            <ul>
                                <li><a>全部公告</a></li>
                                <li><a>已读公告</a></li>
                                <li><a>未读公告</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="nav-m mt20">
                        <div class="nav-tit">
                            <h3>公告类型</h3>
                            <div class="widget-watermark le-nav-d"><i class="le-icon f20 icon-th-list"></i></div>
                        </div>

                        <div class="nav-list">
                            <ul>
                                <li><a>类型一</a></li>
                                <li><a>类型二</a></li>
                                <li><a>类型三</a></li>
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
                                        <button type="button" class="p-but w60 fl ml20" onclick="addUI()">新建</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="warp-main mt24">
                            <div class="warp-tit">
                                <h3 class="tit">通知公告列表</h3>
                                <div class="widget-watermark le-b"><i class="le-icon le-tit-icon icon-bullhorn"></i></div>
                            </div>

                            <div class="warp-con">
                                <div class="model">
                                    <table class="p-table table-omit">
                                        <tbody>
                                        <c:forEach begin="1" end="16">
                                            <tr>
                                                <td class="tl">美术与设计学院2017年上半年优秀团员作入党积极分子拟推荐对象公示</td>
                                                <td class="tc w120">2017-06-16</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <td colspan="2">
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