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
<title>营销管理</title>
<link href="static/easyui/themes/insdep/easyui.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>

<style type="text/css">
	#subject{
		height: 40px;
		width: 400px;
	}
	#content{
		height: 150px;
		width: 400px;
		border:1px bule solid;
	}
	input{
	border:1px solid gray;
	}
	.a{
		text-decoration:none;
	}
	
</style>
<body>

<div id="cc" class="easyui-layout" style="width:1100px;height:530px;"> 
<!-- 顶部页面   -->
    <div data-options="region:'north',title:'营销管理',split:true" style="height:0px;"></div>
<!-- 左侧页面   -->   
    
<!-- 主页面   -->   
    <div data-options="region:'center',title:''" style="padding:5px;background:#eee;">
    	
		   	<div id="tt" class="easyui-tabs" style="width:960px;height:450px;"> 
		   			<div title="首页" data-options="iconCls:'icon-reload',closable:false" style="padding:20px;display:none;">   
				      	  欢迎你！    
				    </div>  
				    <div title="发短信" style="padding:20px;display:none;">   
				        <form action="#" method="post" id="sendMessageFrom">

							收信人：<input type="text" name="name"  /><br/>
							电话号码：<input type="text" name="telephone"  /><br/>
							</textarea>
							<br /> 
							<input type="button" value="发短信" id="sendMessageSub">
						</form>
				</div>   
				    <div title="发邮件" data-options="closable:false" style="overflow:auto;padding:20px;display:none;">   
				        <form action="#" method="post" id="sendEmailFrom">

							发送人：<input type="text" name="username" value="${sessionScope.user.username }"><br> 
							接收人邮箱：<input type="text" name="toAddr" value=""><br> 
							邮件主题 :<br />
							<textarea name="subject" id="subject">
							</textarea>
							<br /> 
							邮件内容 :<br />
							<textarea name="content" id="content">
							</textarea>
							<br /> 
							<input type="button" value="发邮件" id="sendEmailSub">
						</form>   
				    </div>   
				    <div title="短信收件箱" data-options="iconCls:'icon-reload',closable:false" style="padding:20px;display:none;">   
				           <table id="dgMessage"></table> 
				    </div>
				    <div title="邮件收件箱" data-options="iconCls:'icon-reload',closable:false" style="padding:20px;display:none;">   
				       	<table id="dgEmail"></table>  
				    </div>  
			</div> 	
    </div>   
</div> 


</body>
<script type="text/javascript">
$(function(){
	
	$("#sendMessageSub").click(function(){
	
		$.post(
			"sendMessageFrom.do",
			$("#sendMessageFrom").serialize(),
			function(data){
				alert(data.message);
				
			},
			"json"
		);
});
	
	$("#sendEmailSub").click(function(){
	
		$.post(
			"sendEmailSub.do",
			$("#sendEmailFrom").serialize(),
			function(data){
				alert(data.message);
				
			},
			"json"
		);
});
	//短信收件箱
	[id=1, templateCode=1, subject=1, content=1, orderId=1]
	$('#dgMessage').datagrid({    
	    url:'inintDgMessage.do',
	    type:"POST",
	    loadMsg:"数据加载中",
	    pagination:true,
	    striped:true,//斑马线效果
	    columns:[[    
	    	{field:'id',title:'模板编号',width:120},    
	        {field:'templateCode',title:'编码模板',width:120},
	        {field:'subject',title:'主题',width:120},
	        {field:'content',title:'内容',width:120},
	       	{field:'orderId',title:'顺序号',width:120},
	       
	        {field:"caozuo",title:"&nbsp;&nbsp;操作",width:120,formatter: function(value,row,index){
					return "<a href='javascript:;' class='a' onclick='del("+row.id+")'>删除</a>&nbsp;"
					
					}}
	     ]]    
	});
	
	//邮件收件箱
	$('#dgEmail').datagrid({    
	    url:'initDgEmail.do',
	    type:"POST",
	    loadMsg:"数据加载中",
	    pagination:true,
	    striped:true,//斑马线效果
	    columns:[[    
	    	{field:'id',title:'模板编号',width:120},    
	        {field:'subject',title:'主题',width:120},
	        {field:'title',title:'标题',width:120},
	        {field:'content',title:'内容',width:120},
	       	{field:'orderId',title:'顺序号',width:120},
	       
	        {field:"caozuo",title:"&nbsp;&nbsp;操作",width:120,formatter: function(value,row,index){
	        	return "<a href='javascript:;' class='a' onclick='del("+row.id+")'>删除</a>&nbsp;"
					}}
	     ]]    
	});
})
</script>
</html>
