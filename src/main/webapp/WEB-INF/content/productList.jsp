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
<title>产品管理</title>
<%@include file="script.html" %>

</head>
<body>
<a href="product.do" id="proCat" class="easyui-linkbutton" >产品管理</a>
<a href="javascript:void(0);" id="addproduct" class="easyui-linkbutton" iconCls="icon-add">添加产品</a><br>
<a href="productCategory.do" id="proCat" class="easyui-linkbutton" >产品分类管理</a>
<a href="javascript:void(0);" id="addproductCategory" class="easyui-linkbutton" iconCls="icon-add">添加分类</a><br>
产品名称：<input type="text" name="proname" id="proname" style="border:1px solid gray;"/>
<a href="javascript:void(0);" id="pronameSerach" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
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
	    url:'selectProduct',
	    pagination:true,
	    columns:[[    
	        {field:'id',title:'产品编号',width:50},    
	        {field:'categoryId',title:'分类',width:100}, 
	        {field:'name',title:'产品名称',width:100},
	        {field:'creatorUserId',title:'创建者',width:100},
	        {field:'costPrice',title:'成本价',width:100},
	        {field:'suggestedPrice',title:'建议售价',width:100},
	        {field:'developmentTeam',title:'开发团队',width:100},
	        {field:'developmentTime',title:'开发时间',width:100},
	        {field:'link',title:'链接',width:100},
	        {field:'createTime',title:'创建时间',width:100},
	        {field:'updateTime',title:'更新时间',width:100},
	        {field:'description',title:'产品描述',width:100},
	        {field:"caozuo",title:"操作",formatter: function(value,row,index){
					return "<a href='javascript:;' class='a' onclick='update("+row.id+")'>修改</a>&nbsp;"+
					"&nbsp;<a href='javascript:;' class='a' onclick='del("+row.id+")'>删除</a>";}						
					}
	    ]]    
	}); 
	
	
}) 

	//模糊查询
	$('#pronameSerach').click(function(){
		var name = $("#proname").val();
		alert(name);
		$('#dg').datagrid({    
			url:'nameSerachSelect/'+name,
		    pagination:true,
		    columns:[[    
		        {field:'id',title:'产品编号',width:50},    
		        {field:'categoryId',title:'分类',width:100}, 
		        {field:'name',title:'产品名称',width:100},
		        {field:'creatorUserId',title:'创建者',width:100},
		        {field:'costPrice',title:'成本价',width:100},
		        {field:'suggestedPrice',title:'建议售价',width:100},
		        {field:'developmentTeam',title:'开发团队',width:100},
		        {field:'developmentTime',title:'开发时间',width:100},
		        {field:'link',title:'链接',width:100},
		        {field:'createTime',title:'创建时间',width:100},
		        {field:'updateTime',title:'更新时间',width:100},
		        {field:'description',title:'产品描述',width:100},
		        {field:"caozuo",title:"操作",formatter: function(value,row,index){
						return "<a href='javascript:;' class='a' onclick='update("+row.id+")'>修改</a>&nbsp;"+
						"&nbsp;<a href='javascript:;' class='a' onclick='del("+row.id+")'>删除</a>";}						
						}
		    ]]    
		}); 
	});


	//添加产品
	function addproduct(){
		 parent.openTopWindow({
			 title:"添加页面",
			 width:500,
			 height:500,
			 url:"productAdd",//先经过一个servlet查询之后把结果添加到作用域当中，然后在请求转发到这个页面
			 close:function(){
				$("#dg").datagrid('reload');    
			 }
		 });
	}
	$("#addproduct").click(function(e){
		addproduct();
	})
	
	//添加分类
	function addproductCategory(){
		 parent.openTopWindow({
			 title:"添加页面",
			 width:500,
			 height:500,
			 url:"productCategoryAdd",//先经过一个servlet查询之后把结果添加到作用域当中，然后在请求转发到这个页面
			 close:function(){
				$("#dg").datagrid('reload');    
			 }
		 });
	}
	$("#addproductCategory").click(function(e){
		addproductCategory();
	})
	
	
	//修改操作
	function update (id){
		alert("修改");
		 parent.openTopWindow({
			 title:"修改页面",
			 width:500,
			 height:500,
			 url:"productUpdateList/"+id,//先经过一个servlet查询之后把结果添加到作用域当中，然后在请求转发到这个页面
			 close:function(){
				$("#dg").datagrid('reload');    
			 }
		 });
	};
	
	//删除
	
	function del(id){
		
		 $.messager.confirm('确认', '您确认想要删除记录吗？', function(r) {
			
			if (r) {
				$.get("productDelete/"+id, 
				function(data) {
					window.location.reload();//刷新页面
	
				}, "json");
			}
		});
	};
	
	
</script>
</html>