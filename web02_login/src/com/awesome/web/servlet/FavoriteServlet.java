package com.awesome.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesome.domain.Stationery;
import com.awesome.domain.User;
import com.awesome.service.FavoriteService;
import com.awesome.service.StationeryService;
import com.awesome.service.impl.FavoriteServiceImpl;
import com.awesome.service.impl.StationeryServiceImpl;

/**
 * Servlet implementation class FavoriteServlet
 */
@WebServlet("/favoriteServlet")
public class FavoriteServlet extends HttpServlet {

	private FavoriteService favoriteService = new FavoriteServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.獲取 rid
		String sid = request.getParameter("sid");

		// 2.判斷是否登入
		User user = (User) request.getSession().getAttribute("user");
		int uid;
		if (user == null) {
			// 用戶尚未登入
			return; // 給 uid 可能會添加到數據庫，所以直接返回就根本不會加入數據庫
		} else {
			// 用戶登入
			uid = user.getUid();
		}
		// 3.調用 service
		// 添加到數據庫
		favoriteService.addFavorite(sid, uid);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
