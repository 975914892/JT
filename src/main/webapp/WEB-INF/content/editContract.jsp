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

<form action="#" method="post" id="editFrom">

			  <input type="hidden" name="id"  value="${tcc.id }" /><br/>
			  
		合同编号:<input type="text" name="number" value="${tcc.number }" readonly="readonly"/><br/>
			  <input type="hidden" name="businessId"  value="${tcc.businessId }" /><br/>
		签约时间:<input class="easyui-datetimebox" name="ducTime"     
        data-options="required:true,showSeconds:false" value="${tcc.ducTime }" style="width:150px">  
		<br/>
		 
		负责人：<input type="text" name="username" value="${sessionScope.user.username } " readonly="readonly"/><br/>
		合同金额：<input type="text" name="price" value="${tcc.price }"/><br/>
		合同创建时间：<input class="easyui-datetimebox" name="createTime" readonly="readonly"    
        data-options="required:true,showSeconds:false" value="${tcc.createTime }" style="width:150px">  
		<br/>
		合同生效时间：<input class="easyui-datetimebox" name="startDate"     
        data-options="required:true,showSeconds:false" value="${tcc.startDate }" style="width:150px">  
		<br/>
		合同到期时间：<input class="easyui-datetimebox" name="endDate"     
        data-options="required:true,showSeconds:false" value="${tcc.endDate }" style="width:150px">  
		<br/>
		条件和条款：<br/>
		<textarea  name="content" id="content" >${tcc.content}
		</textarea>
		<br/>
		合同描述(限150字以内)：<br/>
		<textarea  name="description" id="description" >${tcc.description}
		</textarea>
		<br/>
		<input type="hidden" name="status"  value="${tcc.status }" /><br/>
		<input type="button" value="修改合同" id ="editSub" >
	</form>
</body>
<script type="text/javascript">
$(function(){
	alert("")
 	$("#editSub").click(function(){
		alert("edit");
		$.post(
			"editContractAfter.do",
			$("#editFrom").serialize(),
			function(data){
				alert(data.message);
				parent.$("#topWindow").window("close");
			},
			"json"
		);
}); 
})
</script>
</html>