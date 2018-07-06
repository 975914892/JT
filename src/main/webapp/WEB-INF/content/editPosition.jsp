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
<body>
<form action="#" method="post" id="editFrom">
		岗位ID:<input type="text" name="id" id="" readonly="readonly" value="${tcp.id }"/><br/>
		岗位名称:<input type="text" name="name" id="" value="${tcp.name }"/><br/>
		
		上级岗位:
		<select name="parentId">
			
			<c:forEach items="${listA }" var="A">
				<c:if test="${A.id eq tcp.parentId  }">
				<option value="${A.id }" selected="selected">${A.name }</option>
				</c:if>
				<option value="${A.id }">${A.name }</option>
			</c:forEach>
		</select>
		<br/>
		所属部门:
		<select name="departmentId">
			
			<c:forEach items="${listB }" var="B">
				<c:if test="${B.id eq tcp.departmentId  }">
				<option value="${B.id }" selected="selected">${B.name }</option>
				</c:if>
				<option value="${B.id }">${B.name }</option>
			</c:forEach>
		</select>
		
		<br/>
		简单描述:<input type="text" name="description" id="" value="${tcp.description}"/><br/>
		<input type="button" value="编辑岗位" id ="editSub" >
	</form>
</body>
<script type="text/javascript">
$(function(){
	
	$("#editSub").click(function(){
	
		$.post(
			"editPositionAfter.do",
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