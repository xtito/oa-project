define(["jquery", "lay-ui", "ito-validation", "module-common"], function ($, lay, valida, commonJs) {

    var roleJs = {
        cache: {},
        loadDataList: function () {
            layui.use('table', function () {
                var table = layui.table, form = layui.form, layer = layui.layer;

                form.render();// 重新渲染表单元素

                table.render({
                    elem: '#data_table'
                    , id: 'user_table'
                    , url: ctx + '/mvc/sysRole/mgr/list'
                    , method: "post"
                    , page: true
                    //, cellMinWidth: 100
                    , cols: [[
                        {type: 'checkbox'}
                        , {title: '序号', type: 'numbers'}
                        , {field: 'name', title: '角色名', sort: true}
                        , {field: 'description', title: '角色描述', sort: true}
                        , {field: 'createTime', title: '创建时间', width: 170, sort: true}
                        , {field: 'updateTime', title: '更新时间', width: 170, sort: true}
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
                            $.post(ctx + "/mvc/sysRole/mgr/delete", {id: data.id}, function (json) {
                                if (json.success) {
                                    obj.del();
                                    layer.close(index);
                                    roleJs.jumpToDataList();
                                }

                                setTimeout(function () {
                                    layer.msg(json.info);
                                }, 50);
                            }, "json");
                        });
                    } else if (obj.event === 'edit') {
                        roleJs.jumpToUpdatePage(data.id);
                    }
                });

                var active = {
                    reload: function () {
                        var $roleName = $('#role_name');

                        //执行重载
                        table.reload('user_table', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                            , where: {
                                roleName: $roleName.val()
                            }
                        });
                    }
                };

                $("#search_btn").on('click', function () {
                    var type = $(this).data('type');
                    active[type] ? active[type].call(this) : '';
                });
            });
        },
        loadDeptTreeList: function () {
            var $this = $(this);

            $.post(ctx + "/static/pages/sys/dept/dept_tree_list.jsp", function (html) {
                layui.use('layer', function (layer) {
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
        },
        saveOrUpdateForm: function (type) {

            var url = ctx + "/mvc/sysRole/mgr/save";
            if (type === "update") {
                url = ctx + "/mvc/sysRole/mgr/update";
            }

            var layer = layui.layer;
            var options = {
                group: '.layui-form-item',
                fields: {
                    name: {
                        validators: {
                            notEmpty: {
                                message: '角色名称不能为空'
                            }
                        }
                    }
                }
            };

            var validaForm = new valida.Validator("#data_form", options);
            if (validaForm.validateForm()) {
                $.ajax({
                    url: url,
                    type: "POST",
                    data: $("#data_form").serialize(),
                    dataType: "json",
                    success: function (json) {
                        if (json.success) {
                            roleJs.jumpToDataList();
                        }
                        setTimeout(function () {
                            layer.msg(json.info);
                        }, 50);
                    }, error: function () {
                        layer.msg("操作失败，请重试");
                    }
                });
            }
        },
        saveForm: function () {
            roleJs.saveOrUpdateForm("add");
        },
        updateForm: function () {
            roleJs.saveOrUpdateForm("update");
        },
        jumpToAddPage: function () {
            // 跳转到添加页面
            commonJs.loadContent(ctx + "/static/pages/sys/role/add_role.jsp", null, function () {
                roleJs.initBindEvent();
            });
        },
        jumpToUpdatePage: function (id) {
            // 请求跳转到更新页面
            if (id) {
                commonJs.loadContent(ctx + "/mvc/sysRole/mgr/update/ui", {id: id}, function () {
                    roleJs.initBindEvent();
                });
            } else {
                layui.use('layer', function (layer) {
                    layer.msg("跳转用户更新页面异常，丢失id");
                });
            }
        },
        jumpToDataList: function () {
            // 跳转到列表页
            commonJs.loadContent(ctx + "/static/pages/sys/role/sys_role.jsp");
        },
        initBindEvent: function () {
            var eleArray = [
                {ele: "#add_btn", event: roleJs.jumpToAddPage},
                {ele: "#back_btn", event: roleJs.jumpToDataList},
                {ele: "#user_dept", event: roleJs.loadDeptTreeList},
                {ele: "#save_btn", event: roleJs.saveForm},
                {ele: "#update_btn", event: roleJs.updateForm}
            ];

            for (var e in eleArray) {
                if (eleArray.hasOwnProperty(e)) {
                    if ($(eleArray[e].ele).length > 0) {
                        $(eleArray[e].ele).click(eleArray[e].event);
                    }
                }
            }
        }
    };

    return roleJs;
});