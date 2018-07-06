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
<title>11111111111111</title>

</head>
<body>
<table id="log"></table>

</body>
<script type="text/javascript">
$(function(){
	var id = ${id};
	$('#log').datagrid({
	    url:"Lead/leadLog.do?id="+id,
	    title:"沟通日志",
	    striped:true,	//斑马线
	    pagination:true,	//分页工具栏
	    method:"post",
	    emptyMsg:"未找到记录",
	    loadMsg:"急个卵,正加载呢!!!!",
	    collapsible: true,	//可折叠
	    rownumbers:true,	//显示行号
	    columns:[[
			{field:'stepTime',title:'上次联系时间'},
	        {field:'step',title:'联系内容'}
	    ]]
	});
});

</script>
</html>