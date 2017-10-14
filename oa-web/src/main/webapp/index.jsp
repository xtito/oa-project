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
    <%@ include file="static/common/public-inc.jsp"%>
    <title>主页</title>
    <script type="text/javascript">
        <!--
        // require.js加载完时调用main.js，main.js调用此方法
        function onRequireReady() {
            require(["domReady"], function(doc) {
            });
        }
        //-->
    </script>
</head>

<body>

    <div class="container" style="border: 1px solid red">
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

</body>

</html>