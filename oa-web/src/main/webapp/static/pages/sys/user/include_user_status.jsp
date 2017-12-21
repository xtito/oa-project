<%@ page language="java" pageEncoding="UTF-8" %>
<script type="text/html" id="statusTpl">
    {{#  if(d.status === 0){ }}
    <span class="status-disable">禁用</span>
    {{#  } }}

    {{#  if(d.status === 1){ }}
    <span class="status-normal">正常</span>
    {{#  } }}

    {{#  if(d.status === 2){ }}
    <span class="status-lock">锁定</span>
    {{#  } }}

    {{#  if(d.status !== 0 && d.status !== 1 && d.status !== 2){ }}
    <span>其他</span>
    {{#  } }}
</script>
