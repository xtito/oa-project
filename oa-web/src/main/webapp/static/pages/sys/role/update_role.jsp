<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/static/include/inc.jsp" %>

<%--
  
  Created by User: Zy
  Created Date: 2017/12/15 21:35
--%>

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
                        <a href="javascript:;" data-url="${ctx}/static/pages/sys/role/sys_role.jsp">
                            <i class="ito ito-role-list"></i><span>角色管理</span>
                        </a>
                    </li>
                    <li class="location-item">
                        <i class="ito ito-update-role"></i><span>添加角色</span>
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
                            <span class="ito ito-update-role fl"></span>
                            <h3 class="panel-title pro-title">更新角色页面</h3>
                            <div class="s-icon xz title-icon"><span class="ito ito-chevron-up"></span></div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="site-text site-block">
                            <form id="data_form" class="layui-form">
                                <input type="hidden" name="id" value="${requestScope.role.id}" />

                                <div class="layui-form-item">
                                    <label class="layui-form-label require-field">角色名</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="name" class="layui-input" placeholder="请输入角色名"
                                               maxlength="30" autocomplete="off" value="${requestScope.role.name}">
                                    </div>
                                </div>

                                <div class="layui-form-item layui-form-text">
                                    <label class="layui-form-label">角色描述</label>
                                    <div class="layui-input-block">
                                        <textarea name="description" placeholder="请输入角色备注" class="layui-textarea"
                                                  maxlength="300" >${requestScope.role.description}</textarea>
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
