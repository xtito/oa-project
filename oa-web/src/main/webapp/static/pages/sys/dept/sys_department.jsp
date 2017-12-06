<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/static/include/inc.jsp" %>

<%--
  
  Created by User: Zy
  Created Date: 2017/11/25 17:23
--%>

<script type="text/javascript">
    <!--
    require(["domReady"], function(doc) {
        require(["jquery", "lay-ui"], function($, lay){
            layui.use('table', function(){
                var table = layui.table
                        ,form = layui.form;

                form.render();// 重新渲染表单元素

                // page: {curr: 1} 重新从第 1 页开始
                table.render({
                    elem: '#data_table'
                    ,id: 'table_reload'
                    ,url: ctx + '/mvc/sysDepartment/mgr/list'
                    ,method: "post"
                    ,page: true
                    ,cellMinWidth: 100
                    ,cols: [[
                        {type:'numbers'}
                        ,{type: 'checkbox'}
                        ,{field:'id', title:'ID', width:100, unresize: true, sort: true}
                        ,{field:'name', title:'部门名称', sort: true}
                        ,{field:'createTime', title:'创建时间', width:180, sort: true}
                        ,{field:'description', title:'部门描述'}
                    ]]
                    ,request: {pageName: "pageNum", limitName: "pageSize"}
                    ,response: {
                        countName: "total",
                        dataName: "list"
                    }
                });

                var active = {
                    reload: function(){
                        var $deptName = $('#dept_name');

                        //执行重载
                        table.reload('table_reload', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                            ,where: {
                                deptName: $deptName.val()
                            }
                        });
                    }
                };

                $("#search_btn").on('click', function(){
                    var type = $(this).data('type');
                    active[type] ? active[type].call(this) : '';
                });
            });
        });
    });

    function addUI() {
        loadContent("${ctx}/static/pages/sys/dept/add_department.jsp");
    }
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
                        <i class="ito ito-department"></i><span>部门管理</span>
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
                                        <label class="layui-form-label w120" for="dept_name">部门名称</label>
                                    </td>
                                    <td class="tl w200">
                                        <div class="layui-input-block in-block w160">
                                            <input type="text" id="dept_name" class="layui-input" name="title" placeholder="请输入部门名称" autocomplete="off">
                                        </div>
                                    </td>

                                    <td class="tl pl40">
                                        <button type="button" id="search_btn" class="layui-btn mr20" data-type="reload">查询</button>
                                        <button type="button" class="layui-btn mr20">重置</button>
                                        <button type="button" class="layui-btn" onclick="addUI()">新建</button>
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
                        <table class="layui-hide" id="data_table"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>