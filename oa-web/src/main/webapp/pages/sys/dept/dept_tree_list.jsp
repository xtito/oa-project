<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc.jsp" %>

<%--
  
  Created by User: Zy
  Created Date: 2017/11/26 20:35
--%>
<script type="text/javascript">
    <!--
    require(["domReady"], function (doc) {
        require(["jquery", "zTree"], function ($, zTree) {

            $(document).ready(function(){
                var settings = {
                    view: {showIcon: false, showLine: false},
                    async: {
                        enable: true,
                        url: ctx + "/mvc/sysDepartment/mgr/list/tree",
                        autoParam: ["id", "level"],
                        dataFilter: function (treeId, parentNode, childNodes) {
                            if (!childNodes) return null;
                            for (var i = 0, l = childNodes.length; i < l; i++) {
                                childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
                            }
                            return childNodes;
                        },
                        type: "get"
                    },
                    callback: {
                        onClick: function(e, treeId, treeNode) {
//                            var zTree = $.fn.zTree.getZTreeObj("treeEle");
//                            zTree.expandNode(treeNode);
                        }
                    }
                };
                $.fn.zTree.init($("#treeEle"), settings);
            });

        });
    });
    //-->
</script>
<div id="treeEle" class="zTree ito-tree"></div>