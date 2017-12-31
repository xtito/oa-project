<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc.jsp" %>

<%--
  
  Created by User: Zy
  Created Date: 2017/12/16 22:50
--%>

<div class="ito-view-con">
    <div class="ito-col-panel">
        <div class="ito-panel">
            <div class="panel-heading">
                <div class="search-title">
                    <span class="ito ito-details fl"></span>
                    <h3 class="panel-title pro-title">用户帐号信息</h3>
                    <div class="s-icon xz title-icon"><span class="ito ito-chevron-up"></span></div>
                </div>
            </div>
            <div class="panel-body">
                <div class="site-text">
                    <div class="layui-form-item">
                        <label class="layui-form-label require-field" for="login_name">用户名</label>
                        <div class="layui-input-block">
                            <input type="text" id="login_name" class="layui-input" readonly disabled
                                   value="${requestScope.user.loginName}">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" for="nickname">昵称</label>
                        <div class="layui-input-block">
                            <input type="text" id="nickname" class="layui-input" name="nickname" readonly
                                   disabled value="${requestScope.user.nickname}">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" for="status">用户状态</label>
                        <div class="layui-input-block">
                            <input type="text" id="status" class="layui-input" readonly disabled
                                   value="${ito:getStatusText(requestScope.user.status)}">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" for="create_time">创建日期</label>
                        <div class="layui-input-block">
                            <input type="text" id="create_time" class="layui-input" readonly disabled
                                   value="${ito:date2string(requestScope.user.createTime, 'yyyy-MM-dd HH:mm:ss')}">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" for="user_dept">部门</label>
                        <div class="layui-input-block">
                            <input type="text" id="user_dept" class="layui-input" readonly disabled
                                   value="${requestScope.user.deptName}">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" for="email">Email</label>
                        <div class="layui-input-block">
                            <input type="text" id="email" class="layui-input" readonly disabled
                                   value="${requestScope.user.email}">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label require-field" for="phone">手机号</label>
                        <div class="layui-input-block">
                            <input type="text" id="phone" class="layui-input" readonly disabled
                                   value="${requestScope.user.phone}">
                        </div>
                    </div>

                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label" for="description">描述</label>
                        <div class="layui-input-block">
                            <textarea id="description" class="layui-textarea"
                                      readonly>${requestScope.user.description}</textarea>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" for="last_time">最后登录日期</label>
                        <div class="layui-input-block">
                            <input type="text" id="last_time" class="layui-input" readonly disabled
                                   value="${ito:date2string(requestScope.user.lastLoginTime, 'yyyy-MM-dd HH:mm:ss')}">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" for="lock_time">锁定时间</label>
                        <div class="layui-input-block">
                            <input type="text" id="lock_time" class="layui-input" readonly disabled
                                   value="${ito:date2string(requestScope.user.lockTime, 'yyyy-MM-dd HH:mm:ss')}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
