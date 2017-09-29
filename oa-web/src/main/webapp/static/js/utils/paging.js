/**
 * $("#inner-paging").loadPaging({
 *				'currentPage': 2,
 *				'totalPage': 11,
 *				'pagingSize': 5,
 *				'totalCount':1000,
 *				'pagingType': 'url',//分页方式,form 第一个表单提交,callback回调函数提交,url本页地址刷新传参
 *				callback: function (pageNo) {
 *				}
 *			});
 */
(function ($) {
    function parseNull(value, defult) {
        if (value == null || "" == value) {
            return defult
        }
        return value
    }

    $.fn.loadPaging = function (opitons) {
        var $this = $(this);
        var thisId = parseNull(this.id, "_" + Math.round(Math.random() * new Date().getTime()));
        var html = "";
        opitons.pagingSize = parseNull(opitons.pagingSize, 5);
        opitons.currentPage = parseNull(opitons.currentPage, 1);
        opitons.totalPage = parseNull(opitons.totalPage, 1);
        opitons.totalCount = parseNull(opitons.totalCount, 1);
        opitons.pageSize = parseNull(opitons.pageSize, 10);
        opitons.currentPage = isNaN(opitons.currentPage) ? 1 : opitons.currentPage;
        opitons.totalPage = isNaN(opitons.totalPage) ? 1 : opitons.totalPage;
        opitons.totalCount = isNaN(opitons.totalCount) ? 1 : opitons.totalCount;
        opitons.pageSize = isNaN(opitons.pageSize) ? 10 : opitons.pageSize;
        opitons.pagingSize = isNaN(opitons.pagingSize) ? 5 : opitons.pagingSize;

        opitons.pagingType = parseNull(opitons.pagingType, "url");

        var first = 1;
        var end = 1;
        var now = parseInt(opitons.currentPage);
        var max = parseInt(opitons.totalPage);
        now = now < 1 ? 1 : now;
        max = max < 1 ? 1 : max;
        now = now > max ? max : now;
        var size = parseInt(opitons.pagingSize);
        if (now - Math.ceil(size / 2) < 1) {
            first = 1;
            end = size > max ? max : size
        } else {
            if (now + Math.ceil(size / 2) > max) {
                end = max;
                first = size >= max ? 1 : max - size + 1
            } else {
                first = now - Math.ceil(size / 2) + 1;
                end = first + size - 1
            }
        }
        html += '<ul class="pagination" id=' + (thisId) + "><li " + (now <= 1 ? ' class="disabled"' : "") + "><a href='javascript:void(0);'  " + ((now <= 1 ? "" : "data-paging='1'")) + ">首页</a></li>";
        html += "<li " + (now - 1 <= 0 ? ' class="disabled"' : "") + " data-paging='" + (now - 1) + "'><a href='javascript:void(0);' " + (now - 1 <= 0 ? "" : "data-paging='" + (now - 1) + "'") + " >上一页</a></li>";

        for (var i = first; i <= end; i++) {
            html += "<li " + (i == now ? 'class="active"' : "") + "><a href='javascript:void(0);' data-paging='" + i + "'>" + i + "</a></li>";
            if (end + 1 < max && i == end) {
                html += "<li class='disabled'><a>...</a></li>"
            }
            if (end < max && i == end) {
                html += "<li><a href='javascript:void(0);' data-paging='" + max + "' >" + max + "</a></li>"
            }
        }
        html += "<li " + (now + 1 > max ? ' class="disabled"' : "") + " data-paging='" + (now + 1) + "'><a href='javascript:void(0);' " + (now + 1 > max ? "" : "data-paging='" + (now + 1) + "'") + " >下一页</a></li>";
        if (end < 1) {
            html += "<li " + (i == now ? 'class="active"' : "") + "><a href='javascript:void(0);' data-paging='" + i + "' >" + i + "</a></li>"
        }
        html += "<li ";
        html += now >= end ? " class='disabled' " : "";
        html += "><a href='javascript:void(0);' " + (now >= end ? "" : " data-paging='" + max + "'") + " >末页</a></li>" + (opitons.totalCount ? '<li class="info"><a class="info" href="javascript:void(0);">' + opitons.pageSize + '条/页,共' + opitons.totalCount + "条</a></li>" : "") + "</ul>";
        $this.html(html);
        $this.addClass("paging");
        if (max <= 1) {
            $this.hide()
        } else {
            $this.show()
        }
        var clickF = function () {
            var $this = $(this);
            if (!$this.attr("data-paging")) {
                return
            }
            var paging = parseInt($this.attr("data-paging"));
            go(paging)
        };
        $("#" + thisId + " li>a,#" + thisId + " .button_pading_span>button").click(clickF);
        if (window.resize && typeof window.resize === "function") {
            window.resize()
        }
        function go(paging) {
            if (opitons.pagingType == "callback") {
                opitons.callback(paging)
            } else {
                if (opitons.pagingType == "url") {
                    var url = location.toString();
                    var params = (location.toString().indexOf("?") > 0 ? location.toString().indexOf("?pageNo=") > 0 ? "" : "&" : "?") + "pageNo=" + paging;
                    if (url.indexOf("pageNo=") > 0) {
                        url = url.replace(/[\&]?pageNo=[^\&]+/g, params)
                    } else {
                        url += params
                    }
                    window.location.href = url
                } else {
                    var $defForm = $("form:first");
                    var inputs = $defForm.find("input");
                    var pageNo = -1;
                    for (var i = 0; i < inputs.length; i++) {
                        if (inputs[i].name == "pageNo") {
                            pageNo = (inputs[i].value = paging)
                        }
                    }
                    if (pageNo == -1) {
                        $defForm.append("<input type='hidden' name='pageNo' value='" + paging + "' >")
                    }
                    $defForm.submit()
                }
            }
            opitons.currentPage = parseInt($(this).attr("data-paging"));
            if (window.resize && typeof window.resize === "function") {
                window.resize()
            }
        }
    }
})($);