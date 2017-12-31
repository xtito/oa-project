<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc.jsp" %>

<%--
  
  Created by User: Zy
  Created Date: 2017/11/25 17:28
--%>
<script type="text/javascript">
    <!--
    require(["domReady"], function(doc) {
        require(["sys-department"], function(deptJs){
            deptJs.initBindEvent();
        });
    });
    //-->
</script>

<div class="layui-tab layui-tab-brief">
    <jsp:include page="inner_department_menu.jsp">
        <jsp:param name="active" value="3" />
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
                        <a href="javascript:;" data-trigger-class="trigger-dept-list">
                            <i class="ito ito-department"></i><span>部门管理</span>
                        </a>
                    </li>
                    <li class="location-item">
                        <i class="ito ito-edit"></i><span>更新部门</span>
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
                                    <span class="ito ito-edit fl"></span>
                                    <h3 class="panel-title pro-title">更新部门页面</h3>
                                    <div class="s-icon xz title-icon"><span class="ito ito-chevron-up"></span></div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="site-text site-block">
                                    <form id="data_form" class="layui-form" method="post">
                                        <input type="hidden" name="id" value="${requestScope.dept.id}" />

                                        <div class="layui-form-item">
                                            <label class="layui-form-label require-field">部门名称</label>
                                            <div class="layui-input-block">
                                                <input type="text" name="name" class="layui-input" placeholder="请输入用户名"
                                                       maxlength="50" autocomplete="off" value="${requestScope.dept.name}">
                                            </div>
                                        </div>

                                        <div class="layui-form-item">
                                            <label class="layui-form-label">上级部门</label>
                                            <div class="layui-input-block">
                                                <input type="hidden" id="dept_id" name="parentId" value="${requestScope.dept.parentId}"/>
                                                <input type="text" id="select_dept" class="layui-input" placeholder="请点击选择上级部门"
                                                       readonly value="${ito:getDepartmentNameById(requestScope.dept.parentId)}">
                                            </div>
                                        </div>

                                        <div class="layui-form-item layui-form-text">
                                            <label class="layui-form-label">部门描述</label>
                                            <div class="layui-input-block">
                                        <textarea name="description" placeholder="请输入部门备注" maxlength="300"
                                                  class="layui-textarea">${requestScope.dept.description}</textarea>
                                            </div>
                                        </div>

                                        <div class="layui-form-item">
                                            <div class="layui-input-block">
                                                <button type="button" id="update_btn" class="layui-btn mr20">立即提交
                                                </button>
                                                <button type="reset" class="layui-btn layui-btn-primary mr20">重置</button>
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