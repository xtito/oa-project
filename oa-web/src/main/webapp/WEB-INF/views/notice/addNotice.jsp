<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@include file="/inc.jsp" %>
<!DOCTYPE html>
<html lang="en">

<%--
  
  Created by User: Zy
  Created Date: 2017/7/28 11:17
--%>

<head>
    <meta charset="UTF-8">
    <%@ include file="/static/common/public-inc.jsp"%>
    <%@ include file="/static/common/js-inc.jsp"%>
    <link rel="stylesheet" href="${ctx}/static/css/upload-plugin.css" />
    <script src="${ctx}/static/js/webuploader.min.js"></script>
    <script src="${ctx}/static/js/getting-started.js"></script>
    <title>通知公告</title>
</head>

<body>
    <%@ include file="../fragment/header.jsp"%>

    <div id="container" class="container">
        <div class="main-con">
            <div class="con-layout">
                <div class="sidebar col-l-2">
                    <div class="nav-m">
                        <div class="nav-tit">
                            <h3>通知公告</h3>
                            <div class="widget-watermark le-nav-d"><i class="le-icon f20 icon-th-list"></i></div>
                        </div>

                        <div class="nav-list">
                            <ul>
                                <li><a>全部公告</a></li>
                                <li><a>已读公告</a></li>
                                <li><a>未读公告</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="nav-m mt20">
                        <div class="nav-tit">
                            <h3>公告类型</h3>
                            <div class="widget-watermark le-nav-d"><i class="le-icon f20 icon-th-list"></i></div>
                        </div>

                        <div class="nav-list">
                            <ul>
                                <li><a>类型一</a></li>
                                <li><a>类型二</a></li>
                                <li><a>类型三</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="warp col-l-8">
                    <div class="warp-div">
                        <div class="warp-main">
                            <div class="warp-tit">
                                <h3 class="tit">添加公告</h3>
                                <div class="widget-watermark le-b"><i class="le-icon le-tit-icon icon-plus"></i></div>
                            </div>

                            <div class="warp-con">
                                <div class="model">
                                    <div class="con-layout">
                                        <div class="in-group has-error">
                                            <label for="notice_tit" class="in-lab col-l-15">标题</label>
                                            <div class="in-con col-l-7">
                                                <input type="text" id="notice_tit" class="in-control" />
                                                <i class="has-icon"></i>
                                                <small class="help-block">请填写公告标题</small>
                                            </div>
                                        </div>

                                        <div class="in-group has-success">
                                            <label for="notice_other" class="in-lab col-l-15">类别</label>
                                            <div class="in-con col-l-7">
                                                <select id="notice_other" class="in-control in-select">
                                                    <option>请选择</option>
                                                    <option selected>基础知识</option>
                                                    <option>其他</option>
                                                </select>
                                                <i class="has-icon"></i>
                                                <small class="help-block">请选择公告类别</small>
                                            </div>
                                        </div>

                                        <div class="in-group">
                                            <label for="notice_desc" class="in-lab col-l-15">描述</label>
                                            <div class="in-con col-l-7">
                                                <textarea id="notice_desc" class="in-control textarea"></textarea>
                                                <i class="has-icon"></i>
                                                <small class="help-block">请填写公告描述</small>
                                            </div>
                                        </div>

                                        <%--<div class="in-group sp-l">
                                            <div class="ck-rd-custom checkbox-custom primary">
                                                <input id="inputUnchecked" type="checkbox">
                                                <label for="inputUnchecked">是否审批</label>
                                            </div>
                                        </div>

                                        <div class="in-group">
                                            <label for="au_per" class="in-lab col-l-15">审批人</label>
                                            <div class="in-con col-l-5">
                                                <input type="text" id="au_per" class="in-control" />
                                                <i class="has-icon"></i>
                                                <small class="help-block">请选择审批人</small>
                                            </div>
                                            <button type="button" class="p-but w60 sp-but">选择</button>
                                        </div>--%>

                                        <div class="con-layout mb12">
                                            <div class="in-group fl">
                                                <label for="au_per" class="in-lab col-l-15 fl">审批人</label>
                                                <div class="in-con col-l-3 fl">
                                                    <input type="text" id="au_per" class="in-control" />
                                                    <i class="has-icon"></i>
                                                    <small class="help-block">请选择审批人</small>
                                                </div>
                                                <button type="button" class="p-but fl w60 sp-but">选择</button>

                                                <div class="ck-rd-custom sp-p fl">
                                                    <div class="cr-custom checkbox-custom primary">
                                                        <input id="inputUnchecked" type="checkbox">
                                                        <label for="inputUnchecked">是否审批</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="in-group has-success">
                                            <label for="send_fw" class="in-lab col-l-15">发送范围</label>
                                            <div class="in-con col-l-7">
                                                <select id="send_fw" class="in-control in-select">
                                                    <option>请选择</option>
                                                    <option selected>全部</option>
                                                    <option>本部门</option>
                                                    <option>其他</option>
                                                </select>
                                                <i class="has-icon"></i>
                                                <small class="help-block">请选择发送范围</small>
                                            </div>
                                        </div>

                                        <div class="uploader-plugins">
                                            <div class="plugin">
                                                <!--用来存放文件信息-->
                                                <div id="the_list" class="uploader-list">
                                                    <p class="default-text">请选择要上传的文件</p>
                                                </div>
                                                <div class="buts">
                                                    <div id="picker" class="picker">选择文件</div>
                                                    <button id="ctr_but" class="but but-default ml20">开始上传</button>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="con-layout">
                                            <button type="button" class="p-but w100">保存</button>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="../fragment/footer.jsp"%>
</body>

</html>