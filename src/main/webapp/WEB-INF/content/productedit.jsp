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
				分类：
		<select name="categoryId">
			<c:forEach items="${proCateList }" var="A">
				<c:if test="${A.parentId eq product.categoryId }">
					<option value="${A.parentId }" selected="selected">${A.name }</option>
				</c:if>
				<option value="${A.parentId }">${A.name }</option>
			</c:forEach>
		</select><br>
		<table >
			<tr>
			<td>用户ID</td>
			<td><input type="text" name="id" value="${product.id }" readonly /></td>
			</tr>
			<tr>	
			<td>产品名称</td>
			<td><input type="text" name="name"  value="${product.name }" /></td>
			</tr >
			<tr>
			<td>创建者</td>
			<td><input type="text" name="creatorUserId" value="${product.creatorUserId }" readonly/></td>
			</tr >
			<tr>
			<td>成本价</td>
			<td><input type="text" name="costPrice" value="${product.costPrice }" /></td>
			</tr >
			<tr>
			<td>建议售价</td>
			<td><input type="text" name="suggestedPrice" value="${product.suggestedPrice }" /></td>
			</tr >
			<tr>
			<td>开发团队</td>
			<td><input type="text" name="developmentTeam" value="${product.developmentTeam }" readonly/></td>
			</tr >
			<tr>
			<td>开发时间</td>
			<td><input type="text" name="developmentTime" value="${product.developmentTime }" readonly/></td>
			</tr >
			<tr>
			<td>链接</td>
			<td><input type="text" name="link" value="${product.link }" /></td>
			</tr >
			<tr>		
			<td>创建时间</td>
			<td><input type="text" name="createTime" value="${product.createTime }" readonly/></td>
			</tr >
			<tr>		
			<td>更新时间</td>
			<td><input type="text" name="updateTime" value="${product.updateTime }" readonly/></td>
			</tr >
			<tr>		
			<td>产品描述</td>
			<td><input type="text" name="description" value="${product.description }" /></td>
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
				"productUpdate",
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