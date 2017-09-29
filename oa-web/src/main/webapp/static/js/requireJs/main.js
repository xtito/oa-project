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
            "date.format": "../utils/date.format"
        }/*,
        // 不符合AMD规范的使用
        shim: {
            /!*'jquery.loadPaging': {
                deps: ['jquery'],
                exports: 'jQuery.fn.loadPaging'
            },*!/
        }*/
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
});