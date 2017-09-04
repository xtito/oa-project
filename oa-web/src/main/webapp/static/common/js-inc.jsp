<%@ page language="java" pageEncoding="UTF-8" %>
<script src="${ctx}/static/js/jquery-1.9.1.min.js"></script>
<!--[if lt IE 9]>
<script src="/static/js/html5shiv.min.js"></script>
<![endif]-->
<script type="text/javascript">

    var ctx = "${ctx}";

    $(document).ready(function() {
        selectMenuActive();
    });

    function selectMenuActive() {
        var $ulDoc = $("#nav_menu");
        $ulDoc.find(".active").removeClass("active");
        var path = window.location.pathname;
        var $aDoc = $ulDoc.find("a[href='"+ path +"']");
        var $liDoc = $aDoc.parent();
        $liDoc.addClass("active");
    }
</script>



