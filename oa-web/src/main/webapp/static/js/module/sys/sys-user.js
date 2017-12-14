define(["jquery", "lay-ui", "ito-validation", "module-common"], function ($, lay, valida, commonJs) {

    var userJs = {
        cache: {},
        loadDataList: function () {
            layui.use('table', function () {
                var table = layui.table, form = layui.form, layer = layui.layer;

                form.render();// 重新渲染表单元素

                table.render({
                    elem: '#data_table'
                    , id: 'user_table'
                    , url: ctx + '/mvc/sysUser/mgr/list'
                    , method: "post"
                    , page: true
                    //, cellMinWidth: 100
                    , cols: [[
                        {type: 'checkbox'}
                        , {title: '序号', type: 'numbers'}
                        , {field: 'loginName', title: '登录名', sort: true}
                        //, {field: 'nickname', title: '用户昵称', sort: true}
                        , {field: 'email', title: 'Email', sort: true}
                        , {field: 'phone', title: '手机号'}
                        , {field: 'deptName', title: '部门', sort: true}
                        , {field: 'status', title: '用户状态', width: 100, sort: true, templet: '#statusTpl'}
                        , {field: 'createTime', title: '创建时间', width: 170, sort: true}
                        , {field: 'lastLoginTime', title: '最后登录时间', width: 170, sort: true}
                        , {title: '操作', width: 70, toolbar: '#operation_con', fixed: 'right'}
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
                        var delMsg = '您确定要删除 ' + data.loginName + " 吗？";
                        layer.confirm(delMsg, function (index) {
                            $.post(ctx + "/mvc/sysUser/mgr/delete/user", {id: data.id}, function (json) {
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
                        table.reload('user_table', {
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

            var url = ctx + "/mvc/sysUser/mgr/save/user";
            if (type === "update") {
                url = ctx + "/mvc/sysUser/mgr/update/user";
            }

            var layer = layui.layer;
            var options = {
                group: '.layui-form-item',
                fields: {
                    loginName: {
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            },
                            regexp: {
                                regexp: /^[^\u4e00-\u9fa5]+$/,
                                message: '用户名只能是英文和字符组合'
                            },
                            stringLength: {
                                min: 5,
                                max: 20,
                                message: '用户名最少5位最多20位'
                            }
                        }
                    },
                    password: {
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            },
                            stringLength: {
                                min: 5,
                                max: 30,
                                message: '密码最少5位最多20位'
                            },
                            identical: {
                                field: 'confirm_password',
                                message: '密码和确认密码必须相同'
                            }
                        }
                    },
                    confirm_password: {
                        validators: {
                            notEmpty: {
                                message: '确认密码不能为空'
                            },
                            stringLength: {
                                min: 5,
                                max: 30,
                                message: '密码最少5位最多20位'
                            },
                            identical: {
                                field: 'password',
                                message: '密码和确认密码必须相同'
                            }
                        }
                    },
                    email: {
                        validators: {
                            emailAddress: {
                                message: '请输入正确格式的Email'
                            }
                        }
                    },
                    phone: {
                        validators: {
                            notEmpty: {
                                message: '手机号不能为空'
                            },
                            regexp: {
                                regexp: /^[1]{1}[0-9]{10}$/,
                                message: '手机号格式不正确'
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
            userJs.saveOrUpdateForm("add");
        },
        updateForm: function () {
            userJs.saveOrUpdateForm("update");
        },
        jumpToAddPage: function () {
            // 跳转到添加页面
            commonJs.loadContent(ctx + "/static/pages/sys/user/add_user.jsp");
        },
        jumpToUpdatePage: function (id) {
            // 请求跳转到更新页面
            if (id) {
                commonJs.loadContent(ctx + "/mvc/sysUser/mgr/update/ui", {id: id});
            } else {
                layui.use('layer', function (layer) {
                    layer.msg("跳转用户更新页面异常，丢失id");
                });
            }
        },
        jumpToDataList: function () {
            // 跳转到列表页
            commonJs.loadContent(ctx + "/static/pages/sys/user/sys_user.jsp");
        },
        initBindEvent: function () {
            var eleArray = [
                {ele: "#add_btn", event: userJs.jumpToAddPage},
                {ele: "#back_btn", event: userJs.jumpToDataList},
                {ele: "#user_dept", event: userJs.loadDeptTreeList},
                {ele: "#save_btn", event: userJs.saveForm},
                {ele: "#update_btn", event: userJs.updateForm}
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

    return userJs;
});