/**
 * requireJs 主模块
 *
 * Created by User: 张渊
 * Created Date: 2017/9/29 10:25
 */
define(function () {
    require.config({
        paths: {
            "domReady": "domReady",
            "jquery": "../jquery-1.9.1.min",
            "date.format": "../utils/date.format",
            "zTree": "../zTree/jquery.zTree.all.min",
            //"resizeHeight": "../utils/resizeHeight",
            "lay-ui": "../../ui/layui/layui",
            //"lay-element": "../../ui/layui/lay/modules/element",
            //"lay-layer": "../../ui/layui/lay/modules/layer",
            "ito-validation": "../utils/ito-validation",
            "module-common": "../module/module-common",
            "sys-user": "../module/sys/sys-user",
            "sys-department": "../module/sys/sys-department",
            "sys-role": "../module/sys/sys-role",
            "sys-pms": "../module/sys/sys-permission"
        },
        // 不符合AMD规范的使用
        shim: {
            'zTree': {
                deps: ['jquery'],
                exports: 'jQuery.fn.zTree'
            }
            /*'jquery.loadPaging': {
                deps: ['jquery'],
                exports: 'jQuery.fn.loadPaging'
            },*/
        }
    });

    var errorCount = 20;
    function loadReady() {
        // 10次没调用到则停止
        if (errorCount-- <= 0) {
            return;
        }

        if (window.onRequireReady && typeof window.onRequireReady === 'function') {
            window.onRequireReady();
        } else {
            // 若加载过快，页面js没有完成，则延时调用
            setTimeout(loadReady, 100);
        }
    }

    loadReady();

    // 左侧菜单事件
    function activeSidebarMenu() {
        require(["jquery"], function($) {
            var $sidebar = $(".sidebar-inner");
            var $oneLevel = $sidebar.find(".sidebar-title .sidebar-title-inner");
            var $twoLevel = $sidebar.find(".sidebar-ul .nav-item");
            $oneLevel.click(function() {
                var $ele = $(this).parents(".sidebar-nav");
                var exists = $ele.hasClass("active");
                $sidebar.find(".sidebar-nav").removeClass("active");
                if (!exists) {
                    $ele.addClass("active");
                }
            });

            $twoLevel.click(function() {
                $sidebar.find(".sidebar-ul .nav-item").removeClass("active");
                $(this).addClass("active");
            });
        });
    }

   /* window.resize = resizeFun;

    function resizeFun(b, callback) {
        require(["jquery", "resizeHeight"], function($, resize) {
            resize.init(b, callback)
        });
    }*/

    require(["domReady"], function (doc) {
        require(["jquery", "lay-ui"], function ($, lay) {

            // 绑定左侧菜单选中事件
            activeSidebarMenu();

            // 延迟一会加载
            setTimeout(function () {
                // 绑定layui菜单等点击事件
                layui.use('element', function () {
                    var element = layui.element;
                });
            }, 100);
        });

        //window.resize();
    });
});