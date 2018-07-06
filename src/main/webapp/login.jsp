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
<title>登录</title>
<link href="static/css/login.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="wrap">
        <div class="container">
        	<h1>WelCome</h1>
	        <form method="post" action="login.do">
	            <input type="text" required="required" placeholder="用户名" name="username"></input>
	            <input type="password" required="required" placeholder="密码" name="password"></input>
	            <input type="submit" value="登录" >
	            <h1 style="color: pink">${message }</h1>
	        </form>
        </div>
</div>
</body>
</html>