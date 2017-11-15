<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@include file="/static/include/inc.jsp" %>
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
                <%--loadContent("${ctx}/static/include/home.jsp");--%>
//                resize();
            });
        }

        function loadContent(url, param) {
            require(["jquery"], function ($) {
                $("#target_frame").load(url, param);
            });
        }
        //-->
    </script>
</head>

<body>

<div class="ito-container">
    <div class="ito-top-bar">
        <div class="console-top-bar">
            <div class="top-bar-wrap">
                <div class="top-bar-head">
                    <a class="btn-logo" href="#">
                        <img src="${ctx}/static/images/logo/white/logo.png"/>
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

    <div class="ito-work-body">
        <%@ include file="./static/include/sidebar-nav.jsp"%>

        <div class="ito-work-min">
            <iframe id="target_frame" name="rightFrame" width="100%" height="765" frameborder="0" src="${ctx}/static/include/home.jsp">加载中...</iframe>
        </div>
    </div>
</div>

</body>

</html>