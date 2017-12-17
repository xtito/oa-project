/**
 * 定义项目全局公用方法JS
 */
define(["jquery", "lay-ui"], function ($, lay) {

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

            // 绑定layui菜单等点击事件
            layui.use('element', function () {
                var element = layui.element;
                element.render();
            });
        },
        loadContent: function (url, param, callback) {
            $("#main_body").load(url, param, function() {
                commonJs.bindLoadContentEvent($(".location-item a, .layui-nav-item a"));
                if (typeof callback === "function") {
                    callback();
                }
            });
        }
    };

    return commonJs;
});