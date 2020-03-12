<%--
  Created by IntelliJ IDEA.
  User: zhongyi
  Date: 2020-02-26
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>首页</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta name="keywords" content="关键字">
    <meta name="description" content="内容描述">

    <!-- css文件引入 -->
    <%-- <link href="<%=path%>/css/xxx.css" rel="stylesheet" type="text/css"> --%>
    <!-- js文件引入 -->
    <%-- <script src="<%=path%>/js/xxx.js" type="text/javascript"></script> --%>

    <style>
    </style>
</head>

<body>
    ${name}

    <!-- 演示静态分离 -->
    <%--<h1><img alt="" src="http://static.ymdx.com/static/imgs/ymdx.jpg" /></h1>--%>
</body>

</html>

