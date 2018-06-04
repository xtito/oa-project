define(["jquery", "lay-ui", "ito-validation", "module-common"], function ($, lay, valida, commonJs) {

    var pmsJs = {
        cache: {},
        loadDataList: function () {
            layui.use('table', function () {
                var table = layui.table, form = layui.form, layer = layui.layer;

                form.render();// 重新渲染表单元素

                table.render({
                    elem: '#data_table'
                    , id: 'pms_table'
                    , url: ctx + '/sysPermission/mgr/list'
                    , method: "post"
                    , page: true
                    //, cellMinWidth: 100
                    , cols: [[
                        {type: 'checkbox'}
                        , {title: '序号', type: 'numbers'}
                        , {field: 'name', title: '权限名称', sort: true}
                        , {field: 'url', title: '权限路径', sort: true}
                        , {field: 'parentName', title: '上级权限'}
                        , {field: 'icon', title: '权限图标', sort: true}
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
                        var delMsg = '您确定要删除 ' + data.name + " 吗？";
                        layer.confirm(delMsg, function (index) {
                            $.post(ctx + "/sysPermission/mgr/delete", {id: data.id}, function (json) {
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
                        pmsJs.jumpToUpdatePage(data.id);
                    }
                });

                var active = {
                    reload: function () {
                        var $pmsName = $('#pms_name');

                        //执行重载
                        table.reload('pms_table', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                            , where: {
                                pmsName: $pmsName.val()
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

            $.post(ctx + "/pages/sys/dept/dept_tree_list.jsp", function (html) {
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

            var url = ctx + "/sysPermission/mgr/save";
            if (type === "update") {
                url = ctx + "/sysPermission/mgr/update";
            }

            var layer = layui.layer;
            var options = {
                group: '.layui-form-item',
                fields: {
                    name: {
                        validators: {
                            notEmpty: {
                                message: '权限名不能为空'
                            }
                        }
                    },
                    url: {
                        validators: {
                            notEmpty: {
                                message: '权限路径不能为空'
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
                            pmsJs.jumpToDataList();
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
            pmsJs.saveOrUpdateForm("add");
        },
        updateForm: function () {
            pmsJs.saveOrUpdateForm("update");
        },
        jumpToAddPage: function () {
            // 跳转到添加页面
            commonJs.loadContent(ctx + "/pages/sys/pms/add_permission.jsp", null, function () {
                pmsJs.initBindEvent();
            });
        },
        jumpToUpdatePage: function (id) {
            // 请求跳转到更新页面
            if (id) {
                commonJs.loadContent(ctx + "/sysPermission/mgr/update/ui", {id: id}, function () {
                    pmsJs.initBindEvent();
                });
            } else {
                layui.use('layer', function (layer) {
                    layer.msg("跳转权限更新页面异常，丢失id");
                });
            }
        },
        jumpToDataList: function () {
            // 跳转到列表页
            commonJs.loadContent(ctx + "/pages/sys/pms/sys_permission.jsp");
        },
        initBindEvent: function () {
            var eleArray = [
                {ele: "#add_btn", event: pmsJs.jumpToAddPage},
                {ele: "#back_btn", event: pmsJs.jumpToDataList},
                {ele: "#user_dept", event: pmsJs.loadDeptTreeList},
                {ele: "#save_btn", event: pmsJs.saveForm},
                {ele: "#update_btn", event: pmsJs.updateForm}
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

    return pmsJs;
});