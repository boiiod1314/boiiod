<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <%--<link rel="shortcut icon" href="statics/images/favicon.ico" type="image/x-icon" />--%>
    <link rel="stylesheet" href="/statics/css/index/index.css">
    <title>主页</title>
</head>
<body>
<jsp:include page="../common/top.jsp"></jsp:include>
<script type="text/javascript" src="/statics/js/index/index.js"></script>

<div class="c_body">
    <div class="c_note_list" id="j_note_list">

    </div>
</div>
</body>
</html>
