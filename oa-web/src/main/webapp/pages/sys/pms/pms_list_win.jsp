<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%--
  
  Created by User: ZhangYuan
  Created Date: 2017/12/22 18:04
--%>

<script type="text/javascript">
    <!--
    require(["domReady"], function (doc) {
        require(["lay-ui"], function (lay) {
            layui.use('table', function (table) {
                table.set({checkName: "pmsChecked"});
                table.render({
                    elem: '#win_pms_table'
                    , id: 'win_assign_pms_table'
                    , url: ctx + '/mvc/sysPermission/mgr/pms/win/list'
                    , method: "post"
                    , where: {roleId: $("#role_id").val()}
                    , width: 600
                    , height: 300
                    , cols: [[
                        {type: 'checkbox'}
                        , {field: 'name', title: '权限名称', width: 180}
                        , {field: 'url', title: '权限地址'}
                    ]]
                    , response: {
                        dataName: "list"
                    }
                });
            });
        });
    });
    //-->
</script>
<div class="ito-view-con">
    <div class="view-body">
        <input type="hidden" id="role_id" value="${requestScope.roleId}"/>
        <table id="win_pms_table"></table>
    </div>
</div>