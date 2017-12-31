<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc.jsp" %>
<!DOCTYPE html>
<html lang="en">

<%--
  
  Created by User: Zy
  Created Date: 2017/10/6 12:26
--%>

<head>
    <meta charset="UTF-8">
    <%@ include file="common/public-inc.jsp"%>
    <link rel="stylesheet" href="${ctx}/static/css/login.css">
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap/bootstrap.min.css">
    <title>登录</title>
</head>

<body class="login-body">

<header>
    <div class="login-header">
        <div class="header-title">
            <h3>OA办公平台</h3>
        </div>
    </div>
</header>

<div class="login-con">
    <div class="login-outer">
        <div class="login-wrap">
            <div class="title"></div>
            <div class="login-content">
                <div class="login-group mb20">
                    <div class="group-con">
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon">
                                <i class="glyphicon glyphicon-user"></i>
                            </span>
                            <input class="form-control" type="text" placeholder="用户名" name="icUser.pwd">
                        </div>
                    </div>
                </div>
                <div class="login-group mb20">
                    <div class="group-con">
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon">
                                <i class="glyphicon glyphicon-lock"></i>
                            </span>
                            <input class="form-control" type="password" placeholder="密码" name="icUser.pwd">
                        </div>
                    </div>
                </div>
                <div class="login-submit  btn-group-lg">
                    <button type="button" class="btn btn-primary btn-block">登录</button>
                </div>
            </div>
        </div>
    </div>
</div>

<footer>
    <div class="login-footer">
        <div class="footer-desc">
            <h4>Copyright © XXX, Inc. All rights reserved.</h4>
        </div>
    </div>
</footer>

</body>

</html>