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
<title>产品添加</title>
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
		<span>分类：</span>
		<select name="categoryId">
			<option value="0" selected="selected">----请选择----</option>
			<c:forEach items="${proCateList }" var="A">
				<option value="${A.parentId }">${A.name }</option>
			</c:forEach>
		</select><br>
		<span>产品名称：</span><input type="text" name="name"/><br>
		<span>成本价：</span><input type="text" name="costPrice"/><br>
		<span>建议售价：</span><input type="text" name="suggestedPrice"/><br>
		<span>开发团队：</span><input type="text" name="developmentTeam"/><br>
		<span>开发时间：</span><input type="text" name="developmentTime"/><br>
		<span>链接：</span><input type="text" name="link"/><br>
		<span>产品描述：</span><input type="text" name="description"/><br>
		<a href="javascript:void(0);" id="editSub" class="easyui-linkbutton" >确定添加</a>
				
	</form>
</center>
</body>
<script type="text/javascript">
	$(function(){
		$("#editSub").click(function(){
			$.post(
				"insertProduct",
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