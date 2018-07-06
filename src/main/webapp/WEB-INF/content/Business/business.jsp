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
<title>Insert title here</title>
<link href="static/easyui/themes/insdep/easyui.css" rel="stylesheet" type="text/css">
<link href="static/easyui/themes/insdep/easyui_animation.css" rel="stylesheet" type="text/css">
<link href="static/easyui/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
<link href="static/easyui/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="static/css/style.css">

<script type="text/javascript" src="static/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="static/js/easyform.js"></script>
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/themes/insdep/jquery.insdep-extend.min.js"></script>
<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>

</head>
<body>
<div id="aaa">
<div id="tb">
	<button class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="del()">批量删除</button>
	<select class="easyui-combobox" style="width:130px;" id="select_id">
			<option style="font-color:gray" value="0">--选择筛选条件--</option>
			<option value="customerName">客户</option>
			<option value="name">商机名</option>
			<option value="origin">商机来源</option>
			<option value="nextstep">下次联系内容</option>
			<option value="ownerName">负责人</option>
			<option value="creatorName">创建人</option>
	</select>
	<input type="text" value="包含" readonly="readonly"style="width:50px;">
	<input type="text" id="queryField" />
	<input type="button" value="搜索" id="mohu" onclick="mohu()">
	<input type="button" value="新建商机"  onclick="addBusiness()">
</div>
	<input type="button" value="回收站" id="recycleBin"  onclick="businessRecycleBin()">

<table id="listBusiness"></table>
</div>
</body>
<script type="text/javascript">
function mohu(){
	var checkValue=$("#select_id").val();  //获取Select选择的Value 
	var value=$("#queryField").val();  //获取input的值
	$("#listBusiness").datagrid("reload","Business/leadBusiness.do?field="+ checkValue+"&value="+value);
}
/**
 *打开在父窗口中打开window
 */
function openTopWindow(options){
options = !options ? {} :options;
options.width = !options.width ? 500: options.width;
options.height = !options.height ? 400 : options.height;
options.title = !options.title ? "" : options.title;
parent.$("#iframe").attr("src",options.url);
parent.$("#topWindow").window({
    title : options.title,
    width: options.width,
    height: options.height,
    modal:true,
    resizable:true,
    collapsible:false,
});

}
</script>
<script type="text/javascript">
$(function(){
	$('#listBusiness').datagrid({
	    url:"Business/leadBusiness.do",
	    title:"线索",
	    striped:true,	//斑马线
	    pagination:true,	//分页工具栏
	    toolbar:"#tb",	//将搜索框作为工具条
	    method:"post",
	    emptyMsg:"未找到记录",
	    loadMsg:"加呀嘛加载中~~",
	    collapsible: true,	//可折叠
	    rownumbers:true,	//显示行号
	    columns:[[
	    	{field:'box',formatter:function(value,row,index){
				return '<input type="checkbox" value="'+row.id+'"name="flag">';
				}
			},
			{field:'id',title:'ID',checkbox:false},
	        {field:'customerName',title:'客户'},
	        {field:'name',title:'商机名'},
	        {field:'origin',title:'商机来源'},
	        {field:'nextstepTime',title:'下次联系时间'},
	        {field:'nextstep',title:'下次联系内容'},
	        {field:'ownerName',title:'负责人'},
	        {field:'creatorName',title:'创建人'},
	        {field:'createTime',title:'创建时间'},
	        {field:'updateTime',title:'更新时间'},
	        {field:'cz',title:'操作',formatter:function(value,row,index){
	        	return  "&nbsp;<a href='jaavscript:;' onclick='list("+row.id+")'>查看</a>&nbsp;"+
	        			"&nbsp;<a href='jaavscript:;' onclick='to("+row.id+")'>推进</a>&nbsp;"+
	        			"&nbsp;<a href='jaavscript:;' onclick='update("+row.id+")'>编辑</a>&nbsp;";
	        }}
	    ]]
	});
});

//添加商机
function addBusiness(){
	parent.openTopWindow({
		width : 1300,
		height : 750,
		title : "添加商机",
		url : "Business/addB.do",
		close : function() {//窗口关闭的回调函数
			$('#listBusiness').datagrid('reload');//重新加载表格数据
		}
	}); 
}

//扩展getChecked方法
$.extend($.fn.datagrid.methods, {
	getChecked: function (jq) {
		var rr = [];
		var rows = jq.datagrid('getRows');
		jq.datagrid('getPanel').find('div.datagrid-cell input:checked').each(function () {
    		var index = $(this).parents('tr:first').attr('datagrid-row-index');
    		rr.push(rows[index]);
		});
		return rr;
	}
});

//修改按钮事件处理函数---批量删除
	function del(){
		var leads = $('#listBusiness').datagrid('getChecked');
		if(!leads.length){
			$.messager.alert("警告","请选择要删除的商机");
			return false;
		}
		$.messager.confirm('你可看好了!', '真的要删除选中的商机吗？', function(r) {
			if (r) {
				var leadIds = '';
				for(var i = 0;i < leads.length;i++){
					leadIds += leads[i].id + ",";
				}
				var userId = '${userId}';
				$.post(
					'Business/deleteBusiness.do?method=delete',
					{"businessIds":leadIds,"userId":userId},
					function(data){
						alert(data.flag);
						$('#listBusiness').datagrid('reload');
					},
					"json"
				);
			}
		});
	}
	
	  //模糊查询
	$("#mohu").click(function(){
		var checkValue=$("#select_id").val();  //获取Select选择的Value 
		var value=$("#queryField").val();  //获取input的值
		alert(checkValue+""+value);
		$("#listBusiness").datagrid("reload","Business/leadBusiness.do?field="+ checkValue+"&value="+value);
	});
	  
	//查询详情
	function list(id){
		parent.openTopWindow({
			width : 1300,
			height : 750,
			title : "商机详情",
			url : "Business/editBusiness.do?id="+id+"&method=list",
			close : function() {//窗口关闭的回调函数
				$('#listBusiness').datagrid('reload');//重新加载表格数据
			}
		}); 
	}
	//修改商机
	function update(id){
		parent.openTopWindow({
			width : 1300,
			height : 750,
			title : "编辑商机",
			url : "Business/editBusiness.do?id="+id+"&method=update",
			close : function() {//窗口关闭的回调函数
				$('#listBusiness').datagrid('reload');//重新加载表格数据
			}
		}); 
	  }
	//查看回收站
	function businessRecycleBin(){
		$("#aaa").load("Business/BP.do?method=businessRecycleBin");
	}
	//推进商机
	function to(id){
		parent.openTopWindow({
			width : 1000,
			height : 650,
			title : "编辑商机",
			url : "Business/putB.do?id="+id+"&method=tuijin",
			close : function() {//窗口关闭的回调函数
				$('#listBusiness').datagrid('reload');//重新加载表格数据
			}
		}); 
	}
	
	
	
	
</script>

</html>