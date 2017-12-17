define(["jquery", "select2", "select2-zh-CN"], function($, select, zh) {

    var selectUtil = {
        _param : {},
        selectEle: null,
        handleEle: function(ele) {
            // 处理元素
            if (ele) {
                var str = ele.substr(0, 1);
                if (str != "." && str != "#") {
                    return "#" + ele;
                } else {
                    return ele;
                }
            }
        },
        bindSelect: function(ele, url, serverFind) {
            // ele: 绑定的元素， url:获取数据的服务器地址，  servlerFind：是否从服务器查询数据

            // 处理元素，如果不是.或#开头，默认转换为#+元素
            var obj = selectUtil.handleEle(ele);
            var $control = $(obj);

            if ($control) {
                var settings = {
                    width: '100%',
                    tags: true,
                    allowClear: true,
                    language: "zh-CN",
                    templateResult: selectUtil.templateResult,
                    templateSelection: selectUtil.templateSelection,
                    escapeMarkup: selectUtil.escapeMarkup,
                    maximumSelectionLength: 3//最多能够选择的个数
                };

                $.extend(settings, selectUtil.loadDataAndFindServer(serverFind, url));

                selectUtil.selectEle = $control.select2(settings);

                if (!serverFind || serverFind == false) {
                    selectUtil.loadData($control, url);
                }
            }

            return selectUtil.selectEle;
        },
        loadDataAndFindServer: function(serverFind, url) {
            if (serverFind && serverFind === true) {
                return {
                    ajax: {
                        url: url,
                        dataType: "json",
                        data: function (params) {
                            return {name: params.term};
                        },
                        processResults: function (data, params) {
                            return {results: data, params: params}
                        },
                        cache: true
                    }
                };
            }
        },
        loadData: function($control, url) {
            // 非远程查询，在页面查询
            $.getJSON(url, function(data) {
                $control.empty();// 清空下拉框
                $.each(data, function(index, item) {
                    $control.append("<option value='"+ item.id +"'>"+ item.text +"</option>");
                });
            });
        },
        templateResult: function(state) {
            // 该方法是在下拉的时候下拉列表中的格式
            // 官网是 state.id
            if (state.loading) {
                return state.text;
            }
            // state.element.value
            return "<span class='ito-select-span'><i class='le-icon icon-group f16'></i>" +
                "<span class='ito-text'>"+ state.text +"</span></span>";
        },
        templateSelection: function(repo) {
            // 该方法用于用户选中选项后在显示框显示的格式
            // 官网是 state.id
            if (repo.loading) {
                return repo.text;
            }
            return "<span class='ito-select-span'><i class='le-icon icon-group f16'></i>" +
                "<span class='ito-text'>"+ repo.text +"</span></span>";
        },
        escapeMarkup: function (markup) {
            // 处理自动转义的内容呈现自定义模板.
            return markup;
        }
    };



    return selectUtil;
});