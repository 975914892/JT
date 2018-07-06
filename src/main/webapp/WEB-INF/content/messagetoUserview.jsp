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
<title>Insert title here</title>
<link href="static/easyui/themes/insdep/easyui.css" rel="stylesheet" type="text/css">
<link href="static/easyui/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
<style type="text/css">
	#editFrom{
		font-size: 16px;
		font-family: "宋体";
	}
	table{
		height: 300px;
		margin-left:auto;
		margin-right: auto;
	}
	table,td{
		
		border: 1px solid #f69234;
		border-collapse: collapse;
		
	}
	td {
		width: 200px;
		text-align: center;
	}
	.div{
		text-align: center;
		
	}
</style>
</head>
<body>

	<h1>此信来自：${fromUserName }</h1>
	<form action="#" method="post" id="editFrom">
		<div class="div">
		<table >


			<tr>
				<td>id</td>
				<td><input type="text" name="id" value="${message.id }"
					readonly /></td>
			</tr>
			<tr>
			<td>收信人id</td>
			<td><input type="text" name="toUserId" value="${message.toUserId }" readonly/></td>
			</tr>
			<tr>	
			<td>写信人id</td>
			<td><input type="text" name="fromUserId" value="${message.fromUserId }" readonly/></td>
			</tr >
			<tr>	
			<td>状态码</td>
			<td><input type="text" name="status" value="${message.status }" readonly/></td>
			</tr >	
			<tr>	
			<td>阅读时间</td>
			<td><input type="text" name="readTime" value="${message.readTime }" readonly/></td>
			</tr >
			<tr>	
			<td>发送时间</td>
			<td><input type="text" name="sendTime" value="${message.sendTime }" readonly/></td>
			</tr >
		</table>
		<textarea rows="12" cols="80" name="content" >${message.content }</textarea>
		
			<input type="button"  value="已阅" id ="editSub"/>
				
         <div>    
		</form>
</body>
<script type="text/javascript">
	$(function(){
		$("#editSub").click(function(){
			$.post(
				"messagelookotherok",
				$("#editFrom").serialize(),
				function(data){
					alert(data.message1);
					parent.$("#topWindow").window("close");
				},
				"json"
			);
	});
	})
</script>

</html>