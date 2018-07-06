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
<title>公告信息</title>
<link href="static/easyui/themes/insdep/easyui.css" rel="stylesheet" type="text/css">







<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>

<style type="text/css">
	#content{
		height: 200px;
		width: 400px;
		border:1px bule solid;
	}
	input{
	border:1px solid gray;
	}
</style>
</head>
<body>
	<form action="#" method="post" id="addFrom">
		
		排序 :<input type="text" name="orderId" id=""/><br/>
		发表人 :<input type="text" name="username" readonly="readonly" value="${sessionScope.user.username }"/><br/>
		标题  :<input type="text" name="title" id=""placeholder="请输入公告标题!"/><br/>
		内容 :<br/>
		<textarea  name="content" id="content" >
		</textarea>
		<br/>
		颜色 :<input type="text" name="color" id=""/><br/>
		通知部门 :<input type="text" name="department" id=""/><br/>
		是否公开 :<input type="text" name="status" id=""/><br/>
		是否发布:<input type="text" name="isshow" id=""/><br/>
		<input type="button" value="添加公告" id ="addSub" >
	</form>
</body>
<script type="text/javascript">
$(function(){
	alert("add")
	$("#addSub").click(function(){
	
		$.post(
			"addAnnouncementAfter.do",
			$("#addFrom").serialize(),
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