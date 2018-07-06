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
<link rel="stylesheet" type="text/css" href="static/css/component.css" />

<script type="text/javascript" src="static/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="static/js/easyform.js"></script>
<script type="text/javascript" src="static/js/PCASClass.js"></script>
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/themes/insdep/jquery.insdep-extend.min.js"></script>
<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>

<style type="text/css">
a:hover {
    color:blue;
     text-decoration:none;
    }
input[type="text"]{
float: left;
}

</style>
</head>
<body>
<h2>${msg}</h2>
<hr style="border: 1px solid gray"/>
<p></p>
<form action="" id="updateBusiness">
	
	<h4>主要信息</h4>
	<table>
				<tr>
					<th>负责人:</th>
					<td><label><input type="text" name="ownerName" value="${BVO.ownerName}" required></label>
					<th>创建时间:</th>
					<td><label><input name="createTime" id="createTime" value="${business.createTime}" type="text" class= "easyui-datebox" style="float: left;" ></label></td>
				</tr>
				<tr>
					<th>上次推进时间:</th>
					<td><label><input name="updateTime" id="updateTime" value="${business.updateTime}" type="text" class= "easyui-datebox" style="float: left;" ></label>
					<th>客户:</th>
					<td><label><input type="text" name="customerName" value="${BVO.customerName}" required></label></td>
				</tr>
				<tr>
					<th>商机余额:</th>
					<td><label><input type="text" name="totalPrice" value="${business.totalPrice}"></label></td>
					<th>商机名:</th>
					<td><label><input type="text" name="name" value="${business.name}" required></label></td>
				</tr>
				<tr>
					<th>联系人:</th>
					<td><label><input type="text" name="contactsName" value="${BVO.contactsName}" required></label></td>
					<th>合同签订地址:</th>
					<td><label><input type="text" name="contractAddress" value="${business.contractAddress}"></label>
					</td>
				</tr>
				<tr>
					<th>商机类型:</th>
					<td><label><input type="text" name="type" value="${business.type}"></label></td>
					<th>状态:</th>
					<td><label>
							<select name="statusId" id="statusId" class="easyui-combobox" style="width:160px;" >
								<option value="1">意向客户</option>
								<option value="2">初步沟通</option>
								<option value="3">深度沟通</option>
								<option value="4">签订合同</option>
								<option value="5">设计制作</option>
								<option value="6">制作完成</option>
								<option value="7">项目成功</option>
								<option value="8">项目失败</option>
							</select>
						</label></td>
				</tr>
				<tr>
					<th>商机来源:</th>
					<td><label><input type="text" name="origin" value="${business.origin}"></label></td>
					<th>赢单率:</th>
					<td><label><input type="text" name="gainRate" value="${business.gainRate}">(注%)</label></td>
				</tr>
				<tr>
					<th>预计成交价:</th>
					<td><label><input type="text" name="estimatePrice" value="${business.estimatePrice}">(单位:元)</label></td>
					<th>下次联系时间:</th>
					<td><label><input name="nextstepTime" id="nextstepTime" value="${business.nextstepTime}" type="text" class= "easyui-datebox" style="float: left;" ></label></td>
				</tr>
				<tr>
					<th>下次联系内容:</th>
					<td><label><input type="text" name="nextstep" value="${business.nextstep}"></label>
				</tr>
			</table>
				<table>
					<tr>
						<th>备注</th>
						<td><input type="text" name="descrption" id="descrption" value="${business.descrption}" style="width:500px;height:90px" ></td>
					</tr>
				</table>
	<c:if test="${msg  eq '修改商机'}">
		<input type="button" value="保存" style="width:50px;" onclick="update()">
	</c:if>
	<button class="easyui-linkbutton"style="width:80px;"  onclick="c()">返回</button>
</form>

</body>
<script language="javascript" defer>
new PCAS("province","city","area");

$(function(){
	 $('#statusId').combobox('select','${business.statusId}');
});
</script>
<script type="text/javascript">
function c(){
	parent.$("#topWindow").window('close', true);
}

function update(){
	var id = ${id}
	$.post(
			"Business/updateBusiness.do?id="+id,
			$("#updateBusiness").serialize(),
			function(data){	//(成功后的回调函数)
			alert(data.aa);
			parent.$("#topWindow").window('close', true);
			},
			"json"	//（服务器响应的信息，最后一条不加","）
		);
}
//放入线索池
function put(){
	document.getElementById('ownerName').value="";
	alert("已放入线索池,请保存");
}
//什么是线索池
function what(){
	alert("线索池是存放无人领取或无人负责的线索的集合。如果新建的线索没有线索负责人，或者工作人员没有在规定时间内将领取的线索进行转换，线索将自动回收入线索池，以防止个人占有大量线索而其他人无法领取线索等情况的发生。而那些已经没有线索的员工，可以从线索池中领取线索。对于员工来说，线索转化为客户的数量越多，说明该员工的工作效果越高。对企业来说，从线索到客户的转换，意味着客户将为企业带来更多利益。");
}
</script>

</html>