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
<title>站内信</title>
<%@include file="script.html" %>

</head>
<body>
<p>站内信</p>
<a href="javascript:void(0);" id="addBtn" class="easyui-linkbutton" iconCls="icon-add">写信</a>
<br>
<p>我写给别人的信：</p>
<table id="dg"></table> 
<p>别人写给我的信：</p>
<table id="dg2"></table>   

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
	    url:'getMessageBM',
	    pagination:true,
	    columns:[[    
	        {field:'id',title:'id',width:50},    
	        {field:'toUserId',title:'收信人id',width:100}, 
	        {field:'fromUserId',title:'写信人id',width:100},
	        {field:'readTime',title:'阅读时间',width:150},
	        {field:'sendTime',title:'发送时间',width:150},
	        {field:'status',title:'状态码',width:100},
	        /* {field:'content',title:'内容',width:100}, */
	        {field:"caozuo",title:"操作",formatter: function(value,row,index){
					return "<a href='javascript:;' class='a' onclick='listmy("+row.id+")'>查看</a>&nbsp;"+
					"&nbsp;<a href='javascript:;' class='a' onclick='del("+row.id+")'>删除</a>";}						
					}
	    ]]    
	}); 
	
	
}) 
$(function () {
	

	$('#dg2').datagrid({    
	    url:'getMessageBO',
	    pagination:true,
	    columns:[[    
	        {field:'id',title:'id',width:50},    
	        {field:'toUserId',title:'收信人id',width:100}, 
	        {field:'fromUserId',title:'写信人id',width:100},
	        {field:'readTime',title:'阅读时间',width:150},
	        {field:'sendTime',title:'发送时间',width:150},
	        {field:'status',title:'状态码',width:100},
	        /* {field:'content',title:'内容',width:100}, */
	        {field:"caozuo",title:"操作",formatter: function(value,row,index){
					return "<a href='javascript:;' class='a' onclick='listoher("+row.id+")'>查看</a>&nbsp;"+
					"&nbsp;<a href='javascript:;' class='a' onclick='del("+row.id+")'>删除</a>";}						
					}
	    ]]    
	}); 
	
	
}) 

	//写信 添加message
	function addMessage(){
		 parent.openTopWindow({
			 title:"写信页面",
			 width:500,
			 height:500,
			 url:"messageAdd",//先经过一个servlet查询之后把结果添加到作用域当中，然后在请求转发到这个页面
			 close:function(){
				$("#dg").datagrid('reload');    
			 }
		 });
	}
$("#addBtn").click(function(e){
	addMessage();
})
	
	//查看自己写的信
	function listmy (id){
		alert("查看");
		 parent.openTopWindow({
			 title:"查看页面",
			 width:600,
			 height:600,
			 url:"messagefromlook/"+id,//先经过一个servlet查询之后把结果添加到作用域当中，然后在请求转发到这个页面
			 close:function(){
				$("#dg").datagrid('reload');    
			 }
		 });
	};
	//查看收到的信
	function listoher (id){
		alert("查看");
		 parent.openTopWindow({
			 title:"查看页面",
			 width:600,
			 height:600,
			 url:"messagetolook/"+id,//先经过一个servlet查询之后把结果添加到作用域当中，然后在请求转发到这个页面
			 close:function(){
				$("#dg").datagrid('reload');  
				window.location.reload();//刷新页面
			 }
		 });
	};
	
	//删除
	
	function del(id){
		
		 $.messager.confirm('确认', '您确认想要删除记录吗？', function(r) {
			
			if (r) {
				$.get("messageDelete/"+id, 
				function(data) {
					window.location.reload();//刷新页面
	
				}, "json");
			}
		});
	};
	
	
</script>
</html>