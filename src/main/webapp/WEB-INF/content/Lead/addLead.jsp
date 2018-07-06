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
<h1>添加线索</h1>
<hr style="border: 1px solid gray"/>
<p></p>
	<div class="dd">
	<h4>主要信息</h4>
<form action="" id="addLead">
	<table>
		<tr>
			<th>负责人:</th>
			<td><input name="ownerName" id="ownerName" type="text" id="name" value="${user.username }">
			<input type="button" value="放入线索池" onclick="put()">
			<a onclick="what()">什么是线索池</a></td>
			<th>公司名:</th>
			<td><input type="text" name="name" id="name" value="${leadVO.name }"></td>
		</tr>
		<tr>
			<th>来源:</th>
			<td><input type="text" name="source" id="source" value="${leadVO.source }"></td>
			<th>联系人姓名:</th>
			<td><input type="text" name="contactsName" id="contactsName"  value="${leadVO.contactsName }"></td>
			<td><font color="red">*必填项</font></td>
		</tr>
		<tr>
			<th>职位:</th>
			<td><input type="text" name="position" id="position"  value="${leadVO.position }"></td>
			<th>尊称:</th>
			<td><select class="easyui-combobox" style="width:160px;" name="saltname" id="saltname">
					<option value="null">--请选择--</option>
					<option value="先生">先生</option>
					<option value="小姐">小姐</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>手机:</th>
			<td><input type="text" name="mobile" id="mobile" value="${leadVO.mobile }"></td>
			<th>邮箱:</th>
			<td><input type="text" name="email" id="email" value="${leadVO.email }"></td>
		</tr>
		<tr>
			<th>地址:</th>
			<td>
			<select  style="width:140px;" name="province" id="province"></select>
			<select  style="width:140px;" name="city" id="city"></select>
			<select  style="width:140px;" name="area" id="area"></select>
			
			<input type="text" name="street" id="street" placeholder="街道信息" style="float: none;"  value="${leadVO.street }">
			</td>
		</tr>
		<tr>
			<th>下次联系时间:</th>
			<td><input name="nextstepTime" id="nextstepTime" type="text" class= "easyui-datebox" style="float: left;"  value="${leadVO.nextstepTime }"></td>
			<th>下次联系内容:</th>
			<td><input type="text" name="nextstep" id="nextstep"  value="${leadVO.nextstep }"></td>
		</tr>
	</table>
	<h4>附加信息</h4>
	<table>
		<tr>
			<th>备注</th>
			<td><input type="text" name="leadnote" id="leadnote" style="width:500px;height:100px"  value="${leadVO.leadnote }"></td>
		</tr>
	</table>
	<input type="button" value="保存" onclick="save()">
	<input type="button" value="保存并新建" onclick="addLead()">
	<button class="easyui-linkbutton"  onclick="c()">返回</button>
	</div>
</form>

</body>
<script language="javascript" defer>
new PCAS("province","city","area");
$(function(){

	 $('#saltname').combobox('select','${leadVO.saltname}');
	 /* $('#province').combobox('select','${leadVO.province}');
	 $('#city').combobox('select','${leadVO.city}');
	 $('#area').combobox('select','${leadVO.area}'); */
	
});
</script>
<script type="text/javascript">
function c(){
	parent.$("#topWindow").window('close', true);
}
function save(){
	alert($("#street").val());
	$.post(
			"Lead/addLead2.do?method=save",
			$("#addLead").serialize(),
			function(data){	//(成功后的回调函数)
			alert(data.flag);
			},
			"json"	//（服务器响应的信息，最后一条不加","）
		);
}

function addLead(){
//	var a = $("#addLead").serializeObject();  //序列化为json数据
//	var b = JSON.stringify(a);
//	alert(b);
	alert($("#street").val());
	$.post(
			"Lead/addLead2.do?method=add",
			$("#addLead").serialize(),
			function(data){	//(成功后的回调函数)
			alert(data.flag);
			},
			"json"	//（服务器响应的信息，最后一条不加","）
		);
}
//放入线索池
function put(){
	document.getElementById('ownerName').value="";
	alert("已放入线索池");
}
//什么是线索池
function what(){
	alert("线索池是存放无人领取或无人负责的线索的集合。如果新建的线索没有线索负责人，或者工作人员没有在规定时间内将领取的线索进行转换，线索将自动回收入线索池，以防止个人占有大量线索而其他人无法领取线索等情况的发生。而那些已经没有线索的员工，可以从线索池中领取线索。对于员工来说，线索转化为客户的数量越多，说明该员工的工作效果越高。对企业来说，从线索到客户的转换，意味着客户将为企业带来更多利益。");
}

</script>

</html>