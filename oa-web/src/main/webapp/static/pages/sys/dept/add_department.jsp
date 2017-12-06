<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/static/include/inc.jsp" %>

<%--
  
  Created by User: Zy
  Created Date: 2017/11/25 17:24
--%>

<script type="text/javascript">
    <!--
    require(["domReady"], function (doc) {
        require(["lay-ui"], function () {
            layui.use('form', function () {
                var form = layui.form;

                //监听提交
                form.on('submit(formDemo)', function (data) {
                    layer.msg(JSON.stringify(data.field));
                    return false;
                });

                form.render();// 重新渲染表单元素
//                form.render('select'); //刷新select选择框渲染
            });
        });

        loadDeptList();
    });

    function loadDeptList() {
        require(["jquery", "lay-ui"], function ($, lay) {
            $("#select_dept").click(function() {
                var $this = $(this);

                $.post(ctx + "/static/pages/sys/dept/dept_tree_list.jsp", function(html) {
                    layui.use('layer', function(){
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
                                    $("#dept_id").val(1);
                                    console.log(nodes[0].name);
                                    $this.val(nodes[0].name);
                                }
                                layer.close(index);
                            },
                            btn2: function(index, layero){
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

    function saveDepartment() {
        require(["jquery", "lay-ui"], function ($, lay) {
            var layer = layui.layer;
            $.ajax({
                url: "${ctx}/mvc/sysDepartment/mgr/save",
                type: "POST",
                data: $("#data_form").serialize(),
                dataType: "json",
                success: function (json) {
                    if (json.success) {
                        jumpToDeptList();
                    }
                    setTimeout(function () {
                        layer.msg(json.info);
                    }, 50);
                }, error: function () {
                    layer.msg("操作失败，请重试");
                }
            });
        });
    }

    function jumpToDeptList() {
        loadContent("${ctx}/static/pages/sys/dept/sys_department.jsp");
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
                        <a href="javascript:;" data-url="${ctx}/static/pages/sys/dept/sys_department.jsp">
                            <i class="ito ito-department"></i><span>部门管理</span>
                        </a>
                    </li>
                    <li class="location-item">
                        <i class="ito ito-add-icon"></i><span>添加部门</span>
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
                            <span class="ito ito-add-icon fl"></span>
                            <h3 class="panel-title pro-title">添加部门页面</h3>
                            <div class="s-icon xz title-icon"><span class="ito ito-chevron-up"></span></div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="site-text site-block">
                            <form id="data_form" class="layui-form" method="post">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">部门名称</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="name" class="layui-input" placeholder="请输入用户名"
                                               autocomplete="off">
                                    </div>
                                </div>

                                <div class="layui-form-item">
                                    <label class="layui-form-label">上级部门</label>
                                    <div class="layui-input-block">
                                        <input type="hidden" id="dept_id" name="parentId" value="0"/>
                                        <input type="text" id="select_dept" class="layui-input" placeholder="请选择上级部门"
                                               readonly>
                                    </div>
                                </div>

                                <div class="layui-form-item layui-form-text">
                                    <label class="layui-form-label">部门描述</label>
                                    <div class="layui-input-block">
                                        <textarea name="description" placeholder="请输入部门备注"
                                                  class="layui-textarea"></textarea>
                                    </div>
                                </div>
                                <input type="text" name="createTime" value="2017-12-16" />


                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button type="button" class="layui-btn mr20" onclick="saveDepartment()">立即提交
                                        </button>
                                        <button type="reset" class="layui-btn layui-btn-primary mr20">重置</button>
                                        <button type="button" class="layui-btn layui-btn-primary" onclick="jumpToDeptList()">返回</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>