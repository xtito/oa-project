(function ($) {

    var myUpload;

    function MyUpload() {
        this._this = null;
        this.data = {};
        this.settings = {};
        this.uploader = null;
        this.addFileToUpTool = function(file) {
            var that = this;
            var template = "<div id='WU_FILE_" + file.size + "' class='upload-con' data-id='" + file.id + "'>" +
                "   <h4 class='f-col f-name' title='" + file.name + "'>" + file.name + "</h4>" +
                "   <span class='f-col f-size' title='" + file.size + "'>" + file.size + "</span>" +
                "   <div class='f-col progress'>" +
                "       <div class='progress-striped active'>" +
                "           <div class='progress-bar' role='progressbar' style='width: 100%;'></div>" +
                "       </div>" +
                "   </div>" +
                "   <p class='f-col f-state success'>上传完毕</p>" +
                "   <div class='f-col f-buts'>" +
                "       <i class='remove icon-trash'></i>" +
                "   </div>" +
                "</div>";

            var $obj = $(this._this);
            var $list = $obj.find(".uploader-list");
            $list.children(".default-text").remove();
            $list.append(template);

            var $remove = $list.find(".f-buts .remove");
            $remove.unbind("click");

            $remove.on("click", function () {
                if (confirm("删除后不可恢复,确认删除该文件?")) {
                    that.removeFile(this);
                }
            });
        };
        this.addFileList = function(obj, files) {
            var liHtml = "";
            if (files != undefined && files != null && files.length > 0) {
                for (var i=0; i<files.length; i++) {
                    var file = files[i];
                    var id = file.id;
                    var name = file.name;
                    var iconPath = ctx + "/static/images/file/file.png";

                    if (name) {
                        var suffix = name.substr(name.lastIndexOf("."), name.length);
                        if (suffix.indexOf("doc") >= 0) {
                            iconPath = ctx + "/images/fileIcon/doc.png";
                        } else if (suffix.indexOf("xls") >= 0) {
                            iconPath = ctx + "/images/fileIcon/xls.png";
                        } else if (suffix.indexOf("ppt") >= 0) {
                            iconPath = ctx + "/images/fileIcon/ppt.png";
                        } else if (suffix.indexOf("pdf") >= 0) {
                            iconPath = ctx + "/images/fileIcon/pdf.png";
                        } else if (suffix.indexOf("jpg") >= 0 || suffix.indexOf("bmp") >= 0 || suffix.indexOf("png") >= 0) {
                            iconPath = ctx + "/images/fileIcon/jpg.png";
                        }
                    }

                    var title = "文件大小：" + file.size + "  文件名：" + file.name;

                    liHtml+= "<li class='down-file' data-id='"+ id +"' title='"+ title +"'>" +
                    "    <div><img src='"+ iconPath +"'></div>" +
                    "    <div class='con'>"+ file.name +"</div>" +
                    "</li>";
                }

            }

            var html = "<div class='files'><ul>" + liHtml + "</ul></div>";
            var $obj = $(obj);
            $obj.append(html);
            var $downLi = $(".down-file");
            $downLi.unbind("click");
            $downLi.bind("click", function() {
                var id = $(this).attr("data-id");
                window.location.href = ctx + "/fileAct!downloadFile?id=" + id;
            });
        };
        this.loadFile = function (bizType, bizId, obj) {
            var that = this;
            // 加载文件列表
            $.post(ctx + "/fileAct!listFile", {bizType: bizType, bizId: bizId}, function (responseText) {
                var list = $.parseJSON(responseText);
                var fileList = [];
                for (var i = 0; i < list.length; i++) {
                    var _file = list[i].upFile;
                    var file = {
                        id: _file.fileId,
                        name: _file.uploadName,
                        size: _file.lengthDb
                    };
                    fileList.push(file);
                    if (!obj) {
                        that.addFileToUpTool(file);
                    }
                }

                if (obj) {
                    that.addFileList(obj, fileList);
                }

            })
        };
        this.removeFile = function (obj) {

            // 删除文件
            var $parent = $(obj).parents(".upload-con");
            var $state = $parent.find(".f-state");
            var $listD = $parent.parent();

            var length = $listD.find(".upload-con").length;// 文件列表数
            var stateText = $state.text();
            var id = $parent.attr("data-id");
            stateText = stateText.substr(0, 4);
            var html = "<p class='default-text'>" + this.settings.upTitle + "</p>";

            if (stateText != null && stateText != undefined && stateText == "等待上传") {
                $parent.remove();

                if (length == 1) {
                    $listD.append(html);
                }
            } else {
                $.ajax({
                    url: ctx + "/fileAct!deleteFile",
                    type: "POST",
                    data: {id: id},
                    dataType: "json",
                    success: function (json) {
                        if (json.success) {
                            $parent.remove();

                            if (length == 1) {
                                $listD.append(html);
                            }
                        } else {
                            alert(json.info);
                        }
                    }, error: function () {
                        alert("删除上传文件异常，请重试");
                    }
                });
            }

            // 如果上传成功的队列中有相关数据也删除，否则会额外保存关联关系
            var $upPlugins = $parent.parents(".uploader-plugins");
            var $upEle = $upPlugins.parent();
            var upId = $upEle.attr("id");
            var upData = this.data[upId];
            if (upData != undefined && upData != null && upData.length > 0) {
                for (var i=0; i<upData.length; i++) {
                    if (id == upData[i].id) {
                        upData.splice(i, 1);
                    }
                }
            }
        };
        this.saveCascade = function (options) {

            var param = {
                id: -1,
                type: ""
            };

            $.extend(param, options);

            var inputHtml = "";
            var upId = $(this._this).attr("id");
            var files = myUpload.data[upId];

            if (files != null && files != undefined && files.length > 0) {
                for (var i = 0; i < files.length; i++) {
                    var obj = files[i];
                    inputHtml += "<input type='hidden' name='ass[" + i + "].upFile.fileId' value='" + obj.id + "'/>" +
                        "<input type='hidden' name='ass[" + i + "].fileType' value='" + obj.type + "'/>" +
                        "<input type='hidden' name='ass[" + i + "].fileName' value='" + obj.name + "'/>" +
                        "<input type='hidden' name='ass[" + i + "].entryId' value='" + param.id + "'/>" +
                        "<input type='hidden' name='ass[" + i + "].entryTable' value='" + param.type + "'/>";
                }

                var html = "<form id='form_data'><div id='data_list'>" + inputHtml + "</div></form>";

                var $dataList = $("#data_list");
                if ($dataList == undefined || $dataList.length == 0) {
                    $("body").append(html);
                }

                var $form = $("#form_data");

                $.ajax({
                    url: ctx + "/fileAct!saveFileAssociations",
                    type: "POST",
                    data: $form.serialize(),
                    dataType: "json",
                    async: false,
                    success: function (json) {
                        if (json.success) {
                            // 先删除临时存储的数据
                            delete myUpload.data[upId];
                        }
                    }, error: function () {
                        alert("保存文件关系异常，请重试");
                    }, complete: function () {
                        $form.remove();
                    }
                });
            }

        };

        this.methods = {
            create: function (settings) {
                var $obj = $(myUpload._this);
                var selectId = "uploader_select_" + settings.id;
                var butId = "uploader_but_" + settings.id;

                $.extend(settings, {selectId: selectId});

                var html = "<div class='uploader-plugins'>" +
                    "    <div class='plugin'>" +
                    "        <div class='uploader-list'>" +
                    "            <p class='default-text'>" + settings.upTitle + "</p>" +
                    "        </div>" +
                    "        <div class='buts'>" +
                    "            <div id='" + selectId + "' class='picker'>选择文件</div>" +
                    "            <button id='" + butId + "' type='button' class='but but-default ml20'>开始上传</button>" +
                    "        </div>" +
                    "    </div>" +
                    "</div>";
                $obj.append(html);
            },
            init: function (options) {

                myUpload._this = this;

                // 初始化
                // 参数扩展
                this.each(function () {

                    var $this = $(this);
                    myUpload.settings = $.extend({
                        id: $this.attr("id"),
                        upTitle: "请选择要上传的文件",
                        fileNumLimit: 5,
                        accept: {title: "所有文件", extensions: "*", mimeTypes: "*"}
                    }, options);

                    var settings = myUpload.settings;

                    myUpload.methods.create(settings);// 创建元素

                    var state = 'pending';
                    var $btn = $this.find(".buts .but");

                    window["uploader_" + settings.id] = WebUploader.create({

                        // 不压缩image
                        resize: false,

                        // swf文件路径
                        swf: ctx + '/js/upload/Uploader.swf',

                        // 文件接收服务端。
                        server: ctx + '/fileAct!uploadFile',

                        // 选择文件的按钮。可选。
                        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                        pick: '#' + settings.selectId,
                        // 文件队列最大值
                        fileNumLimit: settings.fileNumLimit,
                        accept: settings.accept
                    });

                    var uploader = window["uploader_" + settings.id];

                    // 当有文件添加进来的时候
                    uploader.on('fileQueued', function (file) {
                        var sizeStr = "";
                        var fileSize = file.size;
                        var kbSize = 1024;// KB单位
                        var mbSize = kbSize * 1024;// MB单位
                        var gbSize = mbSize * 1024;// GB单位
                        var pbSize = gbSize * 1024;// PB单位
                        if (fileSize >= kbSize && fileSize < mbSize) {
                            sizeStr = (fileSize / kbSize).toFixed(2) + " KB";
                        } else if (fileSize >= mbSize && fileSize < gbSize) {
                            sizeStr = (fileSize / mbSize).toFixed(2) + " MB";
                        } else if (fileSize >= gbSize && fileSize < pbSize) {
                            sizeStr = (fileSize / gbSize).toFixed(2) + " GB";
                        } else if (fileSize >= pbSize) {
                            sizeStr = (fileSize / pbSize).toFixed(2) + " PB";
                        } else {
                            sizeStr = fileSize + " 字节";
                        }

                        var $list = $this.find(".uploader-list");
                        $list.children(".default-text").remove();
                        $list.append('<div id="' + file.id + '" class="upload-con">' +
                            '   <h4 class="f-col f-name" title="' + file.name + '">' + file.name + '</h4>' +
                            '   <span class="f-col f-size" title="' + sizeStr + '">' + sizeStr + '</span>' +
                            '   <div class="f-col progress">' +
                            '       <div class="progress-striped active">' +
                            '           <div class="progress-bar" role="progressbar" style="width: 0;"></div>' +
                            '       </div>' +
                            '   </div>' +
                            '   <p class="f-col f-state">等待上传...</p>' +
                            '   <div class="f-col f-buts">' +
                            '       <i class="remove icon-trash"></i>' +
                            '   </div>' +
                            '</div>');

                        var $remove = $list.find(".f-buts .remove");
                        $remove.unbind("click");

                        $remove.on("click", function () {
                            if (confirm("删除后不可恢复,确认删除该文件?")) {
                                var $fileEle = $(this).parents(".upload-con");
                                var fileId = $fileEle.attr("id");
                                uploader.removeFile(fileId, true);
                                myUpload.removeFile(this);
                            }
                        });

                        myUpload.uploader = uploader;
                    });

                    // 文件上传过程中创建进度条实时显示。
                    uploader.on('uploadProgress', function (file, percentage) {
                        var $li = $('#' + file.id);
                        var $percent = $li.find('.progress-striped .progress-bar');

                        // 避免重复创建
                        if (!$percent.length) {
                            var html = '<div class="progress-striped active">' +
                                '   <div class="progress-bar" role="progressbar" style="width: 0;"></div>' +
                                '</div>';
                            var $progress = $li.find(".progress");
                            $progress.append(html);
                            $percent = $li.find('.progress-bar');
                        }

                        var $state = $li.find('p.f-state');
                        $state.addClass("have-in-hand");
                        $state.text('上传中..');

                        $percent.css('width', percentage * 100 + '%');
                    });

                    uploader.on('uploadSuccess', function (file, response) {
                        var $state = $('#' + file.id).find('p.f-state');
                        $state.removeAttr("class");
                        $state.attr("class", "f-col f-state success");
                        $state.text('上传完毕');

                        var $parents = $state.parents(".upload-con");
                        $parents.attr("data-id", response.id);

                        // 获取文件上传模块区域的DIV属性ID值
                        var upId = $this.attr("id");
                        var tempData = myUpload.data;
                        var upData;
                        // 如果对象存在
                        if (tempData[upId]) {
                            upData = myUpload.data[upId];
                        } else {
                            tempData[upId] = [];
                            upData = myUpload.data[upId];
                        }

                        var fileObj = {id: response.id, type: file.type, name: file.name};
                        upData.push(fileObj);
                    });

                    uploader.on('uploadError', function (file) {
                        var $state = $('#' + file.id).find('p.f-state');
                        $state.removeAttr("class");
                        $state.attr("class", "f-col f-state failed");
                        $state.text('上传出错');
                    });

                    /*uploader.on('uploadComplete', function (file) {
                     $('#' + file.id).find('.progress-striped').fadeOut();
                     });*/

                    uploader.on('all', function (type) {
                        if (type === 'startUpload') {
                            state = 'uploading';
                        } else if (type === 'stopUpload') {
                            state = 'paused';
                        } else if (type === 'uploadFinished') {
                            state = 'done';
                        }

                        if (state === 'uploading') {
                            $btn.text('暂停上传');
                        } else {
                            $btn.text('开始上传');
                        }
                    });

                    $btn.on('click', function () {
                        if (state === 'uploading') {
                            uploader.stop();
                        } else {
                            uploader.upload();
                        }
                    });

                    $this.data("uploader", uploader);
                });
            },
            loadFileList: function(bizType, bizId, obj) {
                myUpload.loadFile(bizType, bizId, obj);
            }
        };
    }

    $.fn.uploadFile = function (method) {
        myUpload = new MyUpload();
        var methods = myUpload.methods;
        if (methods[method]) {
            methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
            return myUpload;
        } else if (typeof method === 'object' || !method) {
            methods.init.apply(this, arguments);
            return myUpload;
        } else {
            $.error('这个方法 ' + method + ' 不是本插件的方法');
        }
    };

})($);


