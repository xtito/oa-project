/**
 * 定义项目全局公用方法JS
 */
define(["jquery"], function ($) {

    var commonJs = {
        bindLoadContentEvent: function ($obj) {
            // 先取消所有click事件
            $obj.unbind("click");
            $obj.click(function () {
                var url = $(this).attr("data-url");
                if (url) {
                    commonJs.loadContent(url);
                }
            });
        },
        loadContent: function (url, param, callback) {
            $("#main_body").load(url, param, function() {
                commonJs.bindLoadContentEvent($(".location-item a, .layui-nav-item a"));
            });
        }
    };

    return commonJs;
});