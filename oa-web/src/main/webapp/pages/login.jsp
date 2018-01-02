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
    <%@ include file="common/public-inc.jsp" %>
    <link rel="stylesheet" href="${ctx}/static/css/login.css">
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap/bootstrap.min.css">
    <title>登录</title>
    <script type="text/javascript">
        <!--
        function onRequireReady() {
            require(["domReady"], function (doc) {
                require(["jquery", "layer"], function () {
                    $(document).ready(function () {
                        $("#sub_btn").click(function ($, layer) {
                            $.ajax({
                                url: "${ctx}/mvc/login/mgr/login",
                                type: "POST",
                                data: $("#data_form").serialize(),
                                dataType: "json",
                                success: function (json) {
                                    if (json.status === 200) {
                                        window.location.href = json.toUrl;
                                    }
                                    layer.msg(json.message);
                                }, error: function () {
                                    layer.msg("操作失败，请重试！");
                                }
                            });
                        });

                        $(".form-control").keydown(function (e) {
                            if (e.keyCode === 13) {
                                $("#sub_btn").trigger("click");
                            }
                        });
                    });
                });
            });
        }

        //-->
    </script>
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
                <form id="data_form" action="${ctx}/mvc/login/mgr/login" method="post">
                    <div class="login-group mb20">
                        <div class="group-con">
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <input class="form-control" type="text" placeholder="用户名" name="loginName">
                            </div>
                        </div>
                    </div>
                    <div class="login-group mb20">
                        <div class="group-con">
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-lock"></i>
                                </span>
                                <input class="form-control" type="password" placeholder="密码" name="password">
                            </div>
                        </div>
                    </div>
                    <div class="login-submit  btn-group-lg">
                        <button type="button" id="sub_btn" class="btn btn-primary btn-block">登录</button>
                    </div>
                </form>
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