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
<title>用户管理</title>
<%@include file="script.html" %>

</head>
<body>
<p>用户管理</p>
<a href="javascript:void(0);" id="addBtn" class="easyui-linkbutton" iconCls="icon-add">添加用户</a>
<table id="dg"></table>  

</body>
<script type="text/javascript">
/**
 *打开在父窗口中打开window
 */
function openTopWindow(options) {
	options = !options ? {} : options;
	options.width = !options.width ? 500 : options.width;
	options.height = !options.height ? 400 : options.height;
	options.title = !options.title ? "" : options.title;
	parent.$("#iframe").attr("src", options.url);
	parent.$("#topWindow").window({
		title : options.title,
		width : options.width,
		height : options.height,
		modal : true,
		resizable : true,
		collapsible : false,
	});
	
}

$(function () {
	

	$('#dg').datagrid({    
	    url:'selectUser',
	    pagination:true,
	    columns:[[    
	        {field:'userId',title:'id',width:50},    
	        {field:'username',title:'用户名',width:100},    
	        {field:'password',title:'密码',width:100},
	        {field:'createTime',title:'开始日期',width:150},
	        {field:'updateTime',title:'更新日期',width:150},
	        {field:"caozuo",title:"操作",formatter: function(value,row,index){
					return "<a href='javascript:;' class='a' onclick='update("+row.userId+")'>修改</a>&nbsp;"+
					"&nbsp;<a href='javascript:;' class='a' onclick='del("+row.userId+")'>删除</a>"+
					"&nbsp;<a href='javascript:;' class='a' onclick='addTask("+row.userId+")'>授权</a>";}						
					}
	    ]]    
	}); 
	
	
}) 
	
	//添加用户
	function addUser(){
		 parent.openTopWindow({
			 title:"添加页面",
			 width:500,
			 height:500,
			 url:"userAdd",//先经过一个servlet查询之后把结果添加到作用域当中，然后在请求转发到这个页面
			 close:function(){
				$("#dg").datagrid('reload');    
			 }
		 });
	}
	$("#addBtn").click(function(e){
		addUser();
	})
	
	//超链接   修改操作
	function update (userId){
		 parent.openTopWindow({
			 title:"修改页面",
			 width:500,
			 height:500,
			 url:"userUpdateList/"+userId,//先经过一个servlet查询之后把结果添加到作用域当中，然后在请求转发到这个页面
			 close:function(){
				$("#dg").datagrid('reload');    
			 }
		 });
	};
	
	//删除
	
	function del(userId){
		
		 $.messager.confirm('确认', '您确认想要删除记录吗？', function(r) {
			
			if (r) {
				$.get("userDelete/"+userId, 
				function(data) {
					window.location.reload();//刷新页面
	
				}, "json");
			}
		});
	};
	
	
	
	//授权操作
	function addTask (userId){
		alert("授权");
		 parent.openTopWindow({
			 title:"授权页面",
			 width:500,
			 height:500,
			 url:"UserForRole?userId="+userId,//先经过一个servlet查询之后把结果添加到作用域当中，然后在请求转发到这个页面
			 close:function(){
				$("#dg").datagrid('reload');    
			 }
		 });
	};
</script>
</html>