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
<title>公告信息</title>
<link href="static/easyui/themes/insdep/easyui.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
<style type="text/css">
	#content{
		height: 100px;
		width: 300px;
		border:1px bule solid;
	}
	#description{
		height: 50px;
		width: 300px;
		border:1px bule solid;
	}
	input{
	border:1px solid gray;
	}
</style>
<body>

<form action="#" method="post" id="addFrom">

		合同编号:<input type="text" name="number" value="${number }" readonly="readonly"/><br/>
		签约时间:<input class="easyui-datetimebox" name="ducTime"     
        data-options="required:true,showSeconds:false" value="" style="width:150px">  
		<br/>
		
		
				来源商机:
				<select name="businessId" id="businessId" onchange="bao(this.options[this.options.selectedIndex].value)" >
					<option value="0" selected="selected">----请选择----</option>
					<c:forEach items="${listA }" var="list">
						<option value="${list.tcbsId }">${list.tcbsName }</option>
					</c:forEach>
				</select>
					来源客户：
					<select name="CustomerName"  id="CustomerName">
						<option value="0" selected="selected">----请选择----</option>
						<c:forEach items="${listA }" var="list">
							<option  value="${list.tcbsId  }">${list.tccrName }</option>
						</c:forEach>
					</select>
					联系人:
					<select name="contactsName" id="contactsName">
						<option value="0" selected="selected">----请选择----</option>
						<c:forEach items="${listA }" var="list">
							<option value="${list.tcbsId }">${list.tccsName }</option>
						</c:forEach>
					</select>
		<br/>
		负责人：<input type="text" name="ownerUserId" value="${sessionScope.user.username } "/><br/>
		合同金额：<input type="text" name="price" value=""/><br/>
		合同生效时间：<input class="easyui-datetimebox" name="startDate"     
        data-options="required:true,showSeconds:false" value="" style="width:150px">  
		<br/>
		合同到期时间：<input class="easyui-datetimebox" name="endDate"     
        data-options="required:true,showSeconds:false" value="" style="width:150px">  
		<br/>
		条件和条款：<br/>
		<textarea  name="content" id="content" >
		</textarea>
		<br/>
		合同描述(限150字以内)：<br/>
		<textarea  name="description" id="description" >
		</textarea>
		<br/>
		<input type="button" value="添加合同" id ="addSub" >
	</form>
</body>
<script type="text/javascript">
$(function(){
	
	$("#addSub").click(function(){
	
		$.post(
			"addContractAfter.do",
			$("#addFrom").serialize(),
			function(data){
				alert(data.message);
				parent.$("#topWindow").window("close");
			},
			"json"
		);
});
})
function bao(tcbsId){

	$.get(
		"onChangeBusinessId?tcbsId="+tcbsId,
		function(data){
			//1.
			$("#CustomerName").attr("value",data.cvo2.tccrName);//设置value=-sel3的项目为当前选中项 
			$("#CustomerName").empty();
			$("<option value='"+data.cvo2.tcbsId+"'>"+data.cvo2.tccrName+"</option>").appendTo("#CustomerName")//添加下拉框的option 
			//2.
			$("#contactsName").attr("value",data.cvo2.tccsName);//设置value=-sel3的项目为当前选中项 
			$("#contactsName").empty();
			$("<option value='"+data.cvo2.tcbsId+"'>"+data.cvo2.tccsName+"</option>").appendTo("#contactsName")//添加下拉框的option 
		},
		"json"
	)
}
</script>
</html>