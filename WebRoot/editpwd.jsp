<%@page import="bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String username = (String) application.getAttribute("username");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>修改密码</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form action="EditPwdServlet" method="post">
		用户名：<input type="text" name="username" readonly="true"
			value="${username}"><br> <br> 新密码：<input
			type="password" name="newpwd"><br> <br> 老密码：<input
			type="password" name="oldpwd"><br> <br> <input
			type="submit" value="确认修改"> <a href="index.jsp"> <input
			type="button" value="返回">
		</a>
	</form>
</body>
</html>
