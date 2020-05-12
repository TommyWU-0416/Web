<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>登錄頁面</h1>
	<form action="/web02_login/loginServlet" method="post">
		<table border="1" width="400">
			<tr>
				<td>用戶名</td>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>密碼</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="登陸" /></td>
			</tr>
		</table>
	</form>

</body>
</html>