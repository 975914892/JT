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
<title>商机详情</title>
<link href="static/easyui/themes/insdep/easyui.css" rel="stylesheet" type="text/css">
<link href="static/easyui/themes/insdep/easyui_animation.css" rel="stylesheet" type="text/css">
<link href="static/easyui/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
<link href="static/easyui/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="static/css/style.css">
<link rel="stylesheet" type="text/css" href="static/css/component.css" />

<script type="text/javascript" src="static/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="static/js/easyform.js"></script>
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/themes/insdep/jquery.insdep-extend.min.js"></script>
<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
</head>
<body>
<form action="" id="Bform">
<table>
	<tr>
		<th>前进到的阶段:</th>
		<td>
			<select name="statusId" id="statusId" class="easyui-combobox" style="width:160px;" >
				<option value="1">意向客户</option>
				<option value="2">初步沟通</option>
				<option value="3">深度沟通</option>
				<option value="4">签订合同</option>
				<option value="5">设计制作</option>
				<option value="6">制作完成</option>
				<option value="7">项目成功</option>
				<option value="8">项目失败</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>下次联系:</th>
		<td>
			<input type="text" class= "easyui-datebox" name="nextstepTime" placeholder="时间">
			<input type="text" name="nextstep" placeholder="内容">
		</td>
	</tr>
	<tr>
		<th>阶段描述:</th>
		<td><input type="text" name="descrption" id="descrption" style="width:500px;height:300px"></td>
	</tr>
</table>
<input type="button" value="推进" style="width:50px;" onclick="to(${id})">
<button class="easyui-linkbutton"style="width:80px;"  onclick="c()">取消</button>
</form>
</body>
<script type="text/javascript">
function c(){
	parent.$("#topWindow").window('close', true);
}
$(function(){
	 $('#statusId').combobox('select','${statusId}');
});
function to(id){
	$.post(
			"Business/putBusiness.do?id="+id,
			$("#Bform").serialize(),
			function(data){	//(成功后的回调函数)
			alert(data.msg);
			parent.$("#topWindow").window('close', true);
			},
			"json"	//（服务器响应的信息，最后一条不加","）
		);
}
</script>
</html>