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
	<thead>
		<tr>
			<th>负责人</th>
			<th>操作时间</th>
			<th>操作方式</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${businessLogList }" var="b">
		<tr>
			<td>${b.OWNERNAME }</td>
			<td>${b.C_TIME }</td>
			<td>${b.C_TYPE }</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>