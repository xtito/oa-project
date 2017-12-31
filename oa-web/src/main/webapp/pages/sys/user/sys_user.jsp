<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc.jsp"%>

<script type="text/javascript">
    <!--
    require(["domReady"], function(doc) {
        require(["sys-user"], function(userJs){
            userJs.initBindEvent();
            userJs.loadDataList();
        });
    });
    //-->
</script>

<div class="layui-tab layui-tab-brief">
    <jsp:include page="inner_user_menu.jsp">
        <jsp:param name="active" value="1" />
    </jsp:include>

    <div class="ito-home-section">
        <div class="ito-home-address">
            <div class="ito-home-location">
                <ol class="ito-breadcrumb">
                    <li class="location-item">
                        <a href="javascript:;" data-trigger-class="trigger-home">
                            <i class="ito ito-home"></i><span>首页</span>
                        </a>
                    </li>
                    <li class="location-item">
                        <i class="ito ito-user-list"></i><span>用户管理</span>
                    </li>
                </ol>
            </div>
        </div>
    </div>

    <div class="layui-body layui-tab-content site-demo site-demo-body">
        <div class="layui-tab-item layui-show">
            <div id="inner_main" class="layui-main">
                <div class="ito-inner-con">
                    <div class="ito-col-panel">
                        <div class="ito-panel">
                            <div class="panel-heading">
                                <div class="search-title">
                                    <span class="ito ito-search fl"></span>
                                    <h3 class="panel-title pro-title">查询条件</h3>
                                    <div class="s-icon xz title-icon"><span class="ito ito-chevron-up"></span></div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <form id="search_form" class="layui-form search-form" method="post">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <td class="tr w110">
                                                <label class="layui-form-label w120" for="user_status">用户状态</label>
                                            </td>
                                            <td class="tl w150">
                                                <div class="layui-input-block in-block w140">
                                                    <select id="user_status" name="status">
                                                        <option value=""></option>
                                                        <option value="1">正常</option>
                                                        <option value="2">禁用</option>
                                                        <option value="3">锁定</option>
                                                    </select>
                                                </div>
                                            </td>

                                            <td class="tr w110">
                                                <label class="layui-form-label w120" for="login_user">登录名</label>
                                            </td>
                                            <td class="tl w150">
                                                <div class="layui-input-block in-block w140">
                                                    <input type="text" id="login_user" class="layui-input" name="loginName" placeholder="用户登录名" autocomplete="off">
                                                </div>
                                            </td>

                                            <td class="tr w110">
                                                <label class="layui-form-label w120" for="email">Email</label>
                                            </td>
                                            <td class="tl w150">
                                                <div class="layui-input-block in-block w140">
                                                    <input type="text" id="email" class="layui-input" name="email" placeholder="用户Email" autocomplete="off">
                                                </div>
                                            </td>

                                            <td class="tr w110">
                                                <label class="layui-form-label w120" for="phone">手机号</label>
                                            </td>
                                            <td class="tl w150">
                                                <div class="layui-input-block in-block w140">
                                                    <input type="text" id="phone" name="phone" class="layui-input" placeholder="手机号" autocomplete="off">
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="tr w110">
                                                <label class="layui-form-label w120" for="user_name">用户姓名</label>
                                            </td>
                                            <td class="tl w150">
                                                <div class="layui-input-block in-block w140">
                                                    <input type="text" id="user_name" name="nickname" class="layui-input" placeholder="用户昵称" autocomplete="off">
                                                </div>
                                            </td>

                                            <td class="tr w110">
                                                <label class="layui-form-label w120" for="begin_time">创建日期</label>
                                            </td>
                                            <td class="tl" colspan="4">
                                                <div class="layui-input-block in-block w140 fl">
                                                    <input type="text" id="begin_time" name="begin" class="layui-input" placeholder="起始日期" autocomplete="off">
                                                </div>
                                                <label class="layui-form-label w60 fl tc" for="end_time">至</label>
                                                <div class="layui-input-block in-block w140 fl">
                                                    <input type="text" id="end_time" name="end" class="layui-input" placeholder="结束日期" autocomplete="off">
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="tl pl40" colspan="8">
                                                <button type="button" id="search_btn" class="layui-btn mr20" data-type="reload">查询</button>
                                                <button type="reset" class="layui-btn mr20">重置</button>
                                                <button type="button" id="add_btn" class="layui-btn">新建</button>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="ito-col-panel mt20">
                        <div class="ito-panel">
                            <div class="panel-heading">
                                <div class="search-title">
                                    <span class="ito ito-list-icon fl"></span>
                                    <h3 class="panel-title pro-title">用户列表</h3>
                                    <div class="s-icon xz title-icon"><span class="ito ito-chevron-up"></span></div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <table class="layui-hide" id="data_table" lay-filter="operation"></table>

                                <%@ include file="include_user_status.jsp"%>
                            </div>
                        </div>
                    </div>

                    <div id="operation_con" style="display: none">
                        <a class="operation-btn" lay-event="detail" title="详情"><i class="ito ito-details mr10"></i></a>
                        <a class="operation-btn" lay-event="edit" title="编辑"><i class="ito ito-edit mr10"></i></a>
                        <a class="operation-btn" lay-event="del" title="删除"><i class="ito ito-delete"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>