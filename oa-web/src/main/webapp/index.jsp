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
    <p>
        1、使用Spring+SpringMvc+Mybatis
        2、安全框架使用Apache Shiro
        3、前端使用Bootstrap
        4、树使用zTree
        5、工作流使用Activity
        6、日期控件使用DateTime Picker
        7、文件上传使用百度的Webuploader
        8、富文本编辑器使用百度的UEditor
        9、图表插件使用百度的ECharts
        10、Freemarker
    </p>

</body>

</html>