<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
		String username=(String)request.getAttribute("username");
		application.setAttribute("username", username);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>个人中心</title>
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
	<h2>${msg}${username}</h2>
	<a href="ClearCookieServlet">
		<button>退出</button>
	</a>
	<br>
	<br>
	<%
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			int i = 0;
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					out.println("尊敬的用户" + cookie.getValue() + ":您好，请继续您的操作！");
					out.println("<h3> 个人中心 </h3>");
					out.print("-------------------操作 -----------------<br>");
					out.print("<a href='editpwd.jsp'><input type='button' value='修改密码'></a><br><br>");
					out.print("<a href='studentinfo.jsp'><input type='button' value='学生信息'></a>");
					break;
				} else if (i == cookies.length-1&&!cookie.getName().equals("username") ) {
					out.println("<a href='login.jsp'><h2>请登录</h2><a>");
				}
				i++;
			}
		}
	%>

</body>
</html>
