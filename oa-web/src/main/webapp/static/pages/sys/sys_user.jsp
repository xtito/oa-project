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
    <%@ include file="/static/common/public-inc.jsp"%>
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

<div class="main-body">
    <div class="section-main">
        <div class="home">
            <div class="home-ads">
                <div class="ads-path col-lg-12">
                    <ol class="breadcrumb">
                        <li><i class="fa fa-home"></i><a href="${ctx}/static/include/home.jsp">首页</a></li>
                        <li><i class="fa fa-user"></i>用户管理</li>
                    </ol>
                </div>
            </div>
        </div>

        <div class="main-content content">
            <div class="inner-con">
                <div class="col-md-12 pn">
                    <div class="col-panel">
                        <div class="panel pro-panel">
                            <div class="panel-heading pro-heading">
                                <div class="search-title">
                                    <span class="fa fa-search-plus fl"></span>
                                    <h3 class="panel-title pro-title">查询条件</h3>
                                    <div class="s-icon xz title-icon"><span class="fa fa-chevron-up"></span></div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <table class="table" style="border: 0 !important;">
                                    <tbody>
                                    <tr>
                                        <td class="tr"><label>用户</label></td>
                                        <td><input type="text" class="form-control"/></td>
                                        <td class="tr"><label>用户</label></td>
                                        <td><input type="text" class="form-control"/></td>
                                        <td class="tr"><label>用户</label></td>
                                        <td><input type="text" class="form-control"/></td>
                                    </tr>
                                    <tr>
                                        <td class="tr"><label>用户</label></td>
                                        <td><input type="text" class="form-control"/></td>
                                        <td class="tr"><label>用户</label></td>
                                        <td><input type="text" class="form-control"/></td>
                                        <td class="tr"><label>用户</label></td>
                                        <td><input type="text" class="form-control"/></td>
                                    </tr>
                                    <tr>
                                        <td colspan="6">
                                            <button type="button" class="but but-primary mr20">查询</button>
                                            <button type="button" class="but but-primary mr20">重置</button>
                                            <button type="button" class="but but-primary" onclick="addUI()">新建</button>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-12 pn">
                    <div class="col-panel">
                        <div class="panel pro-panel">
                            <div class="panel-heading pro-heading">
                                <div class="search-title">
                                    <div class="s-icon fl"><span class="fa fa-list"></span></div>
                                    <h3 class="panel-title pro-title">系统用户列表</h3>
                                    <div class="title-icon">
                                        <div class="s-icon fl"><span class="fa fa-refresh "></span></div>
                                        <div class="s-icon xz fl ml10"><span class="fa fa-chevron-up "></span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="panel pro-panel">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>序号</th>
                                            <th>登录账号</th>
                                            <th>姓名</th>
                                            <th>Email</th>
                                            <th>所属部门</th>
                                            <th>用户状态</th>
                                            <th>锁定时间</th>
                                            <th>创建时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <tr><td>1</td><td>张三</td><td>x000001</td><td>在岗</td><td>研发布</td><td></td></tr>
                                        <tr><td>1</td><td>张三</td><td>x000001</td><td>在岗</td><td>研发布</td><td></td></tr>
                                        <tr><td>1</td><td>张三</td><td>x000001</td><td>在岗</td><td>研发布</td><td></td></tr>
                                        <tr><td>1</td><td>张三</td><td>x000001</td><td>在岗</td><td>研发布</td><td></td></tr>
                                        <tr><td>1</td><td>张三</td><td>x000001</td><td>在岗</td><td>研发布</td><td></td></tr>
                                        <tr><td>1</td><td>张三</td><td>x000001</td><td>在岗</td><td>研发布</td><td></td></tr>
                                        <tr><td>1</td><td>张三</td><td>x000001</td><td>在岗</td><td>研发布</td><td></td></tr>
                                        <tr><td>1</td><td>张三</td><td>x000001</td><td>在岗</td><td>研发布</td><td></td></tr>
                                        <tr><td>1</td><td>张三</td><td>x000001</td><td>在岗</td><td>研发布</td><td></td></tr>
                                        <tr><td>1</td><td>张三</td><td>x000001</td><td>在岗</td><td>研发布</td><td></td></tr>
                                        </tbody>
                                    </table>
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