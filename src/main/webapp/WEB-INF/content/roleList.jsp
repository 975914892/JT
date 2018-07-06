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
<title>角色管理</title>

<%@include file="script.html" %>

</head>
<body>
<p>角色管理</p>
<a href="javascript:void(0);" id="addBtn" class="easyui-linkbutton" iconCls="icon-add">添加角色</a>
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
	    url:'selectRole',
	    pagination:true,
	    columns:[[    
	        {field:'roleId',title:'id',width:50},    
	        {field:'roleName',title:'角色名',width:100}, 
	        {field:'note',title:'备注',width:200},
	        {field:'status',title:'状态码',width:100},
	        {field:'createTime',title:'开始日期',width:150},
	        {field:'updateTime',title:'更新日期',width:150},
	        {field:"caozuo",title:"操作",formatter: function(value,row,index){
					return "<a href='javascript:;' class='a' onclick='update("+row.roleId+")'>修改</a>&nbsp;"+
					"&nbsp;<a href='javascript:;' class='a' onclick='del("+row.roleId+")'>删除</a>"+
					"&nbsp;<a href='javascript:;' class='a' onclick='addTask("+row.roleId+")'>授权</a>";}						
					}
	    ]]    
	}); 
	
	
}) 
	
	//添加用户
	function addRole(){
		 parent.openTopWindow({
			 title:"添加页面",
			 width:500,
			 height:500,
			 url:"roleAdd",//先经过一个servlet查询之后把结果添加到作用域当中，然后在请求转发到这个页面
			 close:function(){
				$("#dg").datagrid('reload');    
			 }
		 });
	}
	$("#addBtn").click(function(e){
		addRole();
	})
	
	//超链接   修改操作
	function update (roleId){
		alert("修改");
		 parent.openTopWindow({
			 title:"修改页面",
			 width:500,
			 height:500,
			 url:"roleUpdateList/"+roleId,//先经过一个servlet查询之后把结果添加到作用域当中，然后在请求转发到这个页面
			 close:function(){
				$("#dg").datagrid('reload');    
			 }
		 });
	};
	
	//删除
	
	function del(roleId){
		
		 $.messager.confirm('确认', '您确认想要删除记录吗？', function(r) {
			
			if (r) {
				$.get("roleDelete/"+roleId, 
				function(data) {
					window.location.reload();//刷新页面
	
				}, "json");
			}
		});
	};
	
	//授权操作
	function addTask (roleId){
		alert("授权");
		 parent.openTopWindow({
			 title:"授权页面",
			 width:500,
			 height:500,
			 url:"RoleForFunction?roleId="+roleId,//先经过一个servlet查询之后把结果添加到作用域当中，然后在请求转发到这个页面
			 close:function(){
				$("#dg").datagrid('reload');    
			 }
		 });
	};
	
</script>
</html>