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
<title>岗位信息</title>
<link href="static/easyui/themes/insdep/easyui.css" rel="stylesheet" type="text/css">
<link href="static/easyui/themes/insdep/easyui_animation.css" rel="stylesheet" type="text/css">
<link href="static/easyui/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">
<link href="static/easyui/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
<link href="static/easyui/themes/insdep/icon.css" rel="stylesheet" type="text/css">
<link href="static/easyui/themes/color.css" rel="stylesheet" type="text/css">
<link href="static/ztree/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
<link href="static/css/common.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/themes/insdep/jquery.insdep-extend.min.js"></script>
<script type="text/javascript" src="static/ztree/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="static/kindeditor/kindeditor-all-min.js"></script>
</head>
<body>
<div id="tb" style="padding: 0 30px;">
			岗位名称： <input type="text" name="name" id="name"  style="border:1px solid gray;"/>
			 <a href="javascript:void(0);" id="positionSerach" class="easyui-linkbutton"
				iconCls="icon-search" data-options="selected:true">查询</a>
			 
			 <a href="javascript:void(0);" id="addBtn" class="easyui-linkbutton" 
			 	iconCls="icon-add">添加</a>
			 
		</div>


	<table id="dg"></table>
</body>
<script type="text/javascript">
/**
 *打开在父窗口中打开window
 */

$(function(){
	//alert("初始化数据");
	$('#dg').datagrid({    
	    url:'positionInfo.do',
	    type:"POST",
	    loadMsg:"数据加载中",
	    pagination:true,
	    striped:true,//斑马线效果
	    columns:[[    
	        {field:'id',title:'岗位编号'},    
	        {field:'pname',title:'岗位名称'},
	        {field:'p2name',title:'上级岗位'},
	        {field:'dname',title:'所属部门'},
	       	{field:'description',title:'简单描述'},
	        
	        {field:"caozuo",title:"&nbsp;&nbsp;操作",formatter: function(value,row,index){
					return "&nbsp;<a href='javascript:;' class='a' onclick='update("+row.id+")'>修改</a>&nbsp;"+
					"&nbsp;<a href='javascript:;' class='a' onclick='del("+row.id+")'>删除</a>&nbsp;"
					}}
	     ]]    
	});
	//添加按钮
	$('#addBtn').click(function(){
		//
		parent.openTopWindow({ 
			 title:"添加公告信息",
			 width:800,
			 height:600,
			 url:"addPositionBefore.do",
			 close:function(){
					$("#dg").datagrid('reload');    
			 }
			 })
	});
	//模糊查询
	$('#positionSerach').click(function(){
		var name = $("#name").val();
		alert(name);
		$('#dg').datagrid({    
		    url:'positionSerachSelect.do?name='+name,
		    type:"GET",
		    loadMsg:"数据加载中",
		    pagination:true,
		    striped:true,//斑马线效果
		    columns:[[    
		    	{field:'id',title:'岗位编号'},    
		        {field:'pname',title:'岗位名称'},
		        {field:'p2name',title:'上级岗位'},
		        {field:'dname',title:'所属部门'},
		       	{field:'description',title:'简单描述'},
		        
		        {field:"caozuo",title:"&nbsp;&nbsp;操作",formatter: function(value,row,index){
						return "&nbsp;<a href='javascript:;' class='a' onclick='update("+row.id+")'>修改</a>&nbsp;"+
						"&nbsp;<a href='javascript:;' class='a' onclick='del("+row.id+")'>删除</a>&nbsp;"
						}}
		    ]]    
		});
	});
});
function update(id){
	parent.openTopWindow({ 
		 title:"修改岗位信息",
		 width:800,
		 height:600,
		 url:"editPositionBefore.do?id="+id,//跳转修改页面
		 close:function(){
				$("#dg").datagrid('reload');    
		 }
		 });
}
function del(id){
	alert(id);
	$.get(
		"deletePosition.do?id="+id,
		function(data){
			alert(data.message);
			$("#dg").datagrid('reload');
			//	window.location.reload();
		},
		"json"
	);
}

</script>
</html>