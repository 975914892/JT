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
</style>
</head>
<body>
<form action="" id="convertLead">
	<table>
	<h4>主要信息</h4>
	<tr>
		<th>负责人:</th>
		<td><input type="text" name="ownerName" id="ownerName" value="${username }">&nbsp;&nbsp;
		<input type="button" onclick="put()" value="放入客户池"></td>
		<th>客户名称:</th>
		<td><input type="text" name="name"  value="${lead.name }"><font color="red">*必填项</font></td>
	</tr>
	<tr>
		<th>客户行业</th>
		<td>
		<select id="industry" name="industry">
			<option value="">--请选择--</option>
			<option value="计算机">计算机</option>
			<option value="金融">金融</option>
			<option value="医药">医药</option>
			<option value="警察">警察</option>
		</select>
		</td>
		<th>客户信息来源</th>
		<td>
		<select id="origin" name="origin">
			<option value="无">--请选择--</option>
			<option value="玩游戏">玩游戏</option>
			<option value="卖保险">卖保险</option>
			<option value="看病">看病</option>
			<option value="抢劫">抢劫</option>
		</select>
		</td>
	</tr>
	<tr>
		<th>公司性质</th>
		<td>
			<input type="radio" name="ownership" value="合资"/>合资&nbsp;&nbsp;
			<input type="radio" name="ownership" value="国企"/>国企&nbsp;&nbsp;
			<input type="radio" name="ownership" value="民营"/>民营
		</td>
		<th>邮编</th>
		<td><input type="text" name="zip_code" value="" ></td>
	</tr>
	<tr>
		<th>年营业额</th>
		<td><input type="text" name="annual_revenue" value="" ></td>
		<th>评分</th>
		<td>
			<input type="radio" name="rating" value="一星"/>一星&nbsp;&nbsp;
			<input type="radio" name="rating" value="二星"/>二星&nbsp;&nbsp;
			<input type="radio" name="rating" value="三星"/>三星
		</td>
	</tr>
	<tr>
		<th>联系地址</th>
		<td><input type="text" name="address" value="${lead.address }" ></td>
	</tr>
	</table>
	<table>
	<h4>首要联系人信息</h4>
	<tr>
		<th>姓名</th>
		<td><input type="text" name="contactsName" value="${lead.contactsName }" ></td>
		<th>称呼</th>
		<td><input type="text" name="saltname" value="${lead.saltname }" ></td>
	</tr>
	<tr>
		<th>邮箱</th>
		<td><input type="text" name="email" value="${lead.email }" ></td>
		<th>职位</th>
		<td><input type="text" name="post" value="${lead.position }" ></td>
	</tr>
	<tr>
		<th>QQ</th>
		<td><input type="text" name="qqNo" value="" ></td>
		<th>手机</th>
		<td><input type="text" name="telephone" value="${lead.mobile }" ></td>
	</tr>
	</table>
	<table>
	<tr>
		<th>备注</th>
		<td><input type="text" name="description" id="description" style="width:500px;height:100px"  value="${lead.leadnote }"></td>
	</tr>
	</table>
	<input type="button" value="保存" onclick="save()">
	<input type="button" value="保存并新建" onclick="addCustomer()">
	<button class="easyui-linkbutton"  onclick="c()">返回</button>
	<input type="checkbox" value="shangji">同时创建商机

</form>

</body>
<script type="text/javascript">
function c(){
	parent.$("#topWindow").window('close', true);
}
//放入客户池
function put(){
	document.getElementById('ownerName').value="";
	alert("已放入客户池，请保存");
}
</script>
<script type="text/javascript">

function addCustomer(){
	$.post(
			"Lead/addCustomer.do?method=add",
			$("#convertLead").serialize(),
			function(data){	//(成功后的回调函数)
			alert(data.msg);
			},
			"json"	//（服务器响应的信息，最后一条不加","）
		);
}

function save(){
	$.post(
			"Lead/addCustomer.do?method=save",
			$("#convertLead").serialize(),
			function(data){	//(成功后的回调函数)
			alert(data.msg);
			},
			"json"	//（服务器响应的信息，最后一条不加","）
		);
}

</script>
</html>