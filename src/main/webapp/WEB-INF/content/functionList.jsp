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
<title>权限管理</title>
<%@include file="script.html" %>

</head>
<body>
<p>权限管理</p>
<a href="javascript:void(0);" id="addBtn" class="easyui-linkbutton" iconCls="icon-add">添加权限</a>
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
	    url:'selectFunction',
	    pagination:true,
	    columns:[[    
	        {field:'funcId',title:'id',width:50},    
	        {field:'funcName',title:'权限名',width:100}, 
	        {field:'funcUrl',title:'路径',width:100},
	        {field:'parentId',title:'父id',width:100},
	        {field:"caozuo",title:"操作",formatter: function(value,row,index){
					return "<a href='javascript:;' class='a' onclick='update("+row.funcId+")'>修改</a>&nbsp;"+
					"&nbsp;<a href='javascript:;' class='a' onclick='del("+row.funcId+")'>删除</a>";}						
					}
	    ]]    
	}); 
	
	
}) 

	//添加权限
	function addRole(){
		 parent.openTopWindow({
			 title:"添加页面",
			 width:500,
			 height:500,
			 url:"functionAdd",//先经过一个servlet查询之后把结果添加到作用域当中，然后在请求转发到这个页面
			 close:function(){
				$("#dg").datagrid('reload');    
			 }
		 });
	}
	$("#addBtn").click(function(e){
		addRole();
	})
	
	//超链接   修改操作
	function update (funcId){
		alert("修改");
		 parent.openTopWindow({
			 title:"修改页面",
			 width:500,
			 height:500,
			 url:"functionUpdateList/"+funcId,//先经过一个servlet查询之后把结果添加到作用域当中，然后在请求转发到这个页面
			 close:function(){
				$("#dg").datagrid('reload');    
			 }
		 });
	};
	
	//删除
	
	function del(funcId){
		
		 $.messager.confirm('确认', '您确认想要删除记录吗？', function(r) {
			
			if (r) {
				$.get("functionDelete/"+funcId, 
				function(data) {
					window.location.reload();//刷新页面
	
				}, "json");
			}
		});
	};
	
	
</script>
</html>