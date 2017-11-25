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

    function saveUser() {
        require(["jquery"], function($) {
            $.ajax({
                url: "${ctx}/mvc/sysUser/mgr/save/user",
                type: "POST",
                data: $("#data_form").serialize(),
                dataType: "json",
                success: function (json) {
                    if (json.success) {

                    } else {

                    }
                }, error: function () {
                    alert("");
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
                        <a href="javascript:;" data-url="${ctx}/mvc/sysUser/mgr/list">
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
                            <span class="ito ito-add-user fl"></span>
                            <h3 class="panel-title pro-title">添加用户页面</h3>
                            <div class="s-icon xz title-icon"><span class="ito ito-chevron-up"></span></div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="site-text site-block">
                            <form class="layui-form" action="">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">用户名</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="title" class="layui-input" lay-verify="required" placeholder="请输入用户名" autocomplete="off">
                                    </div>
                                </div>

                                <div class="layui-form-item">
                                    <label class="layui-form-label">用户密码</label>
                                    <div class="layui-input-block">
                                        <input type="password" name="password" class="layui-input" lay-verify="required" lay-vertype="tips" placeholder="请输入密码" autocomplete="off">
                                    </div>
                                </div>

                                <div class="layui-form-item">
                                    <label class="layui-form-label">再次输入密码</label>
                                    <div class="layui-input-block">
                                        <input type="password" name="password" class="layui-input" lay-verify="required" lay-vertype="tips" placeholder="请再次输入密码" autocomplete="off">
                                    </div>
                                </div>

                                <div class="layui-form-item">
                                    <label class="layui-form-label" for="user_dept">部门</label>
                                    <div class="layui-input-block">
                                        <select id="user_dept" name="city" lay-verify="required">
                                            <option value=""></option>
                                            <option value="0">北京</option>
                                            <option value="1">上海</option>
                                            <option value="2">广州</option>
                                            <option value="3">深圳</option>
                                            <option value="4">杭州</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="layui-form-item">
                                    <label class="layui-form-label">Email</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="title" class="layui-input" placeholder="请输入Email" autocomplete="off">
                                    </div>
                                </div>

                                <div class="layui-form-item">
                                    <label class="layui-form-label">手机号</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="title" class="layui-input" placeholder="请输入手机号" autocomplete="off">
                                    </div>
                                </div>


                                <div class="layui-form-item layui-form-text">
                                    <label class="layui-form-label">备注</label>
                                    <div class="layui-input-block">
                                        <textarea name="desc" placeholder="请输入用户备注" class="layui-textarea"></textarea>
                                    </div>
                                </div>


                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button class="layui-btn" lay-submit="" lay-filter="formDemo">立即提交</button>
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