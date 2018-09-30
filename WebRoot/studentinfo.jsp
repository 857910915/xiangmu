<%@ page language="java"
	import="java.util.*,bean.Student,service.StudentService"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	StudentService service1 = new StudentService();
	service1.connectionSql();
	List<Student> stuList = service1.getStudents();
	request.setAttribute("stuList", stuList);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>学生信息</title>
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
	<h1>学生信息</h1>
	<p>
		操作用户
		<%
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					out.print(cookie.getValue());
				}
			}

		}
	%>
	</p>
	<a href="addstudent.jsp">
		<button>添加</button>
	</a>
	<a href="index.jsp">
		<button>返回</button>
	</a>
	<table border="1" cellspacing="0" width="600px">
		<tr>
			<th>ID</th>
			<th>姓名</th>
			<th>性别</th>
			<th>年龄</th>
			<th>专业</th>
			<th>电话</th>
			<th colspan="2">操作</th>
		</tr>
		<c:forEach items="${stuList}" var="stu">

			<tr>
				<td>${stu.sid}</td>
				<td>${stu.sname}</td>
				<td>${stu.ssex}</td>
				<td>${stu.sage}</td>
				<td>${stu.smajor}</td>
				<td>${stu.sphone}</td>
				<td><a class="btn" href="editstuinfo.jsp?sid=${stu.sid}"
					method="post">修改</a></td>

				<td><a class="btn" href="DeleteStuInfoServlet?sid=${stu.sid}"
					method="post" onclick="opendel('');">删除</a></td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>
