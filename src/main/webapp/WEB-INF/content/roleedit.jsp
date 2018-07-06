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


	<form action="#" method="post" id="editFrom">
		<div class="div">
		<table >


			<tr>
				<td>角色ID</td>
				<td><input type="text" name="roleId" value="${role.roleId }"
					readonly /></td>
			</tr>
			<tr>
			<td>角色名</td>
			<td><input type="text" name="roleName" value="${role.roleName }" /></td>
			</tr>
			<tr>	
			<td>备注</td>
			<td><input type="text" name="note" value="${role.note }" /></td>
			</tr >
			<tr>	
			<td>状态码</td>
			<td><input type="text" name="status" value="${role.status }" readonly/></td>
			</tr >	
			<tr>	
			<td>创建时间</td>
			<td><input type="text" name="createTime" value="${role.createTime }" readonly/></td>
			</tr >
			
			
		</table>
		
			<input type="button"  value="修改" id ="editSub"/>
				
         <div>    
		</form>
</body>
<script type="text/javascript">
	$(function(){
		$("#editSub").click(function(){
			$.post(
				"roleUpdateok",
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