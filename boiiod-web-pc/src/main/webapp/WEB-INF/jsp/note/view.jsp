<%--
  Created by IntelliJ IDEA.
  User: boiiod
  Date: 2017/2/21
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>笔记详情-${noteVo.note.title}</title>
    <jsp:include page="../common/common.jsp"></jsp:include>
    <jsp:include page="../common/markdown.jsp"></jsp:include>
    <script type="text/javascript" src="/statics/js/note/view.js"></script>
</head>
<body>

<c:if test="${empty noteVo.noteContents}">
    <div style="margin: 50px auto;">
        笔记内容为空!
        <c:if test="${userContext.user.id == noteVo.note.userId}">
            <a href="/note/view/${noteVo.note.id}.html">>> 去添加内容</a>
        </c:if>
    </div>
</c:if>
<c:forEach var="noteContent" items="${noteVo.noteContents}">
    <div id="layout">
        <div id="boiiod-editormd-view-note">
            <textarea style="display: none;">${noteContent.content}</textarea>
        </div>
    </div>
</c:forEach>
</body>
</html>
