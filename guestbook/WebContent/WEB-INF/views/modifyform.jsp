<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath }/gb">
	<input type="hidden" name="no" value="${param.no }">
	<input type="hidden" name="a" value="modify">
	<table>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="password"></td>
			
			<td><a href="${pageContext.request.contextPath }/gb">메인으로 돌아가기</a></td>
		</tr>
	</table>
	<table border=1 width=500>
		<tr>
			<td>이름</td><td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td colspan=4><textarea name="content" cols=60 rows=5></textarea></td>
		</tr>
		<tr>
			<td colspan=4 align=right><input type="submit" VALUE="수정 " ></td>
			
		</tr>
	</table>
	</form>
</body>
</html>