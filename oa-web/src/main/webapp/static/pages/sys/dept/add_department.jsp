<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/static/include/inc.jsp" %>

<%--
  
  Created by User: Zy
  Created Date: 2017/11/25 17:24
--%>

<script type="text/javascript">
    <!--
    require(["domReady"], function(doc) {
        require(["lay-ui"], function() {
            layui.use('form', function(){
                var form = layui.form;

                //监听提交
                form.on('submit(formDemo)', function(data){
                    layer.msg(JSON.stringify(data.field));
                    return false;
                });

                form.render();// 重新渲染表单元素
//                form.render('select'); //刷新select选择框渲染
            });
        });
    });

    function saveDepartment() {
        require(["jquery", "lay-ui"], function($, lay) {
            var layer = layui.layer;
            $.ajax({
                url: "${ctx}/mvc/sysDepartment/mgr/save",
                type: "POST",
                data: $("#data_form").serialize(),
                dataType: "json",
                success: function (json) {
                    console.log(JSON.stringify(json));
                    if (json.success) {

                    }
                    layer.msg(json.info);
                }, error: function () {
                    layer.msg("操作失败，请重试");
                }
            });
        });


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
                        <a href="javascript:;" data-url="${ctx}/mvc/sysDepartment/mgr/list">
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
                                        <input type="text" name="name" class="layui-input" placeholder="请输入用户名" autocomplete="off">
                                    </div>
                                </div>

                                <div class="layui-form-item">
                                    <label class="layui-form-label">上级部门</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="parentId" class="layui-input" placeholder="请选择上级部门" readonly>
                                    </div>
                                </div>

                                <div class="layui-form-item layui-form-text">
                                    <label class="layui-form-label">部门描述</label>
                                    <div class="layui-input-block">
                                        <textarea name="description" placeholder="请输入部门备注" class="layui-textarea"></textarea>
                                    </div>
                                </div>


                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button type="button" class="layui-btn" onclick="saveDepartment()">立即提交</button>
                                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
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