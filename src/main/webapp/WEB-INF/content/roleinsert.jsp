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
<title>角色添加</title>
<%@include file="script.html" %>
<style type="text/css">
	input{
	 border:1px solid black;
	 font-size: 20px;
	}
	span{
	font-size: 20px;
	}
</style>
</head>
<body>
<center>

	<form action="#" method="post" id="editFrom">
		<br><br>
		<span>角色名字：</span><input type="text" name="roleName"/><br><br><br><br>
		<span>角色备注：</span><br><textarea rows="10" cols="50" name="note"></textarea><br><br><br>
		<a href="javascript:void(0);" id="editSub" class="easyui-linkbutton" >确定添加</a>
				
	</form>
</center>
</body>
<script type="text/javascript">
	$(function(){
		$("#editSub").click(function(){
			$.post(
				"insertRole",
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