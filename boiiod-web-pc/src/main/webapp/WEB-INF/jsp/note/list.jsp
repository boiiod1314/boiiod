<%--
  Created by IntelliJ IDEA.
  User: boiiod
  Date: 2017/2/21
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <title>笔记列表</title>
</head>
<body>
<jsp:include page="../common/top.jsp"></jsp:include>
<script type="text/javascript" src="/statics/js/note/list.js"></script>
<%--<script type="text/javascript" src="/statics/js/index/index.js"></script>--%>

<%--<div class="c_body">
    <div class="c_note_list" id="j_note_list">

    </div>
</div>--%>
<%--<jsp:include page="../common/top.jsp"></jsp:include>
<script type="text/javascript" src="/statics/js/note/list.js"></script>
--%>
<div class="c_body">
    <c:if test="${userContext != null && userContext.user.id == userId}">
        <a href="javascript:;" id="J_btn_addRootFolderOrNote" style="margin: 5px 20px;">添加根目录/根笔记</a>
    </c:if>
    <div id="J_div_list">
    </div>
    <span id="j_user_id" style="display: none;">${userId}</span>
</div>
</body>
</html>