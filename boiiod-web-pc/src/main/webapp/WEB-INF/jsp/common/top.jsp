<%--
  Created by IntelliJ IDEA.
  User: boiiod
  Date: 16/10/26
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta name="keywords" content="邹凡奇 个人网站">
    <title>邹凡奇个人网站</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="../common/common.jsp"></jsp:include>
    <%--<script type="text/javascript" src="statics/js/common/jquery1.11.1.min.js"></script>--%>
    <script type="text/javascript" src="/statics/bootstrap/js/bootstrap.min.js"></script>
    <link href="/statics/css/common/common.css" rel="stylesheet" type="text/css">

    <link href="/statics/css/common/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/statics/css/common/top.css" rel="stylesheet" type="text/css">
    <link href="/statics/css/common/body.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="/statics/js/common/top.js"></script>
</head>

<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <%--<a class="navbar-brand">www.zoufanqi.com</a>--%>
        </div>

        <div class="collapse navbar-collapse" id="navbar-ex-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="">
                    <a href="/"><i class="fa fa-fw"></i>主页</a>
                </li>

                <%--<li class="active">
                    <a href="#">Contacts</a>
                </li>--%>
                <c:choose>
                    <c:when test="${userContext == null}">
                        <li id="j_login_register"><a href="javascript:;">登录•注册</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-expanded="false">
                                Hi. ${userContext.user.nickname}
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="/note/list.html">我的笔记</a></li>
                                <li><a href="/logout.login">退出</a></li>
                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</div>

