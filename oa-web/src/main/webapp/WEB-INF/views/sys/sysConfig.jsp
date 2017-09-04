<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@include file="/inc.jsp" %>
<!DOCTYPE html>
<html lang="en">

<%--
  
  Created by User: Zy
  Created Date: 2017/7/27 11:30
--%>

<head>
    <meta charset="UTF-8">
    <%@ include file="/static/common/public-inc.jsp"%>
    <%@ include file="/static/common/js-inc.jsp"%>
    <title>系统设置</title>
</head>

<body>
    <%@ include file="../fragment/header.jsp"%>

    <div id="container" class="container">
        <div class="main-con">
            <div class="con-layout">
                <div class="sidebar col-l-2">
                    <div class="nav-m">
                        <div class="nav-tit">
                            <h3>系统设置</h3>
                            <div class="widget-watermark le-nav-d"><i class="le-icon f20 icon-cogs"></i></div>
                        </div>

                        <div class="nav-list">
                            <ul>
                                <li>
                                    <a href="#">用户管理</a>
                                    <ul class="two-ul">
                                        <li><a href="#">用户管理</a></li>
                                        <li><a href="#">角色管理</a></li>
                                        <li><a href="#">权限管理</a></li>
                                    </ul>
                                </li>
                                <li><a href="#">流程管理</a></li>
                                <li><a href="#">数据字典管理</a></li>
                                <li><a href="#">日志管理</a></li>
                                <li><a href="#">数据库备份</a></li>
                                <li><a href="#">归档管理</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="warp col-l-8">
                    <div class="warp-div">
                        <div class="con-layout col-l-24">
                            <div class="sidebar-nav">
                                <div class="all-user-d">
                                    <div class="user-d active">
                                        <h4>所有用户</h4>
                                        <div class="widget-watermark le-s-c"><i class="le-icon f20 icon-group"></i></div>
                                    </div>
                                </div>

                                <div class="sidebar-con">
                                    <div class="sidebar-role">
                                        <h4>角色</h4>
                                        <ul>
                                            <li>
                                                <a href="#">系统管理员<span class="u-num">1</span></a>
                                            </li>
                                            <li>
                                                <a href="#">管理员<span class="u-num">1</span></a>
                                            </li>
                                            <li>
                                                <a href="#">客服<span class="u-num">5</span></a>
                                            </li>
                                            <li>
                                                <a href="#">Java工程师<span class="u-num">8</span></a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <div class="sidebar-but">
                                    <div class="add-role-but">
                                        <h4>添加新角色</h4>
                                        <div class="widget-watermark le-s-c"><i class="le-icon f20 icon-plus"></i></div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="con-layout pl20 col-l-76">
                            <div class="warp-tit">
                                <h3 class="tit">用户列表</h3>
                                <div class="widget-watermark le-b"><i class="le-icon le-tit-icon icon-user"></i></div>
                            </div>

                            <div class="warp-con">
                                <div class="con-layout">
                                    <div class="model">
                                        <table class="p-table table-omit">
                                            <thead>
                                            <tr>
                                                <th>用户名</th>
                                                <th class="w160">创建日期</th>
                                                <th>登录次数</th>
                                                <th>登录IP</th>
                                                <th class="w60">操作</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach begin="1" end="16">
                                                <tr>
                                                    <td class="tc">xiaxuan@admui_demo</td>
                                                    <td class="tc">2017-03-19 18:08:23</td>
                                                    <td class="tc">43124</td>
                                                    <td class="tc">115.171.185.16</td>
                                                    <td class="tc">
                                                        <a href="#"><i class="icon-edit"></i></a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>

                                            <tfoot>
                                            <tr>
                                                <td colspan="5">
                                                    <div class="paging my-paging">
                                                        <ul class="pagination">
                                                            <li class="disabled"><a href="#"><i class="le-tit-icon icon-angle-left"></i></a></li>
                                                            <li><a href="#">1</a></li>
                                                            <li><a href="#">2</a></li>
                                                            <li><a href="#">3</a></li>
                                                            <li><a href="#">4</a></li>
                                                            <li class="disabled"><a href="#">...</a></li>
                                                            <li><a href="#">51</a></li>
                                                            <li><a><i class="le-tit-icon icon-angle-right"></i></a></li>
                                                        </ul>
                                                    </div>
                                                </td>
                                            </tr>
                                            </tfoot>
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

    <%@ include file="../fragment/footer.jsp"%>
</body>

</html>