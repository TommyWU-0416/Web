<%@page import="java.util.HashMap"%>
<%@page import="com.awesome.domain.CartItem"%>
<%@page import="java.util.Map"%>
<%@page import="com.awesome.domain.Stationery"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加到購物車</title>
</head>
<body>
	<%
		//購物車功能
		//1:首先考慮我購買的是哪一本商品，這裏可以使用id確認也可以使用session中取出哪一本商品
		Stationery stationery = (Stationery) session.getAttribute("Stationery");
		
		//2:考慮如何把商品放到購物車中
		//2.1:首先考慮是否有購物車，如果沒有，則創建，如果有直接使用
		//2.2:其次先將購物車從session中拿出來，不存在就創建。
		Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
		//如果沒有購物車，那麽創建，只有第一次訪問才會操作
		if (cart == null) {
			//new一個購物車
			cart = new HashMap<>();
		}

		//3:考慮如何把商品放到購物車中
		//3.1:第一考慮購物車中是否有該商品，所以先從購物車中獲取該商品，如果為空，那麽沒有該商品
		CartItem item = (CartItem) cart.get(stationery.getStationeryId());
		if (item == null) {
			//如果購物車中不存在該商品，那麽創建，且數量默認為1
			item = new CartItem();
			//將商品放到購物車中
			item.setStationery(stationery);
			//將商品的默認數量為1
			item.setNumber(1);
		} else {
			//如果購物車中以及有該商品，那麽數量加1 
			item.setNumber(item.getNumber() + 1);
		}

		//4:考慮如何把購物車項(即挑選的商品是哪一個和商品的數量)放到購物車中
		cart.put(stationery.getStationeryId(), item);

		//5:將購物車放到session中，方便後面取出來
		session.setAttribute("cart", cart);

		response.sendRedirect("index.html");
	%>
</body>
</html>