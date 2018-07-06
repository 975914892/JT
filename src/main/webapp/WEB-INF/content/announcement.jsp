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
<title>公告信息</title>
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
			标题名： <input type="text" name="title" id="title"  style="border:1px solid gray;"/>
			 <a href="javascript:void(0);" id="titleSerach" class="easyui-linkbutton"
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
	    url:'getAnnouncementInfo.do',
	    type:"POST",
	    loadMsg:"数据加载中",
	    pagination:true,
	    striped:true,//斑马线效果
	    columns:[[    
	        {field:'id',title:'文章编号'},    
	        {field:'orderId',title:'排序'},
	        {field:'username',title:'发表人'},
	        {field:'title',title:'标题'},
	       	{field:'content',title:'内容',width:100},
	        {field:'createTime',title:'创建时间',width:100},
	        {field:'updateTime',title:'更新时间',width:100},
	        {field:'color',title:'颜色'},
	        {field:'department',title:'通知部门'},
	        {field:'status',title:'是否发布'},
	        {field:'isshow',title:'是否公开'},
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
			 url:"addAnnouncementBefore.do",
			 close:function(){
					$("#dg").datagrid('reload');    
			 }
			 })
	});
	//模糊查询
	$('#titleSerach').click(function(){
		var title = $("#title").val();
		alert(title);
		$('#dg').datagrid({    
		    url:'titleSerachSelect.do?title='+title,
		    type:"GET",
		    loadMsg:"数据加载中",
		    pagination:true,
		    striped:true,//斑马线效果
		    columns:[[    
		    	{field:'id',title:'文章编号'},    
		        {field:'orderId',title:'排序'},
		        {field:'username',title:'发表人'},
		        {field:'title',title:'标题'},
		       	{field:'content',title:'内容',width:100},
		        {field:'createTime',title:'创建时间',width:100},
		        {field:'updateTime',title:'更新时间',width:100},
		        {field:'color',title:'颜色'},
		        {field:'department',title:'通知部门'},
		        {field:'status',title:'是否发布'},
		        {field:'isshow',title:'是否公开'},
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
		 title:"修改公告信息",
		 width:800,
		 height:600,
		 url:"editAnnouncementBefore.do?id="+id,//跳转修改页面
		 close:function(){
				$("#dg").datagrid('reload');    
		 }
		 });
}
function del(id){
	alert(id);
	$.get(
		"deleteAnnouncement.do?id="+id,
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