// 图片上传demo
//jQuery(function () {
//    var $ = jQuery,
//        $list = $('#fileList'),
//    // 优化retina, 在retina下这个值是2
//        ratio = window.devicePixelRatio || 1,
//
//    // 缩略图大小
//        thumbnailWidth = 100 * ratio,
//        thumbnailHeight = 100 * ratio,
//
//    // Web Uploader实例
//        uploader;
//
//    // 初始化Web Uploader
//    uploader = WebUploader.create({
//
//        // 自动上传。
//        auto: true,
//
//        // swf文件路径
//        swf: ctx + '/js/upload/Uploader.swf',
//
//        // 文件接收服务端。
//        server: ctx + '/fileAct!uploadImage',
//
//        // 选择文件的按钮。可选。
//        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
//        pick: '#filePicker',
//
//        // 只允许选择文件，可选。
//        accept: {
//            title: 'Images',
//            extensions: 'gif,jpg,jpeg,bmp,png',
//            mimeTypes: 'image/*'
//        }
//    });
//
//    // 当有文件添加进来的时候
//    uploader.on('fileQueued', function (file) {
//        var $li = $(
//                '<div id="' + file.id + '" class="file-item thumbnail">' +
//                '<img>' +
//                '<div class="info">' + file.name + '</div>' +
//                '</div>'
//            ),
//            $img = $li.find('img');
//
//        $list.append($li);
//
//        // 创建缩略图
//        uploader.makeThumb(file, function (error, src) {
//            if (error) {
//                $img.replaceWith('<span>不能预览</span>');
//                return;
//            }
//
//            $img.attr('src', src);
//        }, thumbnailWidth, thumbnailHeight);
//    });
//
//    // 文件上传过程中创建进度条实时显示。
//    uploader.on('uploadProgress', function (file, percentage) {
//        var $li = $('#' + file.id),
//            $percent = $li.find('.progress span');
//
//        // 避免重复创建
//        if (!$percent.length) {
//            $percent = $('<p class="progress"><span></span></p>')
//                .appendTo($li)
//                .find('span');
//        }
//
//        $percent.css('width', percentage * 100 + '%');
//    });
//
//    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
//    uploader.on('uploadSuccess', function (file) {
//        $('#' + file.id).addClass('upload-state-done');
//    });
//
//    // 文件上传失败，现实上传出错。
//    uploader.on('uploadError', function (file) {
//        var $li = $('#' + file.id),
//            $error = $li.find('div.error');
//
//        // 避免重复创建
//        if (!$error.length) {
//            $error = $('<div class="error"></div>').appendTo($li);
//        }
//
//        $error.text('上传失败');
//    });
//
//    // 完成上传完了，成功或者失败，先删除进度条。
//    uploader.on('uploadComplete', function (file) {
//        $('#' + file.id).find('.progress').remove();
//    });
//});


