<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>登陸成功的頁面</h1>
<%
	int count = 0;
	if(this.getServletContext().getAttribute("count") != null){
		count = (int)this.getServletContext().getAttribute("count");
	}
%>




<h3>登錄成功總人數<%= count %></h3>
</body>
</html>