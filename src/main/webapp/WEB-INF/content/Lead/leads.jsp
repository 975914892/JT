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
<title>线索</title>
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
<input type="button" value="打开线索池" onclick="openPool()">
<div id="tb" name="搜索框">
<form action="" id="fieldForm">
	<select class="easyui-combobox" style="width:130px;" id="select1">
		<option value="0">--批量操作--</option>
		<option value="delete">批量删除</option>
		<option value="put">批量放入线索池</option>
	</select>
	<select class="easyui-combobox" style="width:130px;" id="select_id">
			<option style="font-color:gray" value="0">--选择筛选条件--</option>
		<c:forEach items="${leadsFields }" var="l">
			<option value="${l.fieldName }">${l.fieldValue }</option>
		</c:forEach>
	</select>
	<input type="text" value="包含" readonly="readonly"style="width:50px;">
	<input type="text" id="queryField" />
	<input type="button" value="搜索" id="mohu">
	<button class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="">搜索</button>
	
	<input type="button" value="添加" id="setBtn"  onclick="addLead()">
	
</form>
	<input type="button" value="发短信" id="aass"  onclick="send()">
	<input type="button" value="回收站" id="recycleBin"  onclick="leadRecycleBin()">
</div>
<table id="listLeads"></table>
</div>

</body>
<script type="text/javascript">
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
$(document).ready(function () {
	$("#select1").combobox({
		onChange: function (n,o) {
			if(n=="delete"){
				edit();
			}else if(n=="put"){
				put();
			}
		}
	});
});


</script>
<script type="text/javascript">
$(function(){
	$('#listLeads').datagrid({
	    url:"Lead/leads2.do",
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
	        {field:'ownerName',title:'负责人'},
	        {field:'creatorName',title:'创建人'},
	        {field:'haveTime',title:'创建时间'},
	        {field:'day',title:'距到期天数'},
	        {field:'cz',title:'操作',formatter:function(value,row,index){
	        	return  "&nbsp;<a href='jaavscript:;' onclick='list("+row.id+")'>查看</a>&nbsp;"+
	        			"&nbsp;<a href='jaavscript:;' onclick='convert("+row.id+")'>转换</a>&nbsp;"+
	        			"&nbsp;<a href='jaavscript:;' onclick='update("+row.id+")'>修改</a>&nbsp;";
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
	//修改按钮事件处理函数---批量删除
	function edit(){
		var leads = $('#listLeads').datagrid('getChecked');
		if(!leads.length){
			$.messager.alert("警告","请选择要修改的数据");
			return false;
		}
		$.messager.confirm('你可看好了!', '真的要删除这条线索吗？', function(r) {
			if (r) {
				var leadIds = '';
				for(var i = 0;i < leads.length;i++){
					leadIds += leads[i].id + ",";
				}
				var userId = '${userId}';
				$.post(
					'Lead/deleteLeads.do?method=delete',
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
		alert(checkValue+""+value);
		$("#listLeads").datagrid("reload","Lead/leads2.do?field="+ checkValue+"&value="+value);
	});
	  
	  //添加线索
	function addLead(){
		parent.openTopWindow({
			width : 1300,
			height : 750,
			title : "添加线索",
			url : "Lead/addLead.do",
			close : function() {//窗口关闭的回调函数
				$('#listLeads').datagrid('reload');//重新加载表格数据
			}
		}); 
	  }
	//查询详情
	function list(id){
		parent.openTopWindow({
			width : 1300,
			height : 750,
			title : "线索详情",
			url : "Lead/editLead.do?id="+id+"&method=list",
			close : function() {//窗口关闭的回调函数
				$('#listLeads').datagrid('reload');//重新加载表格数据
			}
		}); 
	}
	//修改线索
	function update(id){
		parent.openTopWindow({
			width : 1300,
			height : 750,
			title : "编辑线索",
			url : "Lead/editLead.do?id="+id+"&method=update",
			close : function() {//窗口关闭的回调函数
				$('#listLeads').datagrid('reload');//重新加载表格数据
			}
		}); 
	  }
	//转换为客户
	function convert(id){
		parent.openTopWindow({
			width : 1300,
			height : 750,
			title : "转化客户",
			url : "Lead/editLead.do?id="+id+"&method=convert",
			close : function() {//窗口关闭的回调函数
				$('#listLeads').datagrid('reload');//重新加载表格数据
			}
		}); 
	}
	function send(){
		parent.openTopWindow({
			width : 1300,
			height : 750,
			title : "转化客户",
			url : "Lead/s.do",
			close : function() {//窗口关闭的回调函数
				
			}
		});
	}
	//批量放入线索池
	function put(){
		var leads = $('#listLeads').datagrid('getChecked');
		if(!leads.length){
			$.messager.alert("警告","请选择要修改的数据");
			return false;
		}
		$.messager.confirm('你可看好了!', '真的要放入线索池吗？', function(r) {
			if (r) {
				var leadIds = '';
				for(var i = 0;i < leads.length;i++){
					leadIds += leads[i].id + ",";
				}
				$.post(
					'Lead/putLeads.do',
					{"leadIds":leadIds},
					function(data){
						alert(data.flag);
						$('#listLeads').datagrid('reload');
					},
					"json"
				);
			}
		});
	}
	
	//查看线索池
	function openPool(){
		$("#aaa").load("Lead/LP.do?method=open");
	}
	//查看回收站
	function leadRecycleBin(){
		$("#aaa").load("Lead/LP.do?method=leadRecycleBin");
	}
	  
	
	
//	jQuery序列化后的表单值转换成Json
	$.fn.serializeObject = function()
	{
	    var o = {};
	    var a = this.serializeArray();
	    $.each(a, function() {
	        if (o[this.name] !== undefined) {
	            if (!o[this.name].push) {
	                o[this.name] = [o[this.name]];
	            }
	            o[this.name].push(this.value || '');
	        } else {
	            o[this.name] = this.value || '';
	        }
	    });
	    return o;
	};

	
</script>
</html>