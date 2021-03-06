<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc.jsp" %>
<!DOCTYPE html>
<html lang="en">

<%--

  Created by User: Zy
  Created Date: 2017/8/10 8:49
--%>

<head>
    <meta charset="UTF-8">
    <%@ include file="../common/public-inc.jsp" %>
    <link rel="stylesheet" href="${ctx}/static/css/layout.css" />
    <link rel="stylesheet" href="${ctx}/static/css/oa-styleCopy.css" />
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap/bootstrap.min.css" />
    <title>主页</title>
    <script type="text/javascript">
        <!--
        // require.js加载完时调用main.js，main.js调用此方法
        function onRequireReady() {
            require(["domReady"], function (doc) {
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
                        <li><i class="ito ito-home"></i><a href="${ctx}/pages/include/home.jsp">首页</a></li>
                        <li><i class="ito ito-dian-nao"></i>Dashboard</li>
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
                                    <span class="ito ito-search fl"></span>
                                    <h3 class="panel-title pro-title">查询条件</h3>
                                    <div class="s-icon xz title-icon"><span class="ito ito-chevron-up"></span></div>
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
                                            <button class="but but-primary mr20">查询</button>
                                            <button class="but but-primary mr20">重置</button>
                                            <button class="but but-primary">新建</button>
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
                                    <div class="s-icon fl"><span class="ito ito-list-icon"></span></div>
                                    <h3 class="panel-title pro-title">数据列表</h3>
                                    <div class="title-icon">
                                        <div class="s-icon fl"><span class="ito ito-refresh "></span></div>
                                        <div class="s-icon xz fl ml10"><span class="ito ito-chevron-up "></span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="panel pro-panel">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                        <tr><th>序号</th><th>姓名</th><th>编号</th><th>状态</th><th>部门</th><th>操作</th></tr>
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

                                    <div>
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
                                    <div>
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