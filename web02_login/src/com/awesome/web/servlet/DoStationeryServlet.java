package com.awesome.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesome.domain.CartItem;
import com.awesome.domain.Stationery;
import com.awesome.service.StationeryService;
import com.awesome.service.impl.StationeryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class doStationery
 */
@WebServlet("/doStationeryServlet")
public class DoStationeryServlet extends HttpServlet {

	private StationeryService stationeryService = new StationeryServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 透過獲取商品id，可以知道購物了哪些
		int uid = Integer.parseInt(request.getParameter("uid"));

		// 調用 service
		List<Stationery> stationeryList = stationeryService.getStationeryList(uid);

		// 將 Stationery 儲存到 Attribute
		for (Stationery stationery : stationeryList) {
			System.out.println(stationery.toString());
		}
		request.getSession().setAttribute("stationery", stationeryList);
		// 儲存到 Map 裡面並且存到 cart，方便之後網頁生成
		Map<Integer, CartItem> cart = new HashMap<>();
		for (Stationery stationery : stationeryList) {
			// 新增CartItem
//			CartItem item = new CartItem();
//			item.setStationery(stationery);
//			item.setNumber(1);
//			// 放進map裡面，之後生成要用到
//			cart.put(stationery.getSid(), item);
			CartItem item = (CartItem) cart.get(stationery.getSid());
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
			cart.put(stationery.getSid(), item);
		}
		

		request.getSession().setAttribute("cart", cart);
		// 將 user寫回客戶端
//		Object obj = request.getSession().getAttribute("cart");
//        ObjectMapper mapper = new ObjectMapper();
//        response.setContentType("application/json; charset=utf-8");
//        mapper.writeValue(response.getOutputStream(), obj);
		response.sendRedirect("docart.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
