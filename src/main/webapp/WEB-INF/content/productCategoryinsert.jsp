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
<title>产品分类添加</title>
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
		<span>父分类：</span><input type="text" name="parentId"/><br>
		<span>分类名称：</span><input type="text" name="name"/><br>
		<span>分类描述：</span><input type="text" name="description"/><br>
		<a href="javascript:void(0);" id="editSub" class="easyui-linkbutton" >确定添加</a>
				
	</form>
</center>
</body>
<script type="text/javascript">
	$(function(){
		$("#editSub").click(function(){
			$.post(
				"insertProductCategory",
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