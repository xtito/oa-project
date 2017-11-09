<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/static/include/inc.jsp" %>

<!DOCTYPE html>
<html lang="en">

<%--
  
  Created by User: Zy
  Created Date: 2017/11/9 21:35
--%>

<head>
    <meta charset="UTF-8">
    <%@ include file="/static/common/public-inc.jsp" %>
    <title>添加用户页面</title>
    <script type="text/javascript">
        <!--
        // require.js加载完时调用main.js，main.js调用此方法
        function onRequireReady() {
            require(["domReady", "jquery"], function (doc, $) {


            });
        }

        function saveUser() {
            require(["jquery"], function($) {
                $.ajax({
                    url: "${ctx}/mvc/sysUser/mgr/save/user",
                    type: "POST",
                    data: $("#data_form").serialize(),
                    dataType: "json",
                    success: function (json) {
                        if (json.success) {

                        } else {

                        }
                    }, error: function () {
                        alert("");
                    }
                });
            });
        }
        //-->
    </script>
</head>

<body>

<div class="main-body">
    <div class="section-main">
        <div class="home">
            <div class="home-ads">
                <div class="ads-path col-lg-12">
                    <ol class="breadcrumb">
                        <li><i class="fa fa-home"></i><a href="${ctx}/static/include/home.jsp">首页</a></li>
                        <li><i class="fa fa-user"></i><a href="${ctx}/static/pages/sys/sys_user.jsp">用户管理</a></li>
                        <li><i class="fa fa-plus-circle"></i>用户管理</li>
                    </ol>
                </div>
            </div>
        </div>

        <div class="main-content content">
            <div class="inner-con">
                <div class="col-md-12 pn">
                    <div class="col-panel">
                        <div class="panel pro-panel">
                            <div class="panel-body">
                                    <div class="panel panel-info">
                                        <div class="panel-heading">
                                            添加用户
                                        </div>
                                        <div class="panel-body">
                                            <form id="data_form" role="form">
                                                <div class="form-group">
                                                    <label for="user_name">用户名</label>
                                                    <input id="user_name" class="form-control" type="text" placeholder="请设置用户名">
                                                    <p class="help-block">Help text here.</p>
                                                </div>
                                                <div class="form-group">
                                                    <label for="user_phone">手机号</label>
                                                    <input id="user_phone" class="form-control" type="text" placeholder="可用于登录或找回密码">
                                                    <p class="help-block">Help text here.</p>
                                                </div>
                                                <div class="form-group">
                                                    <label for="valid_code">验证码</label>
                                                    <input id="valid_code" class="form-control" type="text" placeholder="请输入验证码">
                                                    <p class="help-block">Help text here.</p>
                                                </div>
                                                <div class="form-group">
                                                    <label for="user_pwd">密码</label>
                                                    <input id="user_pwd" class="form-control" type="text" placeholder="请设置登录密码">
                                                    <p class="help-block">Help text here.</p>
                                                </div>
                                                <div class="form-group">
                                                    <label>Text area</label>
                                                    <textarea class="form-control" rows="3"></textarea>
                                                </div>
                                            </form>

                                            <div>
                                                <button type="button" class="but but-primary" onclick="saveUser()">保存</button>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

</body>

</html>