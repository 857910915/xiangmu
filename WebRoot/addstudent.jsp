<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加学生信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
.info {
	height: 320px;
	width: 300px;
	border: 1px black solid;
	text-align: center;
	margin: auto;
}
</style>
</head>

<body>
	<div class="info">
		<h3>添加信息</h3>
		<form action="AddStudentServlet" method="POST">
			学号：<input type="text" name="stuid" readonly="true"><br>
			<br> 姓名：<input type="text" name="stuname"><br> <br>
			性别：<input type="radio" name="stusex" value="boy">男 <input
				type="radio" name="stusex" value="girl">女<br> <br>
			年龄：<input type="text" name="stuage"><br> <br> 专业：<select
				name="stumajor" id="stumajor">
				<option>---请选择---</option>
				<option value="JAVA">JAVA</option>
				<option value="PHP">PHP</option>
				<option value="Python">Python</option>
				<option value="H5">H5</option>
			</select><br> <br> 电话：<input type="text" name="stuphone"><br>
			<br> <input type="submit" value="保存"> <a
				href="studentinfo.jsp"> <input type="button" value="返回">
			</a>

		</form>
	</div>

</body>
</html>
