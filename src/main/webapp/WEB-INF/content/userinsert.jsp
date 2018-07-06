<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户添加</title>
<%@include file="script.html" %>
<style type="text/css">
	input{
	 border:1px solid black;
	 font-size: 30px;
	}
	span{
	font-size: 30px;
	}
</style>
</head>
<body>
<center>

	<form action="#" method="post" id="editFrom">
		<br><br>
		<span>用户名字：</span><input type="text" name="username"/><br><br><br><br>
		<span>用户密码：</span><input type="text" name="password"/><br><br><br><br>
		<a href="javascript:void(0);" id="editSub" class="easyui-linkbutton" >确定添加</a>
				
	</form>
</center>
</body>
<script type="text/javascript">
	$(function(){
		$("#editSub").click(function(){
			$.post(
				"insertUser",
				$("#editFrom").serialize(),
				function(data){
					alert(data.message);
					parent.$("#topWindow").window("close");
				},
				"json"
			);
	});
	})
</script>

</html>