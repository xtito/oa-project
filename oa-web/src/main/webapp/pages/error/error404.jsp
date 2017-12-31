<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@include file="/pages/common/inc.jsp" %>
<!DOCTYPE html>
<html lang="en">

<%--
  
  Created by User: Zy
  Created Date: 2017/8/10 10:35
--%>

<head>
    <meta charset="UTF-8">
    <%@ include file="/pages/common/public-inc.jsp"%>
    <title>404找不到页面</title>

    <style type="text/css">
        html, body {
            padding: 0;
            margin: 0;
            text-align: center;
            font-family: "微软雅黑", serif;
            background-color: #FFF;
        }

        .errorInfo {
            width: 780px;
            margin: 180px auto;
            text-align: left;
        }

        .errorInfo img {
            float: left;
        }

        .time {
            font-weight: 600;
            color: #af0000;
            padding: 0 5px;
        }

        .info-text {
            clear: both;
            padding:20px
        }
    </style>
</head>

<body>

    <div class="errorInfo">
        <img src="${ctx}/static/images/error/404-zsp.jpg" width="362" height="282"/>
        <img src="${ctx}/static/images/error/404-info.jpg" width="324" height="220"/>
    </div>
    <div class="info-text">
        <span>找不到页面，将在</span>
        <span id="time" class="time">5</span>
        <span>秒后</span>
        <a href="${ctx}/">回到首页</a>
        <span>，或者点击返回</span>
        <a href="javascript:history.go(-1);">返回上一页</a>
    </div>

</body>

</html>

<script type="text/javascript">
    <!--
    var inter = setInterval(function () {
        var time = parseInt(document.getElementById("time").innerText);
        if (time === 0) {
            clearInterval(inter);
            top.location.href = "../../";
            return;
        }
        document.getElementById("time").innerText = --time + "";
    }, 1000);
    //-->
</script>