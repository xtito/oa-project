<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/static/include/inc.jsp" %>

<%--
  
  Created by User: Zy
  Created Date: 2017/12/14 14:12
--%>
<script type="text/javascript">
    <!--
    require(["domReady"], function(doc) {
        require(["sys-user"], function(userJs){
            userJs.initBindEvent();
        });
    });
    //-->
</script>

<div class="layui-tab layui-tab-brief">
    <jsp:include page="inner_user_menu.jsp">
        <jsp:param name="active" value="4" />
    </jsp:include>

    <div class="ito-home-section">
        <div class="ito-home-address">
            <div class="ito-home-location">
                <ol class="ito-breadcrumb">
                    <li class="location-item">
                        <a href="javascript:;" data-trigger-class="trigger-home">
                            <i class="ito ito-home"></i><span>首页</span>
                        </a>
                    </li>
                    <li class="location-item">
                        <a href="javascript:;" data-trigger-class="trigger-user-list">
                            <i class="ito ito-user-list"></i><span>用户管理</span>
                        </a>
                    </li>
                    <li class="location-item">
                        <i class="ito ito-edit-user"></i><span>更新用户</span>
                    </li>
                </ol>
            </div>
        </div>
    </div>

    <div class="layui-body layui-tab-content site-demo site-demo-body">
        <div class="layui-tab-item layui-show">
            <div id="inner_main" class="layui-main">
                <div class="ito-inner-con">
                    <div class="ito-col-panel">
                        <div class="ito-panel">
                            <div class="panel-heading">
                                <div class="search-title">
                                    <span class="ito ito-edit-user fl"></span>
                                    <h3 class="panel-title pro-title">更新用户页面</h3>
                                    <div class="s-icon xz title-icon"><span class="ito ito-chevron-up"></span></div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="site-text site-block">
                                    <form id="data_form" class="layui-form">
                                        <input type="hidden" name="id" value="${requestScope.user.id}" />

                                        <div class="layui-form-item">
                                            <label class="layui-form-label require-field">用户名</label>
                                            <div class="layui-input-block">
                                                <input type="text" class="layui-input" placeholder="您的账户名和登录名" readonly disabled
                                                       value="${requestScope.user.loginName}">
                                            </div>
                                        </div>

                                        <div class="layui-form-item">
                                            <label class="layui-form-label">昵称</label>
                                            <div class="layui-input-block">
                                                <input type="text" class="layui-input" name="nickname" placeholder="您的昵称"
                                                       maxlength="64" value="${requestScope.user.nickname}">
                                            </div>
                                        </div>

                                        <div class="layui-form-item">
                                            <label class="layui-form-label" for="user_dept">部门</label>
                                            <div class="layui-input-block">
                                                <input type="hidden" id="dept_id" name="departmentId" value="${requestScope.user.departmentId}"/>
                                                <input type="text" id="user_dept" class="layui-input" placeholder="请点击选择部门"
                                                       value="${requestScope.user.deptName}" readonly>
                                            </div>
                                        </div>

                                        <div class="layui-form-item">
                                            <label class="layui-form-label">Email</label>
                                            <div class="layui-input-block">
                                                <input type="text" name="email" class="layui-input" placeholder="您的常用Email"
                                                       autocomplete="off" maxlength="50" value="${requestScope.user.email}">
                                            </div>
                                        </div>

                                        <div class="layui-form-item">
                                            <label class="layui-form-label require-field">手机号</label>
                                            <div class="layui-input-block">
                                                <input type="text" name="phone" class="layui-input" placeholder="建议使用常用手机"
                                                       autocomplete="off" maxlength="11" value="${requestScope.user.phone}">
                                            </div>
                                        </div>


                                        <div class="layui-form-item layui-form-text">
                                            <label class="layui-form-label">描述</label>
                                            <div class="layui-input-block">
                                        <textarea name="description" placeholder="请输入用户备注" maxlength="300"
                                                  class="layui-textarea">${requestScope.user.description}</textarea>
                                            </div>
                                        </div>


                                        <div class="layui-form-item">
                                            <div class="layui-input-block">
                                                <button type="button" id="update_btn" class="layui-btn mr10">立即提交</button>
                                                <button type="reset" class="layui-btn layui-btn-primary mr10">重置</button>
                                                <button type="button" id="back_btn" class="layui-btn layui-btn-primary">返回
                                                </button>
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
    </div>
</div>
