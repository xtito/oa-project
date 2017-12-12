<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/static/include/inc.jsp" %>

<%--
  
  Created by User: Zy
  Created Date: 2017/11/25 17:23
--%>

<script type="text/javascript">
    <!--
    require(["domReady"], function (doc) {
        require(["jquery", "lay-ui"], function ($, lay) {
            layui.use('table', function () {
                var table = layui.table, form = layui.form, layer = layui.layer;

                form.render();// 重新渲染表单元素

                table.render({
                    elem: '#data_table'
                    , id: 'table_reload'
                    , url: ctx + '/mvc/sysDepartment/mgr/list'
                    , method: "post"
                    , page: true
                    , cellMinWidth: 100
                    , cols: [[
                        {type: 'checkbox'}
                        , {title: '序号', type: 'numbers'}
//                        ,{field:'id', title:'ID', width:100, unresize: true, sort: true}
                        , {field: 'name', title: '部门名称', sort: true}
                        , {field: 'createTime', title: '创建时间', width: 180, sort: true}
                        , {field: 'description', title: '部门描述'}
                        , {title: '操作', width: 100, toolbar: '#operation_con', fixed: 'right'}
                    ]]
                    , request: {pageName: "pageNum", limitName: "pageSize"}
                    , response: {
                        countName: "total",
                        dataName: "list"
                    }
                });

                table.on("tool(operation)", function (obj) {
                    var data = obj.data;
                    if (obj.event === 'del') {
                        var delMsg = '您确定要删除 ' + data.name + " 吗？";
                        layer.confirm(delMsg, function (index) {
                            $.post("${ctx}/mvc/sysDepartment/mgr/delete", {id: data.id}, function (json) {
                                console.log(json);
                                if (json.success) {
                                    obj.del();
                                    layer.close(index);
                                    jumpToDeptList();
                                }

                                setTimeout(function () {
                                    console.log(json.info);
                                    layer.msg(json.info);
                                }, 50);
                            }, "json");
                        });
                    } else if (obj.event === 'edit') {
                        loadInnerContent("${ctx}/mvc/sysDepartment/mgr/update/ui?id=" + data.id);
                    }
                });

                var active = {
                    reload: function () {
                        var $deptName = $('#dept_name');

                        //执行重载
                        table.reload('table_reload', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                            , where: {
                                deptName: $deptName.val()
                            }
                        });
                    }
                };

                $("#search_btn").on('click', function () {
                    var type = $(this).data('type');
                    active[type] ? active[type].call(this) : '';
                });
            });
        });
    });

    function loadDeptList() {
        require(["jquery", "lay-ui"], function ($, lay) {
            $("#select_dept").click(function () {
                var $this = $(this);

                $.post(ctx + "/static/pages/sys/dept/dept_tree_list.jsp", function (html) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        var title = "<span><i class='ito ito-department'></i><span class='ml6'>部门列表</span></span>";

                        layer.open({
                            id: "department_list",
                            type: 1,
                            title: title,
                            area: ['400px', '320px'],
                            content: html,
                            btn: ['确定', '取消'],
                            yes: function (index) {
                                var treeObj = $.fn.zTree.getZTreeObj("treeEle");
                                var nodes = treeObj.getSelectedNodes();
                                if (nodes) {
                                    $("#dept_id").val(nodes[0].id);
                                    $this.val(nodes[0].name);
                                }
                                layer.close(index);
                            },
                            btn2: function (index, layero) {
                                // 按钮【按钮二】的回调
                                layer.close(index);
                                //return false 开启该代码可禁止点击该按钮关闭
                            }
                        });
                    });
                });
            });
        });
    }

    function addUI() {
        loadInnerContent("${ctx}/static/pages/sys/dept/add_department.jsp");
    }

    // 跳转模块首页的时候一定要调用外部加载方法 loadContent
    function jumpToDeptList() {
        loadContent("${ctx}/static/pages/sys/dept/sys_department.jsp");
    }
    //-->
</script>

<div id="inner_main" class="inner-main">
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
                                                <input type="text" id="dept_name" class="layui-input" name="title"
                                                       placeholder="请输入部门名称" autocomplete="off">
                                            </div>
                                        </td>

                                        <td class="tl pl40">
                                            <button type="button" id="search_btn" class="layui-btn mr20" data-type="reload">
                                                查询
                                            </button>
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
                            <table class="layui-hide" id="data_table" lay-filter="operation"></table>
                        </div>
                    </div>
                </div>

                <script type="text/html" id="operation_con">
                    <a class="operation-btn" lay-event="edit"><i class="ito ito-edit mr10"></i></a>
                    <a class="operation-btn" lay-event="del"><i class="ito ito-delete"></i></a>
                </script>
            </div>
        </div>
    </div>
</div>
