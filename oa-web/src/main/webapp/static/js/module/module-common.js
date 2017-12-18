/**
 * 定义项目全局公用方法JS
 */
define(["jquery", "lay-ui"], function ($, lay) {

    var commonJs = {
        bindLoadContentEvent: function ($obj) {
            // 绑定加载tab标签页区域内容事件
            // 先取消所有click事件
            $obj.unbind("click");
            $obj.click(function () {
                var url = $(this).attr("data-url");
                if (url) {
                    commonJs.loadContent(url);
                }
            });

            commonJs.bindMenuEvent();
        },
        bindMenuEvent: function () {
            // 绑定layui菜单等点击事件
            layui.use('element', function () {
                var element = layui.element;
                element.render();
            });
        },
        bindLocationAddressEvent: function ($obj) {
            // 绑定地址栏模拟点击事件
            $obj.unbind("click");
            $obj.click(function () {
                var triggerClass = $(this).attr("data-trigger-class");
                var triggerId = $(this).attr("data-trigger-id");
                var element = triggerClass ? ("." + triggerClass) : triggerId ? ("#" + triggerId) : "";
                if (element) {
                    $(element).trigger("click");
                }
            });
        },
        loadContent: function (url, param, callback) {
            $("#main_body").load(url, param, function() {
                commonJs.bindLoadContentEvent($(".layui-nav-item .layui-nav-child a, .layui-tab-title a"));

                // 当主要业务页面加载完毕后，绑定右侧地址导航元素事件，查找data-trigger-class和data-trigger-id属性触发对应class和id的事件
                commonJs.bindLocationAddressEvent($(".location-item a"));

                if (typeof callback === "function") {
                    callback();
                }
            });
        }
    };

    return commonJs;
});