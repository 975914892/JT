<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商机日志</title>
</head>
<body>
<table>
	<tr>
		<th>合同编号:</th>
		<td><label>${Contract.number }</label></td>
		<th>商机名:</th>
		<td><label>${Contract.BUSINESSNAME }</label></td>
	</tr>
	<tr>
		<th>总价</th>
		<td><label>${Contract.PRICE }</label></td>
		<th>签约日期</th>
		<td><label>${Contract.DUC_TIME }</label></td>
	</tr>
	<tr>
		<th>负责人</th>
		<td><label>${Contract.OWNERNAME }</label></td>
		<th>创建人</th>
		<td><label>${Contract.CREATENAME }</label></td>
	</tr>
	<tr>
		<th>内容</th>
		<td><label>${Contract.CONTENT }</label></td>
		<th>描述</th>
		<td><label>${Contract.DESCRIPTION }</label></td>
	</tr>
	<tr>
		<th>创建时间</th>
		<td><label>${Contract.CREATE_TIME }</label></td>
		<th>更新时间</th>
		<td><label>${Contract.UPDATE_TIME }</label></td>
	</tr>
	<tr>
		<th>起始时间</th>
		<td><label>${Contract.START_DATE }</label></td>
		<th>结束时间</th>
		<td><label>${Contract.END_DATE }</label></td>
	</tr>
	<tr>
		<th>状态</th>
		<td><label>${Contract.STATUS }</label></td>
	</tr>
</table>
</body>
</html>