package com.awesome.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesome.domain.Stationery;
import com.awesome.service.StationeryService;
import com.awesome.service.impl.StationeryServiceImpl;

/**
 * Servlet implementation class doStationery
 */
@WebServlet("/doStationeryServlet")
public class DoStationeryServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Stationery stationery = new Stationery();

		// 透過獲取商品id，可以知道購物了哪些
		String sid = request.getParameter("id");

		// 調用 service
		StationeryService service = new StationeryServiceImpl();
		stationery.setStationeryId(Integer.parseInt(sid));
		Stationery stationerys = service.getStationery(stationery);

		request.getSession().setAttribute("Stationery", stationerys);
		response.sendRedirect("cart.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
