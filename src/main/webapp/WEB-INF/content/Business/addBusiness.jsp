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
<link href="static/easyui/themes/insdep/easyui_animation.css" rel="stylesheet" type="text/css">
<link href="static/easyui/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
<link href="static/easyui/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="static/css/style.css">
<link rel="stylesheet" type="text/css" href="static/css/component.css" />

<script type="text/javascript" src="static/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="static/js/easyform.js"></script>
<script type="text/javascript" src="static/js/PCASClass.js"></script>
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/themes/insdep/jquery.insdep-extend.min.js"></script>
<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>

<style type="text/css">
a:hover {
    color:blue;
     text-decoration:none;
    }
input[type="text"]{
float: left;
}
.form-div{   
	display: none;
	position: absolute;   
	top: 10%;   
	left: 30%;   
	background-color: white;   
	text-align: center;
        }
</style>
</head>
<body>
<!-- 通过选中的客户给商机名选择值 -->
<div class="form-div" id="Div1">
	<c:forEach items="${userListAtBusiness }" var="u">
		<button class="easyui-linkbutton" onclick="chuan1('${u.USERNAME }')">${u.USERNAME }</button><br>
	</c:forEach>
</div>
<div class="form-div" id="Div2">
	<c:forEach items="${contactsListAtBusiness }" var="c">
		<button class="easyui-linkbutton" onclick="chuan2('${c.NAME }')">${c.NAME }</button><br>
	</c:forEach>
</div>
<h4>主要信息</h4>
<form action="" id="businessForm">
	<table>
		<tr>
			<th>负责人：</th>
			<td>
				<input type="text" value="${user.username }" name="ownerName" id="ownerName" onclick="a()">
			</td>
			<th>客户：</th>
			<td>
				<select  style="width:150px;height: 36px;" name="customerId" id="customerId"
				onchange="s(this.options[this.options.selectedIndex].value)">
						<option value="0">请选择客户</option>
					<c:forEach items="${customerListAtBusiness }" var="c">
						<option value="${c.ID }">${c.NAME }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>商机金额：</th>
			<td><input type="text" name="totalPrice" id="totalPrice"></td>
			<th>商机名：</th>
			<td><input type="text" name="name" id="name" ><font color="red">*必填项</font></td>
		</tr>
		<tr>
			<th>联系人：</th>
			<td><input type="text" name="contactsName" id="contactsName" onclick="b()"></td>
			<th>合同签订地址：</th>
			<td><input type="text" name="contractAddress" id="contractAddress"></td>
		</tr>
		<tr>
			<th>商机类型：</th>
			<td><input type="text" name="type" id="type"></td>
			<th>状态：</th>
			<td>
				<select class="easyui-combobox" style="width:130px;" name="statusId"  id="statusId">
					<option value="1">意向客户</option>
					<option value="2">上钩客户</option>
					<option value="3">成交客户</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>商机来源：</th>
			<td><input type="text" name="origin" id="origin"></td>
			<th>赢单率：</th>
			<td><input type="text" name="gainRate" id="gainRate"><font color="red">(%)</font></td>
		</tr>
		<tr>
			<th>预计成交价：</th>
			<td><input type="text" name="estimatePrice" id="estimatePrice"></td>
			<th>下次联系时间：</th>
			<td><input type="text" name="nextstepTime" id="nextstepTime" class= "easyui-datebox"></td>
		</tr>
		<tr>
			<th>下次联系内容</th>
			<td><input type="text" name="nextstep" id="nextstep"></td>
		</tr>
	</table>
	<h4>附加信息</h4>
	<table>
		<tr>
			<th>备注</th>
			<td><input type="text" name="descrption" id="descrption" style="width:500px;height:100px"  value=""></td>
		</tr>
	</table>
	<input type="button" value="保存" onclick="save()">
	<input type="button" value="保存并新建" onclick="addBusiness()">
	<button class="easyui-linkbutton"  onclick="c()">返回</button>
</form>
</body>
<script type="text/javascript">

function s(v){
	$.post(
			"Business/littleSelect.do",
			{"customerId":v},
			function(data){	//(成功后的回调函数)
				
				$("#name").val(data.name);
				
			},
			"json"	//（服务器响应的信息，最后一条不加","）
		);
}
//
function a(){
	document.getElementById('Div1').style.display='block';
}
function b(){
	document.getElementById('Div2').style.display='block';
}
function c(){
	parent.$("#topWindow").window('close', true);
}
function chuan1(v){
	$("#ownerName").val(v);
	document.getElementById('Div1').style.display='none';
}
function chuan2(v){
	$("#contactsName").val(v);
	document.getElementById('Div2').style.display='none';
}
</script>
<script type="text/javascript">
function addBusiness(){
	$.post(
			"Business/addBusiness.do?method=add",
			$("#businessForm").serialize(),
			function(data){	//(成功后的回调函数)
			alert(data.msg);
			parent.$("#topWindow").window('close', true);
			},
			"json"	//（服务器响应的信息，最后一条不加","）
		);
}

</script>

</html>