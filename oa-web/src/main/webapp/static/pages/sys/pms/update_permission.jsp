<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/static/include/inc.jsp" %>

<%--
  
  Created by User: Zy
  Created Date: 2017/12/15 23:38
--%>
<script type="text/javascript">
    <!--
    require(["domReady"], function(doc) {
        require(["sys-pms"], function(pmsJs){
            pmsJs.initBindEvent();
        });
    });
    //-->
</script>

<div class="layui-tab layui-tab-brief">
    <jsp:include page="inner_pms_menu.jsp">
        <jsp:param name="active" value="5" />
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
                        <a href="javascript:;" data-trigger-class="trigger-pms-list">
                            <i class="ito ito-permissions-list"></i><span>权限管理</span>
                        </a>
                    </li>
                    <li class="location-item">
                        <i class="ito ito-edit-permissions"></i><span>更新权限</span>
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
                                    <span class="ito ito-edit-permissions fl"></span>
                                    <h3 class="panel-title pro-title">更新权限页面</h3>
                                    <div class="s-icon xz title-icon"><span class="ito ito-chevron-up"></span></div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="site-text site-block">
                                    <form id="data_form" class="layui-form">
                                        <input type="hidden" name="id" value="${requestScope.pms.id}" />

                                        <div class="layui-form-item">
                                            <label class="layui-form-label require-field">权限名称</label>
                                            <div class="layui-input-block">
                                                <input type="text" name="name" class="layui-input" placeholder="请输入权限名称"
                                                       maxlength="30" autocomplete="off" value="${requestScope.pms.name}">
                                            </div>
                                        </div>

                                        <div class="layui-form-item">
                                            <label class="layui-form-label require-field">权限路径</label>
                                            <div class="layui-input-block">
                                                <input type="text" name="url" class="layui-input" placeholder="请输入权限路径"
                                                       maxlength="200" autocomplete="off" value="${requestScope.pms.url}">
                                            </div>
                                        </div>

                                        <div class="layui-form-item">
                                            <label class="layui-form-label" for="parent_pms">上级权限</label>
                                            <div class="layui-input-block">
                                                <input type="hidden" id="pms_id" name="pid" value="${requestScope.pms.pid}"/>
                                                <input type="text" id="parent_pms" class="layui-input" placeholder="请点击选择权限" readonly>
                                            </div>
                                        </div>

                                        <div class="layui-form-item">
                                            <label class="layui-form-label">权限图标</label>
                                            <div class="layui-input-block">
                                                <input type="text" name="icon" class="layui-input" placeholder="权限图标路径"
                                                       maxlength="200" autocomplete="off" value="${requestScope.pms.icon}">
                                            </div>
                                        </div>

                                        <div class="layui-form-item">
                                            <label class="layui-form-label">描述</label>
                                            <div class="layui-input-block">
                                        <textarea name="description" class="layui-textarea" placeholder="权限描述"
                                                  maxlength="240">${requestScope.pms.description}</textarea>
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