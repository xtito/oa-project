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
            "zTree": "../zTree/jquery.zTree.all.min"
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

    // 左侧菜单事件
    function activeSidebarMenu() {
        require(["jquery"], function($) {
            var $sidebar = $(".sidebar-inner");
            var $oneLevel = $sidebar.find(".sidebar-title .sidebar-title-inner");
            var $twoLevel = $sidebar.find(".sidebar-ul .nav-item");
            $oneLevel.click(function() {
                $sidebar.find(".sidebar-nav").removeClass("active");
                $(this).parents(".sidebar-nav").addClass("active");
            });

            $twoLevel.click(function() {
                $sidebar.find(".sidebar-ul .nav-item").removeClass("active");
                $(this).addClass("active");
            });
        });
    }

    loadReady();
    activeSidebarMenu();
});