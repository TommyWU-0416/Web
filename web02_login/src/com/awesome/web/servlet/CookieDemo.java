package com.awesome.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieDemo
 */
@WebServlet("/cookie")
public class CookieDemo extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		
		out.print("<a href='delcookie'>清除上次访问时间</a><br/>");// 输出跳转到删除cookie的servlet按钮
		out.print("您上次访问的时间是：");

		// 获取cookie
		Cookie cookies[] = request.getCookies();
		int count = 0;
		for (count = 0; cookies != null && count < cookies.length; count++) {
			if (cookies[count].getName().equals("lastAccessDate")) {
				long longtime = Long.parseLong(cookies[count].getValue());
				Date date = new Date(longtime);
				out.print(date.toLocaleString());
			}
		}
		// 设置最新的时间cookie
		Cookie cookie= new Cookie("lastAccessDate", System.currentTimeMillis() + "");
		cookie.setMaxAge(30 * 24 * 3600);// 过期时间
		response.addCookie(cookie);
		
		cookie.setPath("/cookie");// 作用于当前页面

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}