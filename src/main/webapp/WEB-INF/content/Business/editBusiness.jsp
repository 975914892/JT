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
    <div data-options="region:'north',title:'商机详情',collapsible:false" style="height:80px;text-align:center;">
    	<input type="button" value="修改" onclick="a(${business.id})">
    	<input type="button" value="删除" onclick="b(${business.id})">
    	<input type="button" value="返回" onclick="c()">
    </div>   
    <div data-options="region:'south',split:true,collapsible:false" style="height:100px;"></div>
    <div data-options="region:'east',iconCls:'icon-reload',title:'商机详情',collapsible:false" style="width:180px;">
	    <button class="easyui-linkbutton" onclick="">推进</button><br>
    	<button class="easyui-linkbutton" onclick="">添加沟通日志(自动)</button><br>
    </div>   
    <div data-options="region:'west',title:'商机详情',collapsible:false" style="width:180px;">
    	<div class="d1" tabindex="1" onclick="window.location.reload();">基本信息</div>
    	<div class="d1" tabindex="1" onclick="goutong()">沟通日志</div>
    	<div class="d1" tabindex="1" onclick="fuze()">负责人日志</div>
    	<div class="d1" tabindex="1" onclick="hetong()">合同</div>
    </div>   
    <div id="center" data-options="region:'center',collapsible:false" style="padding:5px;background:#eee;">
    	<form action="" id="addBusiness">
			<div class="dd">
			
			<table>
				<tr>
					<th>负责人:</th>
					<td><label>${BVO.ownerName}</label>
					<th>创建时间:</th>
					<td><label>${business.createTime}</label></td>
				</tr>
				<tr>
					<th>上次推进时间:</th>
					<td><label>${business.updateTime}</label>
					<th>客户:</th>
					<td><label>${BVO.customerName}</label></td>
				</tr>
				<tr>
					<th>商机余额:</th>
					<td><label>${business.totalPrice}</label></td>
					<th>商机名:</th>
					<td><label>${business.name}</label></td>
				</tr>
				<tr>
					<th>联系人:</th>
					<td><label>${BVO.contactsName}</label></td>
					<th>合同签订地址:</th>
					<td><label>${business.contractAddress}</label>
					</td>
				</tr>
				<tr>
					<th>商机类型:</th>
					<td><label>${business.type}</label></td>
					<th>状态:</th>
					<td><label>
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
						</label></td>
				</tr>
				<tr>
					<th>商机来源:</th>
					<td><label>${business.origin}</label></td>
					<th>赢单率:</th>
					<td><label>${business.gainRate}</label></td>
				</tr>
				<tr>
					<th>预计成交价:</th>
					<td><label>${business.estimatePrice}</label></td>
					<th>下次联系时间:</th>
					<td><label>${business.nextstepTime}</label></td>
				</tr>
				<tr>
					<th>下次联系内容:</th>
					<td><label>${business.nextstep}</label>
				</tr>
				<tr>
					<th>备注:</th>
					<td><label>${business.descrption}</label>
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
	$("#b1").load("Business/editBusiness.do?method=update&id="+id);
}
function b(id){
	$.messager.confirm('你可看好了!', '真的要删除这条线索吗？', function(r) {
		if (r) {
			$.get("Business/deleteBusinessOne.do", 
				{ "id" : id },
		    	function(data){
					alert(data.flag);
					parent.$("#topWindow").window('close', true);
		    	},"json");
		}
	});
	
}
$(function(){
	 $('#statusId').combobox('select','${business.statusId}');
});

</script>
<script type="text/javascript">

function goutong(){
	var id = ${business.id};
	$("#center").load("Business/businessA.do?method=goutong&id="+id);
	
}
function fuze(){
	var id = ${business.id};
	$("#center").load("Business/businessA.do?method=fuze&id="+id);
}
function hetong(){
	var id = ${business.id};
	$("#center").load("Business/businessA.do?method=hetong&id="+id);
}
function tuijin(){
	var id = ${business.id};
	$("#center").load("Business/putB.do?method=tuijin&id="+id);
}
</script>

</html>