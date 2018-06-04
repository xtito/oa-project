define(["jquery", "lay-ui", "ito-validation", "module-common"], function ($, lay, valida, commonJs) {
    var pmsJs = {
        cache: {},
        loadDataList: function () {
            layui.use('table', function () {
                var table = layui.table, form = layui.form, layer = layui.layer;

                form.render();// 重新渲染表单元素

                table.render({
                    elem: '#data_table'
                    , id: 'assign_pms_table'
                    , url: ctx + '/sysRole/mgr/role/pms/list'
                    , method: "post"
                    , page: true
                    , cellMinWidth: 100
                    , cols: [[
                        {title: '序号', type: 'numbers'}
                        , {field: 'name', title: '角色名称', sort: true}
                        // , {field: 'description', title: '角色备注', sort: true}
                        , {field: 'pmsName', title: '拥有的权限', sort: true}
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
                        var delMsg = '您确定要清空 ' + data.name + " 的用户角色吗？";
                        layer.confirm(delMsg, function (index) {
                            $.post(ctx + "/sysPermission/mgr/clear/role/pms", {roleId: data.id}, function (json) {
                                if (json.success) {
                                    obj.del();
                                    layer.close(index);
                                    pmsJs.jumpToDataList();
                                }

                                setTimeout(function () {
                                    layer.msg(json.info);
                                }, 50);
                            }, "json");
                        });
                    } else if (obj.event === 'edit') {
                        pmsJs.loadRoleWinList(data.id);
                    }
                });

                var active = {
                    reload: function () {
                        var $searchCon = $('#search_con');

                        //执行重载
                        table.reload('assign_pms_table', {
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
            $.post(ctx + "/sysPermission/mgr/assign/pms/page", {roleId: uid}, function (html) {
                layui.use('layer', function (layer) {
                    var table = layui.table;
                    var roleId = uid;
                    var title = "<span><i class='ito ito-setting-permissions'></i><span class='ml6'>分配权限</span></span>";

                    layer.open({
                        id: "pms_list_fp",
                        type: 1,
                        title: title,
                        skin: 'layui-layer-molv', //样式类名
                        area: ['630px', '436px'],
                        closeBtn: 0,
                        content: html,
                        btn: ['确定', '取消'],
                        yes: function (index) {
                            var checkStatus = table.checkStatus('win_assign_pms_table');
                            checkStatus.roleId = roleId;
                            pmsJs.assignPms(checkStatus);// 分配权限
                        },
                        btn2: function (index, layero) {
                            // 按钮【按钮二】的回调
                            layer.close(index);
                        }
                    });
                });
            });
        },
        assignPms: function (obj) {
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
                    pmsJs.assignRoleValida(obj.roleId, ids);

                    $.ajax({
                        url: ctx + "/sysPermission/mgr/assign/pms",
                        type: "POST",
                        data: {roleId: obj.roleId, pmsId: ids},
                        dataType: "json",
                        success: function (json) {
                            if (json.success) {
                                layer.closeAll();
                                pmsJs.jumpToDataList();
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
        assignRoleValida: function (roleId, pmsId) {
            if (!pmsId || pmsId === null || pmsId.length === 0) {
                throw new Error("请至少分配一个权限！");
            }

            if (!roleId) {
                throw new Error("操作异常，用户ID丢失，请刷新页面重试！");
            }
        },
        jumpToDataList: function () {
            // 跳转到列表页
            commonJs.loadContent(ctx + "/pages/sys/pms/role_pms_list.jsp");
        }
    };

    return pmsJs;
});