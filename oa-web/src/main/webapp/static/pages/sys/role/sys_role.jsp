<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/static/include/inc.jsp"%>

<script type="text/javascript">
    <!--
    require(["domReady"], function(doc) {
        require(["sys-role"], function(roleJs){
            roleJs.initBindEvent();
            roleJs.loadDataList();
        });
    });
    //-->
</script>

<div class="ito-inner-body">
    <div class="ito-home-section">
        <div class="ito-home-address">
            <div class="ito-home-location">
                <ol class="ito-breadcrumb">
                    <li class="location-item">
                        <a href="javascript:;" data-url="${ctx}/static/include/home.jsp">
                            <i class="ito ito-home"></i><span>首页</span>
                        </a>
                    </li>
                    <li class="location-item">
                        <i class="ito ito-role-list"></i><span>角色管理</span>
                    </li>
                </ol>
            </div>
        </div>
    </div>

    <div class="layui-body">
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
                                        <label class="layui-form-label w120" for="role_name">角色名称</label>
                                    </td>
                                    <td class="tl w200">
                                        <div class="layui-input-block in-block w160">
                                            <input type="text" id="role_name" class="layui-input"
                                                   placeholder="请输入角色名称" autocomplete="off">
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
                            <h3 class="panel-title pro-title">用户列表</h3>
                            <div class="s-icon xz title-icon"><span class="ito ito-chevron-up"></span></div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <table class="layui-hide" id="data_table" lay-filter="operation"></table>

                        <script type="text/html" id="statusTpl">
                            {{#  if(d.status === 1){ }}
                            <span class="status-normal">正常</span>
                            {{#  } }}

                            {{#  if(d.status === 2){ }}
                            <span class="status-disable">禁用</span>
                            {{#  } }}

                            {{#  if(d.status === 3){ }}
                            <span class="status-lock">锁定</span>
                            {{#  } }}

                            {{#  if(d.status !== 1 && d.status !== 2 && d.status !== 3){ }}
                            <span>其他</span>
                            {{#  } }}
                        </script>
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