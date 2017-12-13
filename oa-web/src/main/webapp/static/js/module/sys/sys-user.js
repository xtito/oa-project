define(["jquery", "lay-ui", "ito-validation"], function($, lay, valida) {

    var userJs = {
        cache: {},
        loadDataList: function () {
            layui.use('table', function () {
                var table = layui.table, form = layui.form, layer = layui.layer;

                form.render();// 重新渲染表单元素

                table.render({
                    elem: '#data_table'
                    , id: 'table_reload'
                    , url: ctx + '/mvc/sysUser/mgr/list'
                    , method: "post"
                    , page: true
                    , cellMinWidth: 100
                    , cols: [[
                        {type: 'checkbox'}
                        , {title: '序号', type: 'numbers'}
                        , {field: 'loginName', title: '登录名', sort: true}
                        , {field: 'nickname', title: '用户昵称', sort: true}
                        , {field: 'email', title: 'Email', sort: true}
                        , {field: 'phone', title: '手机号'}
                        , {field: 'deptName', title: '部门', sort: true}
                        , {field: 'status', title: '用户状态', sort: true}
                        , {field: 'createTime', title: '创建时间', width: 160, sort: true}
                        , {field: 'lastLoginTime', title: '最后登录时间', width: 160, sort: true}
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
                            $.post("${ctx}/mvc/sysUser/mgr/delete/user", {id: data.id}, function (json) {
                                console.log(json);
                                if (json.success) {
                                    obj.del();
                                    layer.close(index);
                                    userJs.jumpToDataList();
                                }

                                setTimeout(function () {
                                    layer.msg(json.info);
                                }, 50);
                            }, "json");
                        });
                    } else if (obj.event === 'edit') {
                        userJs.jumpToUpdatePage(data.id);
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
            if (type === "add") {

            }

            var layer = layui.layer;
            var options = {
                group: '.layui-form-item',
                fields: {
                    name: {
                        validators: {
                            notEmpty: {
                                message: '用户名称不能为空'
                            }
                        }
                    }
                }
            };

            var validaForm = new valida.Validator("#data_form", options);
            if (validaForm.validateForm()) {
                $.ajax({
                    url: ctx + "/mvc/sysUser/mgr/save/user",
                    type: "POST",
                    data: $("#data_form").serialize(),
                    dataType: "json",
                    success: function (json) {
                        if (json.success) {
                            userJs.jumpToDataList();
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
            this.saveOrUpdateForm("add");
        },
        jumpToAddPage: function () {
            // 跳转到添加页面
            loadInnerContent(ctx + "/static/pages/sys/user/add_user.jsp");
        },
        jumpToUpdatePage: function (id) {
            // 请求跳转到更新页面
            if (id) {
                loadInnerContent("${ctx}/mvc/sysDepartment/mgr/update/ui", {id: id});
            } else {
                layui.use('layer', function (layer) {
                    layer.msg("跳转用户更新页面异常，丢失id");
                });
            }
        },
        jumpToDataList: function () {
            // 跳转到列表页
            loadContent(ctx + "/static/pages/sys/user/sys_user.jsp");
        },
        initBindEvent: function () {
            $("#add_btn").click(userJs.jumpToAddPage);
            $("#back_btn").click(userJs.jumpToDataList);
            $("#user_dept").click(userJs.loadDeptTreeList);
            $("#save_btn").click(userJs.saveForm);
        }
    };

    return userJs;
});