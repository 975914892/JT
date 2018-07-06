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
<%@include file="script.html" %>

</head>
  
  <body >
  	<form>
	   	<table id="roleList" class="easyui-datagrid"></table>
	    <div id="toolbar"> 
	          <input type="button" value="确定" id="setBtn" class="easyui-linkbutton" data-options="iconCls:'icon-reload' " onclick="edit()">  
	    </div>
    </form>
    <script type="text/javascript">
    	//加载用户数据
    	$(function(){
    		$("#roleList").datagrid({
    			url : "roleFindByPage?userId=${userId}",
    			pagination : true,
    			toolbar : "#toolbar",
    			fitColumns : true,
    			idField : "roleId",
    			rownumbers : true,
    			//singleSelect:true,
    			columns : [[
    				{field:'flag',formatter:function(value,row,index){
	    					if(row.flag){
	    						return '<input type="checkbox" name="flag" value="'+row.roleId+'" checked="checked">';
	    					}
	    					else{
	    						return '<input type="checkbox" value="'+row.roleId+'"name="flag">';
	    					}
    					}
    				},
    				{field:"roleId",title:"编号",checkbox:false}, 				
    				{field:"roleName",title:"角色名称",sortable:true},
    				{field:"note",title:"备注"},
    				{field:"system",title:"系统标识"},
    				{field:"createTime",title:"创建时间"},
    				{field:"updateTime",title:"更新时间"},
   	
    			]],
    			loadFilter:function(data){
    				//data是服务器返回的数据,将服务器端返回的数据转换为datagrid需要的格式
    				return {"total":data.totalRows,"rows":data.result};
    			}
    		
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
    	//修改按钮事件处理函数
    	function edit(){
    		var rows = $("#roleList").datagrid("getChecked");
    		if(!rows.length){
    			$.messager.alert("警告","请选择要修改的数据");
    			return false;
    		}
    		var roleIds = '';
    		for(var i = 0;i < rows.length;i++){
    			roleIds += rows[i].roleId + ",";
    		}
    		var userId = '${userId}';
    		$.post(
    			'editUserRole',
    			{"roleIds":roleIds,"userId":userId},
    			function(data){
    				parent.$("#topWindow").window('close', true);
    			},
    			"json"
    		);
    		
    	}
  
    </script>
  </body>
</html>
