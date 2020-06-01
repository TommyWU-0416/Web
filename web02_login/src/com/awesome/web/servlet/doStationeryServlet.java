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
public class doStationeryServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Stationery stationery = new Stationery();
		String sid = request.getParameter("id");
		System.err.println(sid);
		Integer id = Integer.parseInt(sid);
		StationeryService service = new StationeryServiceImpl();
		stationery.setStationeryId(id);
		Stationery stationerys = service.getStationery(stationery);

		request.getSession().setAttribute("Stationery", stationerys);
		response.sendRedirect("cart.jsp");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
