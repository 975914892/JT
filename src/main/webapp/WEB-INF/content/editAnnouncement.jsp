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
<title>修改公告信息</title>
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
	<form action="#" method="post" id="editFrom">
		文章编号 :<input type="text" name="id" readonly="readonly" value="${avo.id}"/><br/>
		排序 :<input type="text" name="orderId" value="${avo.orderId}"/><br/>
		发表人 :<input type="text" name="username" readonly="readonly" value="${avo.username}"/><br/>
		标题  :<input type="text" name="title" value="${avo.title}"/><br/>
		内容 :<br/>
		<textarea  name="content" id="content" >${avo.content}
		</textarea>
		<br/>
		颜色 :<input type="text" name="color" value="${avo.color}"/><br/>
		通知部门 :<input type="text" name="department" value="${avo.department}"/><br/>
		是否公开 :<input type="text" name="status" value="${avo.status}"/><br/>
		是否发布:<input type="text" name="isshow" value="${avo.isshow}"/><br/>
		<input type="hidden" name="createTime" value="${avo.createTime}"/><br/> 
		
		<input type="button" value="修改公告" id ="editSub" >
	</form>
</body>
<script type="text/javascript">
$(function(){
	alert("edit")
	$("#editSub").click(function(){
	
		$.post(
			"editAnnouncementAfter.do",
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