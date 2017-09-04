<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@include file="/inc.jsp" %>
<!DOCTYPE html>
<html lang="en">

<%--
  
  Created by User: Zy
  Created Date: 2017/7/27 15:17
--%>

<head>
    <meta charset="UTF-8">
    <%@ include file="/static/common/public-inc.jsp"%>
    <%@ include file="/static/common/js-inc.jsp"%>
    <title>文档管理</title>
</head>

<body>
    <%@ include file="../fragment/header.jsp"%>

    <div id="container" class="container">
        <div class="main-con">
            <div class="con-layout">
                <div class="sidebar col-l-2">
                    <div class="nav-m">
                        <div class="nav-tit">
                            <h3>文档查询</h3>
                            <div class="widget-watermark le-nav-d"><i class="le-icon f20 icon-th-list"></i></div>
                        </div>

                        <div class="nav-list">
                            <ul>
                                <li><a href="#">车辆申请</a></li>
                                <li><a href="#">车辆审批</a></li>
                                <li><a href="#">车辆使用查询</a></li>
                                <li><a href="#">驾驶员维护</a></li>
                                <li><a href="#">车辆维护</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="nav-m mt20">
                        <div class="nav-tit">
                            <h3>文档分类</h3>
                            <div class="widget-watermark le-nav-d"><i class="le-icon f20 icon-th-list"></i></div>
                        </div>

                        <div class="nav-list">
                            <ul>
                                <li>
                                    <a href="#">视频资源</a>
                                    <ul class="two-ul">
                                        <li><a href="#">时事新闻</a></li>
                                        <li><a href="#">教育学习</a></li>
                                        <li><a href="#">政策法规</a></li>
                                    </ul>
                                </li>

                                <li>
                                    <a href="#">部门文件</a>
                                    <ul class="two-ul">
                                        <li><a href="#">技术部</a></li>
                                        <li><a href="#">市场部</a></li>
                                        <li><a href="#">销售部</a></li>
                                        <li><a href="#">行政部</a></li>
                                        <li><a href="#">测试部</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="warp col-l-8">
                    <div class="warp-div">
                        <div class="con-layout">
                            <div class="warp-con border-none">
                                <div class="model p-none">
                                    <div class="chart-main">
                                        <div class="chart-tit">
                                            <h4>文档统计</h4>
                                        </div>

                                        <div class="chart-con">
                                            <img src="${ctx}/static/images/charts/zg.png" alt="文档统计" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="con-layout mt20">
                            <div class="warp-tit">
                                <h3 class="tit">热门文档</h3>
                                <div class="widget-watermark le-b"><i class="le-icon le-tit-icon icon-book"></i></div>
                            </div>

                            <div class="warp-con">
                                <div class="con-layout">
                                    <div class="model">
                                        <table class="p-table table-omit">
                                            <thead>
                                            <tr>
                                                <th class="w110">时间</th>
                                                <th class="w100">文档类型</th>
                                                <th>文档名称</th>
                                            </tr>
                                            </thead>
                                            <tbody>

                                            <tr>
                                                <td class="tc">2017-3-19</td>


                                                <td class="tc fb docC">公文</td>

                                                <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                            </tr>

                                            <tr>
                                                <td class="tc">2017-3-19</td>

                                                <td class="tc fb carC">车辆管理</td>


                                                <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                            </tr>

                                            <tr>
                                                <td class="tc">2017-3-19</td>


                                                <td class="tc fb docC">公文</td>

                                                <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                            </tr>

                                            <tr>
                                                <td class="tc">2017-3-19</td>

                                                <td class="tc fb carC">车辆管理</td>


                                                <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                            </tr>

                                            <tr>
                                                <td class="tc">2017-3-19</td>


                                                <td class="tc fb docC">公文</td>

                                                <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                            </tr>

                                            <tr>
                                                <td class="tc">2017-3-19</td>

                                                <td class="tc fb carC">车辆管理</td>


                                                <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                            </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="con-layout mt20">
                            <div class="con-layout pr10 col-l-5">
                                <div class="warp-tit">
                                    <h3 class="tit">我上传的文档</h3>
                                    <div class="widget-watermark le-b"><i class="le-icon le-tit-icon icon-upload-alt"></i></div>
                                </div>

                                <div class="warp-con">
                                    <div class="con-layout">
                                        <div class="model">
                                            <table class="p-table table-omit">
                                                <thead>
                                                <tr>
                                                    <th class="w110">时间</th>
                                                    <th class="w100">文档类型</th>
                                                    <th>文档名称</th>
                                                </tr>
                                                </thead>
                                                <tbody>

                                                <tr>
                                                    <td class="tc">2017-3-19</td>


                                                    <td class="tc fb docC">公文</td>

                                                    <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                                </tr>

                                                <tr>
                                                    <td class="tc">2017-3-19</td>

                                                    <td class="tc fb carC">车辆管理</td>


                                                    <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                                </tr>

                                                <tr>
                                                    <td class="tc">2017-3-19</td>


                                                    <td class="tc fb docC">公文</td>

                                                    <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                                </tr>

                                                <tr>
                                                    <td class="tc">2017-3-19</td>

                                                    <td class="tc fb carC">车辆管理</td>


                                                    <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                                </tr>

                                                <tr>
                                                    <td class="tc">2017-3-19</td>


                                                    <td class="tc fb docC">公文</td>

                                                    <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                                </tr>

                                                <tr>
                                                    <td class="tc">2017-3-19</td>

                                                    <td class="tc fb carC">车辆管理</td>


                                                    <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                                </tr>

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                </div>
                            </div>

                            <div class="con-layout pl10 col-l-5">
                                <div class="warp-tit">
                                    <h3 class="tit">最新文档</h3>
                                    <div class="widget-watermark le-b"><i class="le-icon le-tit-icon icon-file"></i></div>
                                </div>

                                <div class="warp-con">
                                    <div class="con-layout">
                                        <div class="model">
                                            <table class="p-table table-omit">
                                                <thead>
                                                <tr>
                                                    <th class="w110">时间</th>
                                                    <th class="w100">文档类型</th>
                                                    <th>文档名称</th>
                                                </tr>
                                                </thead>
                                                <tbody>

                                                <tr>
                                                    <td class="tc">2017-3-19</td>


                                                    <td class="tc fb docC">公文</td>

                                                    <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                                </tr>

                                                <tr>
                                                    <td class="tc">2017-3-19</td>

                                                    <td class="tc fb carC">车辆管理</td>


                                                    <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                                </tr>

                                                <tr>
                                                    <td class="tc">2017-3-19</td>


                                                    <td class="tc fb docC">公文</td>

                                                    <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                                </tr>

                                                <tr>
                                                    <td class="tc">2017-3-19</td>

                                                    <td class="tc fb carC">车辆管理</td>


                                                    <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                                </tr>

                                                <tr>
                                                    <td class="tc">2017-3-19</td>


                                                    <td class="tc fb docC">公文</td>

                                                    <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                                </tr>

                                                <tr>
                                                    <td class="tc">2017-3-19</td>

                                                    <td class="tc fb carC">车辆管理</td>


                                                    <td class="tl"><a href="#">足球场改造扩建工程申请批示</a></td>
                                                </tr>

                                                </tbody>
                                            </table>
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