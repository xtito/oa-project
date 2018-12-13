<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc.jsp" %>

<%--
  
  Created by User: Zy
  Created Date: 2017/11/25 17:23
--%>

<script type="text/javascript">
    <!--
    require(["domReady"], function(doc) {
        require(["sys-department"], function(deptJs){
            deptJs.initBindEvent();
            deptJs.loadDataList();
        });
    });
    //-->
</script>

<div class="layui-tab layui-tab-brief">
    <jsp:include page="workflow_form_list_menu.jsp">
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
                        <i class="ito ito-department"></i><span>部门管理</span>
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
                                                <label class="layui-form-label w120" for="dept_name">部门名称</label>
                                            </td>
                                            <td class="tl w200">
                                                <div class="layui-input-block in-block w160">
                                                    <input type="text" id="dept_name" class="layui-input" name="title"
                                                           placeholder="请输入部门名称" autocomplete="off">
                                                </div>
                                            </td>

                                            <td class="tl pl40">
                                                <button type="button" id="search_btn" class="layui-btn mr20" data-type="reload">
                                                    查询
                                                </button>
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
                                    <h3 class="panel-title pro-title">部门列表</h3>
                                    <div class="s-icon xz title-icon"><span class="ito ito-chevron-up"></span></div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <table class="layui-hide" id="data_table" lay-filter="operation"></table>
                            </div>
                        </div>
                    </div>

                    <div id="operation_con" style="display: none">
                        <a class="operation-btn" lay-event="edit"><i class="ito ito-edit mr10"></i></a>
                        <a class="operation-btn" lay-event="del"><i class="ito ito-delete"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>