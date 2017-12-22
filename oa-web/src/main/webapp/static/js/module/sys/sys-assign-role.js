define(["jquery", "lay-ui", "ito-validation", "module-common"], function ($, lay, valida, commonJs) {
    var fpJs = {
        cache: {},
        loadDataList: function () {
            layui.use('table', function () {
                var table = layui.table, form = layui.form, layer = layui.layer;

                form.render();// 重新渲染表单元素

                table.render({
                    elem: '#data_table'
                    , id: 'assign_role_table'
                    , url: ctx + '/mvc/sysUser/mgr/user/role/list'
                    , method: "post"
                    , page: true
                    , cellMinWidth: 100
                    , cols: [[
                        {title: '序号', type: 'numbers'}
                        , {field: 'nickname', title: '用户昵称', sort: true}
                        , {field: 'loginName', title: '用户帐号', sort: true}
                        , {field: 'status', title: '用户状态', width: 100, sort: true, templet: '#statusTpl'}
                        , {field: 'roleName', title: '拥有的角色', sort: true}
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
                        var delMsg = '您确定要清空 ' + data.nickname + " 的用户角色吗？";
                        layer.confirm(delMsg, function (index) {
                            $.post(ctx + "/mvc/sysRole/mgr/clear/user/roles", {userId: data.id}, function (json) {
                                if (json.success) {
                                    obj.del();
                                    layer.close(index);
                                    fpJs.jumpToDataList();
                                }

                                setTimeout(function () {
                                    layer.msg(json.info);
                                }, 50);
                            }, "json");
                        });
                    } else if (obj.event === 'edit') {
                        fpJs.loadRoleWinList(data.id);
                    }
                });

                var active = {
                    reload: function () {
                        var $searchCon = $('#search_con');

                        //执行重载
                        table.reload('assign_role_table', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                            , where: {
                                searchCon: $searchCon.val()
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
        loadRoleWinList: function (uid) {
            $.post(ctx + "/static/pages/sys/role/role_list_win.jsp", function (html) {
                layui.use('layer', function (layer) {
                    var table = layui.table;
                    var userId = uid;
                    var title = "<span><i class='ito ito-setting-role'></i><span class='ml6'>分配角色</span></span>";

                    layer.open({
                        id: "role_list_fp",
                        type: 1,
                        title: title,
                        skin: 'layui-layer-molv', //样式类名
                        area: ['400px', '320px'],
                        closeBtn: 0,
                        content: html,
                        btn: ['确定', '取消'],
                        yes: function (index) {
                            var checkStatus = table.checkStatus('win_assign_role_table');
                            checkStatus.userId = userId;
                            fpJs.assignRole(checkStatus);// 分配角色
                        },
                        btn2: function (index, layero) {
                            // 按钮【按钮二】的回调
                            layer.close(index);
                        }
                    });
                });
            });
        },
        assignRole: function (obj) {
            if (obj) {
                var layer = layui.layer;
                var data = obj.data;
                var ids = [];
                for (var i in data) {
                    if (data.hasOwnProperty(i)) {
                        ids.push(data[i].id.toString());
                    }
                }

                try {
                    fpJs.assignRoleValida(obj.userId, ids);

                    $.ajax({
                        url: ctx + "/mvc/sysRole/mgr/assign/roles",
                        type: "POST",
                        data: {userId: obj.userId, roleId: ids},
                        dataType: "json",
                        success: function (json) {
                            if (json.success) {
                                layer.closeAll();
                                fpJs.jumpToDataList();
                            }
                            layer.msg(json.info);
                        }, error: function () {
                            layer.msg("操作失败，请重试！");
                        }
                    });
                } catch (e) {
                    layer.msg(e.message);
                }
            }
        },
        assignRoleValida: function (userId, roleId) {
            if (!roleId || roleId === null || roleId.length === 0) {
                throw new Error("请至少选择一个角色！");
            }

            if (!userId) {
                throw new Error("操作异常，用户ID丢失，请刷新页面重试！");
            }

            if (roleId.length > 5) {
                throw new Error("最多为用户分配5个角色");
            }
        },
        jumpToDataList: function () {
            // 跳转到列表页
            commonJs.loadContent(ctx + "/static/pages/sys/pms/user_role_list.jsp");
        }
    };

    return fpJs;
});