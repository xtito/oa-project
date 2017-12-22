<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%--
  
  Created by User: ZhangYuan
  Created Date: 2017/12/21 22:04
--%>

<script type="text/javascript">
    <!--
    require(["domReady"], function (doc) {
        require(["lay-ui"], function (lay) {
            layui.use('table', function (table) {
                table.render({
                    elem: '#win_role_table'
                    , id: 'win_assign_role_table'
                    , url: ctx + '/mvc/sysRole/mgr/list'
                    , method: "post"
                    , width: 360
                    , page: false
                    , cols: [[
                        {type: 'checkbox'}
                        , {field: 'name', title: '角色名称'}
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
<div class="con-layout">
    <div class="win-role-body">
        <table id="win_role_table"></table>
    </div>
</div>