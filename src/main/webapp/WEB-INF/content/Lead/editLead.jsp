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
<title>线索详情</title>
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

<style type="text/css">
.d1:focus {
    background-color:#029be2;
    color: white;
}
.d1 {
	font-size:10px;
	text-align: center;
	line-height: 30px;
	height: 30px;
}
</style>
</head>
<body id="b1">   
<div id="cc" class="easyui-layout" style="width:100%;height:100%;">
    <div data-options="region:'north',title:'线索详情',collapsible:false" style="height:80px;text-align:center;">
    	<input type="button" value="修改" onclick="a(${lead.id})">
    	<input type="button" value="删除" onclick="b(${lead.id})">
    	<input type="button" value="返回" onclick="c()">
    </div>   
    <div data-options="region:'south',split:true,collapsible:false" style="height:100px;"></div>
    <div data-options="region:'east',iconCls:'icon-reload',title:'编辑详情',collapsible:false" style="width:180px;">
    	<button class="easyui-linkbutton" onclick="zhuanhuan()">转换为客户</button><br>
    </div>   
    <div data-options="region:'west',title:'线索详情',collapsible:false" style="width:180px;">
    	<div class="d1" tabindex="1" onclick="window.location.reload();">基本信息</div>
    	<div class="d1" tabindex="1" onclick="goutong()">沟通日志</div>
    	<div class="d1" tabindex="1" onclick="fuze()">负责人日志</div>
    </div>   
    <div id="center" data-options="region:'center',collapsible:false" style="padding:5px;background:#eee;">
    	<form action="" id="addLead">
			<div class="dd">
			
			<table>
				<tr>
					<th>创建时间:</th>
					<td><label>${lead.createTime}</label>
					<th>创建人:</th>
					<td><label>${leadVO.creatorName}</label></td>
				</tr>
				<tr>
					<th>负责人:</th>
					<td><label>${leadVO.ownerName}</label>
					<th>公司名:</th>
					<td><label>${lead.name}</label></td>
				</tr>
				<tr>
					<th>来源:</th>
					<td><label>${lead.source}</label></td>
					<th>联系人姓名:</th>
					<td><label>${lead.contactsName}</label></td>
				</tr>
				<tr>
					<th>职位:</th>
					<td><label>${lead.position}</label></td>
					<th>尊称:</th>
					<td><label>${lead.saltname}</label>
					</td>
				</tr>
				<tr>
					<th>手机:</th>
					<td><label>${lead.mobile}</label></td>
					<th>邮箱:</th>
					<td><label>${lead.email}</label></td>
				</tr>
				<tr>
					<th>地址:</th>
					<td><label>${lead.address}</label></td>
					<th>最后一次更新时间:</th>
					<td><label>${lead.updateTime}</label></td>
				</tr>
				<tr>
					<th>下次联系时间:</th>
					<td><label>${lead.nextstepTime}</label></td>
					<th>下次联系内容:</th>
					<td><label>${lead.nextstep}</label></td>
				</tr>
				<tr>
					<th>备注</th>
					<td><label>${lead.leadnote}</label>
				</tr>
			</table>
			</div>
		</form>
    </div>   
</div>
</body>
<script type="text/javascript">
function c(){
	parent.$("#topWindow").window('close', true);
}
function a(id){
	$("#b1").load("Lead/editLead.do?method=update&id="+id);
}
function b(id){
	$.messager.confirm('你可看好了!', '真的要删除这条线索吗？', function(r) {
		if (r) {
			$.get("Lead/deleteLeadOne.do", 
				{ "id" : id },
		    	function(data){
					alert(data.flag);
					parent.$("#topWindow").window('close', true);
		    	},"json");
		}
	});
	
}

</script>
<script type="text/javascript">

function goutong(){
	var id = ${lead.id};
	$("#center").load("Lead/leadA.do?method=goutong&id="+id);
	
}
function fuze(){
	var id = ${lead.id};
	$("#center").load("Lead/leadA.do?method=fuze&id="+id);
}
function zhuanhuan(){
	var id = ${lead.id};
	$("#center").load("Lead/editLead.do?id="+id+"&method=convert");
}
</script>

</html>