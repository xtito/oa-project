<%@ page language="java" pageEncoding="UTF-8" %>
<%--
    width = device-width：宽度等于当前设备的宽度
    initial-scale：初始的缩放比例（默认设置为1.0）
    minimum-scale：允许用户缩放到的最小比例（默认设置为1.0）
    maximum-scale：允许用户缩放到的最大比例（默认设置为1.0）
    user-scalable：用户是否可以手动缩放（默认设置为no，因为我们不希望用户放大缩小页面）
--%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!-- 设置IE渲染方式默认为最高，避免IE9使用IE8文档模式渲染， chrome：如果有安装谷歌浏览器插件，则以Webkit引擎及V8引擎进行排版及运算 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<link rel="shortcut icon" type="image/x-icon" href="${ctx}/static/images/logo/favicon.ico"/>
<%--<link rel="stylesheet" href="${ctx}/static/css/bootstrap/bootstrap.min.css">--%>
<link rel="stylesheet" href="${ctx}/static/css/zTree/zTree.css" />
<link rel="stylesheet" href="${ctx}/static/css/main-public.css" />
<link rel="stylesheet" href="${ctx}/static/ui/layui/css/layui.css" />
<link rel="stylesheet" href="${ctx}/static/js/plugins/layer/theme/default/layer.css" />
<link rel="stylesheet" href="${ctx}/static/ui/layui/css/global.css" />
<link rel="stylesheet" href="${ctx}/static/js/plugins/select2/css/select2.css" />
<script src="${ctx}/static/js/requireJs/require.min.js" data-main="${ctx}/static/js/requireJs/main" defer async></script>
<!--[if lt IE 9]>
<script src="../static/js/html5shiv.min.js"></script>
<script src="../static/js/respond.min.js"></script>
<![endif]-->

<script type="text/javascript">
    var ctx = "${ctx}";
</script>
