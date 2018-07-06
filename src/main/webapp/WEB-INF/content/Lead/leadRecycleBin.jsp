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
<title>线索池</title>
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

	<button class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="re()">批量恢复</button>
<div id="tb" name="搜索框">
	<select class="easyui-combobox" style="width:130px;" id="select_id">
			<option style="font-color:gray" value="0">--选择筛选条件--</option>
			<option value="name">公司名</option>
			<option value="contacts_name">联系人姓名</option>
			<option value="saltname">尊称</option>
			<option value="mobile">手机</option>
			<option value="nextstep_time">下次联系时间</option>
			<option value="nextstep">下次联系内容</option>
			<option value="creatorName">创建人</option>
			<option value="create_time">创建时间</option>
	</select>
	<input type="text" value="包含" readonly="readonly"style="width:50px;">
	起始时间:<input  id="startTime"  type= "text" class= "easyui-datebox"> </input>
	终止时间:<input  id="endTime"  type= "text" class= "easyui-datebox"> </input>
	<input type="text" id="queryField" />
	<input type="button" value="搜索" id="mohu">
	<input type="button" value="返回线索" id="backLead"  onclick="back()">
	<input type="button" value="返回线索池" onclick="openPool()">
</div>
<table id="listLeads"></table>

</div>
</body>
<script type="text/javascript">
//返回--线索的页面
function back(){
	$("#aaa").load("Lead/leads.do");
}
//返回线索池
function openPool(){
	$("#aaa").load("Lead/LP.do?method=open");
}

</script>
<script type="text/javascript">
$(function(){
	$('#listLeads').datagrid({
	    url:"Lead/leadRecycleBin.do",
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
	        {field:'name',title:'公司名称'},
	        {field:'contactsName',title:'联系人姓名'},
	        {field:'saltname',title:'尊称'},
	        {field:'mobile',title:'手机号'},
	        {field:'nextstepTime',title:'下次联系时间'},
	        {field:'nextstep',title:'下次联系内容'},
	        {field:'creatorName',title:'创建人'},
	        {field:'haveTime',title:'创建时间'},
	        {field:'cz',title:'操作',formatter:function(value,row,index){
	        	return  "&nbsp;<a href='jaavscript:;' onclick='del("+row.id+")'>彻底删除</a>&nbsp;";
	        }}
	    ]]
	});
});


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
//修改按钮事件处理函数---批量恢复
function re(){
	var leads = $('#listLeads').datagrid('getChecked');
	if(!leads.length){
		$.messager.alert("警告","请选择要恢复的数据");
		return false;
	}
	$.messager.confirm('你可看好了!', '真的要恢复选中的线索吗？', function(r) {
		if (r) {
			var leadIds = '';
			for(var i = 0;i < leads.length;i++){
				leadIds += leads[i].id + ",";
			}
			var userId = '${userId}';
			$.post(
				'Lead/deleteLeads.do?method=re',
				{"leadIds":leadIds,"userId":userId},
				function(data){
					alert(data.flag);
					$('#listLeads').datagrid('reload');
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
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	alert(checkValue+""+value);
	$("#listLeads").datagrid("reload","Lead/leadRecycleBin.do?field="+ checkValue+"&value="+value+"&startTime="+startTime+"&endTime="+endTime);
});

function del(id){
	$.messager.confirm('你可看好了!', '真的要删除这条线索吗？', function(r) {
		if (r) {
			$.post(
					'Lead/actuallyDeleteLeadOne.do',
					{"id":id},
					function(data){
						alert(data.flag);
						$('#listLeads').datagrid('reload');
					},
					"json"
				);
			}
		});
}

</script>
</html>