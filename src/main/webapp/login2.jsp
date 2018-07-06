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
<link href="static/css/login2.css" rel="stylesheet" type="text/css">
</head>
<body>  
<div id="login_frame">  
  
    <p id="image_logo"><img src="static/images/deng.jpg" width="100px"></p>
  
    <form method="post" action="login.do">  
  
        <p><label class="label_input">用户名</label><input type="text" id="username" name="username" class="text_field" required="required" /></p>  
        <p><label class="label_input">密码</label><input type="password" id="password" name="password" class="text_field" required="required"/></p>  
  
        <div id="login_control">  
            <input type="submit" id="btn_login" value="登录" />  
            <a id="forget_pwd" href="">忘记密码？</a>  
        </div>  
    </form>  
</div>  
  
</body>  
</html>