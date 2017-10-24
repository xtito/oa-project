define(["jquery"], function($) {
    var resize = {
        init: function(b, callback) {
            try {
                // 获取顶层滚动条所在位置的值
                var scroll = $(window.top.document).scrollTop();

                // 判断地址是否相等
                if (top.location === location) {
                    /*try {
                     sysnRightHeight(j);
                     } catch (e) {
                     }*/
                    return resize.logger("此页面是最顶层页面，终止resize!", 2)
                }

                var IFrame = null;
                // 获取父窗体中的IFrame元素
                var tempIFrame = window.parent.$("iframe");
                if (!tempIFrame) {
                    return resize.logger("没有找到对应该的iframe!", 3)
                }

                // 循环取IFrame，得到当前页面的父级IFrame
                for (var i = 0; i < tempIFrame.length; i++) {
                    if (tempIFrame[i].contentWindow == window) {
                        IFrame = tempIFrame[i];
                        break
                    }
                }

                if (!IFrame) {
                    return resize.logger("没有找到对应该的iframe!", 3)
                }

                resize.logger("iframe -- >id:" + IFrame.id, 2);

                if (b && typeof b == "object") {
                } else {
                    // 此处如果重置初始值为0，部分IE8会获取不到文档高度，导致计算页面高度失败
                    $(IFrame).attr("height", 5);
                    $(IFrame).css("height", 5);
                    $(IFrame).height(5);
                }

                var docHeight = $(document).height();
                var bodyHeight = $(document.body).height();
                var calculationHeight = docHeight > bodyHeight ? docHeight : bodyHeight;

                if (/*b == true && */calculationHeight < 700) {
                    calculationHeight = 700
                }

                if (b && typeof b == "object" && b[0]) {
                    calculationHeight = b[0].scrollHeight > calculationHeight ? b[0].scrollHeight : calculationHeight;
                    b.height(calculationHeight);
                    resize.logger("resize Object id :" + b.attr("id") + ": height:" + calculationHeight + "px")
                }

                // 取得浏览器的userAgent字符串 根据浏览器增加高度
                /*var userAgent = navigator.userAgent;
                 if (userAgent.indexOf("Opera") > -1 || userAgent.indexOf("Safari") > -1) {
                 calculationHeight += 60;// 未测试
                 } else if (userAgent.indexOf("Firefox") > -1) {
                 calculationHeight += 30;
                 } else if (userAgent.indexOf("Chrome") > -1) {
                 calculationHeight += 80;
                 } else if (userAgent.indexOf("compatible") > -1
                 && userAgent.indexOf("MSIE") > -1
                 && !userAgent.indexOf("Opera") > -1) {
                 calculationHeight += 80;
                 }*/

                calculationHeight += 30;
                $(IFrame).attr("height", calculationHeight);
                $(IFrame).css("height", calculationHeight);
                $(IFrame).height(calculationHeight);
                resize.logger("iframe -- >id:" + IFrame.id + ", resize:" + calculationHeight + "px", 1);


                if (top.location != location) {
                    window.parent.resize();
                }

                /*try {
                 sysnRightHeight(calculationHeight);
                 } catch (e) {
                 }*/

                $(window.top.document).scrollTop(scroll);
            } catch (g) {
                resize.logger("resize:" + g.message, 1)
            }
        },
        logger: function(str, type) {
            try {
                if (!type || type == 1) {
                    console.log(str);
                } else if (type == 2) {
                    console.info(str);
                } else if (type == 3) {
                    console.error(str);
                } else {
                    console.log(str);
                }
            } catch (e) {
                return e;
            }
            return null;
        }
    };

    return resize;
});