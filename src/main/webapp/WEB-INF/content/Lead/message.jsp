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

<link rel="stylesheet" href="static/css/style.css">
<link rel="stylesheet" type="text/css" href="static/css/component.css" />

<script type="text/javascript" src="static/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>

</head>
<body>
<table>
	<tr>
		<th>姓名:</th>
		<td><input type="text" name="name" id="name"></td>
	</tr>
	<tr>
		<th>电话号码:</th>
		<td><input type="text" name="phone" id="phone"><br></td>
	</tr>
</table>


<input type="button" value="发送短信" onclick="send()">
</body>
<script type="text/javascript">
function send(){
	var name=$("#name").val();
	var	phone=$("#phone").val();
	alert(phone);
	$.ajax({
		url:"Lead/send.do",
		data:{
			"name":name,"phone":phone
		},
		dataType:"json",
		type:"post",
		traditional:true,//防止深度序列化
		success:function(){
			alert(data.msg);
		}
		});
}
</script>
</html>