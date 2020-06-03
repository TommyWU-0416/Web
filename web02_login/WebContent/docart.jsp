<%@ page import="com.awesome.domain.CartItem"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.awesome.domain.Stationery"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的購物車的頁面</title>
</head>
<body>
	<%
		Stationery stationery = (Stationery) session.getAttribute("Stationery");
		if( stationery == null){
			request.getRequestDispatcher("notBuyYet.html").forward(request, response);
			return ;
			//response.sendRedirect("/notBuyYet.html");
		}
	%>
	
	<table width="100%" align="center" border="1px">
		<tr>
			<th>商品編號</th>
			<th>商品名稱</th>
			<th>商品單價</th>
			<th>商品數量</th>
			<th>商品小計</th>
		</tr>
		<%
			//1:將添加到購物車裏面的物品顯示出來
			Map<Integer, CartItem> map = (Map<Integer, CartItem>) session.getAttribute("cart");
			//2:將購物車裏面的內容遍歷出來
			double count = 0;//顯示出總價格
			for (Map.Entry<Integer, CartItem> entry : map.entrySet()) {
				//計算出每一樣的商品一共花了多少錢
				double price = entry.getValue().getStationery().getPrice() * entry.getValue().getNumber();
				//計算出一共花了多少錢
				count = count + price;
		%>
		<tr align="center">
			<td><%=entry.getKey()%></td>
			<td><%=entry.getValue().getStationery().getStationeryName()%></td>
			<td><%=entry.getValue().getStationery().getPrice()%></td>
			<td><%=entry.getValue().getNumber()%></td>
			<td><%=entry.getValue().getStationery().getPrice() * entry.getValue().getNumber()%></td>

		</tr>
		<%
			}
		%>
		<tr>
			<td colspan="4" align="right">價格總計</td>
			<td><%=count%></td>
		</tr>
	</table>
	<div style="text-align: center; font-size: 36px;">
		<a href="index.html">回首頁</a>
	</div> 
</body>
</html>