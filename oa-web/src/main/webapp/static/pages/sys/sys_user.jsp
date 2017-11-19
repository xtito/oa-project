<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/static/include/inc.jsp"%>

<!DOCTYPE html>
<html lang="en">

<%--

  Created by User: Zy
  Created Date: 2017/11/9 20:26
--%>

<head>
    <meta charset="UTF-8">
    <title>用户管理</title>
    <script type="text/javascript">
        <!--
        // require.js加载完时调用main.js，main.js调用此方法
        function onRequireReady() {
            require(["domReady", "jquery"], function(doc, $) {


            });
        }

        // 添加用户页面
        function addUI() {
            window.location.href = "${ctx}/mvc/sysUser/mgr/addUI";
        }
        //-->
    </script>

</head>

<body>

<div class="layui-body">

    <h1>我是添加用户页面</h1>

</div>

</body>

</html>