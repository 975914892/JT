<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>沟通日志</title>
</head>
<body>
<table>
	<thead>
		<tr>
			<th>上次联系时间</th>
			<th>联系内容</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${leadLogList }" var="l">
		<tr>
			<td>${l.stepTime }</td>
			<td>${l.step }</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

</body>
</html>