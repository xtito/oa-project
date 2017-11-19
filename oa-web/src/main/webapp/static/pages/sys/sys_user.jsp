<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/static/include/inc.jsp"%>

<script type="text/javascript">
    <!--
    require(["domReady"], function(doc) {
        require(["lay-ui"], function(){
            layui.use('table', function(){
                var table = layui.table
                        ,form = layui.form;

                /*table.render({
                    elem: '#test',
                    url: ctx + '/static/json/demo1.json',
                    cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                    cols: [[
                        {field:'id', title: 'ID', sort: true},
                        {field:'username', title: '用户名'}, //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                        {field:'sex', title: '性别', sort: true},
                        {field:'city', title: '城市'},
                        {field:'sign', title: '签名'},
                        {field:'classify', title: '职业', align: 'center'}, //单元格内容水平居中
                        {field:'experience', title: '积分', sort: true, align: 'right'}, //单元格内容水平居中
                        {field:'score', title: '评分', sort: true, align: 'right'},
                        {field:'wealth', title: '财富', sort: true, align: 'right'}
                    ]]
                });*/

                /*table.render({
                    elem: '#test'
                    ,url: ctx + '/static/json/demo1.json'
                    ,cellMinWidth: 80
                    ,cols: [[
                        {type:'numbers'}
                        ,{type: 'checkbox'}
                        ,{field:'id', title:'ID', width:100, unresize: true, sort: true}
                        ,{field:'username', title:'用户名', templet: '#usernameTpl'}
                        ,{field:'city', title:'城市'}
                        ,{field:'wealth', title: '财富', minWidth:120, sort: true}
                        ,{field:'sex', title:'性别', width:85, templet: '#switchTpl', unresize: true}
                        ,{field:'lock', title:'是否锁定', width:110, templet: '#checkboxTpl', unresize: true}
                    ]]
                    ,page: true
                });

                //监听性别操作
                form.on('switch(sexDemo)', function(obj){
                    layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
                });

                //监听锁定操作
                form.on('checkbox(lockDemo)', function(obj){
                    layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
                });*/

                table.render({
                    elem: '#test'
                    ,url: ctx + '/static/json/demo1.json'
                    ,page: {}
                    //,height: 300
                    ,cols: [[
                        {type:'numbers'}
                        ,{type: 'checkbox'}
                        ,{field:'id', title:'ID', width:100, unresize: true, sort: true}
                        ,{field:'username', title:'用户名', templet: '#usernameTpl'}
                        ,{field:'email', title:'邮箱'}
                        ,{field:'sex', title:'性别', width:85, templet: '#switchTpl'}
                        ,{field:'lock', title:'是否锁定', width:110, templet: '#checkboxTpl'}
                        ,{field:'city', title:'城市', width: 100}
                        //,{field:'sign', title:'签名', width:150}
                        //,{field:'experience', title:'积分', width:80, sort: true}
                        //,{field:'ip', title:'IP', width:120}
                        //,{field:'logins', title:'登入次数', width:100, sort: true}
                        //,{field:'joinTime', title:'加入时间', width:120}
                        //,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
                    ]]
                });
            });
        });
    });

    function addUI() {
        loadContent("${ctx}/static/pages/sys/add_user.jsp");
    }
    //-->
</script>

<div class="ito-inner-body">
    <div class="ito-home-section">
        <div class="ito-home-address">
            <div class="ito-home-location">
                <ol>
                    <li class="location-item">
                        <a><i class="ito ito-home"></i><span>首页</span></a>
                    </li>
                </ol>
            </div>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-main">
            <table class="layui-hide" id="test"></table>

            <div>
                <button type="button" class="layui-btn" onclick="addUI()">新增</button>
            </div>

            <script type="text/html" id="switchTpl">
                <input type="checkbox" name="yyy" lay-skin="switch" lay-text="女|男">
            </script>

            <script type="text/html" id="checkboxTpl">
                <input type="checkbox" name="" title="锁定" checked>
            </script>
        </div>
    </div>
</div>