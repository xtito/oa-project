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
    <title>主页</title>
    <script type="text/javascript">
        function jumpLoginPage() {
            window.location.href = "${ctx}/mvc/loginController/manager/page";
        }
    </script>
</head>

<body>

    <h1>我是首页</h1>

</body>

</html>