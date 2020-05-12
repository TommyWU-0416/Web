package com.awesome.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesome.domain.User;
import com.awesome.model.UserModel;

/**
 * 登陸用的Servlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.接收數據
			//  處理中文亂碼
			request.setCharacterEncoding("UTF-8");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			//2.封裝數據
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			
			//3.處理數據
			UserModel userModel = new UserModel();
			User existUser = userModel.login(user);
			
			//4.頁面跳轉
			if(existUser == null) {
				//fail login
				//向request域中保存錯誤訊息
				request.setAttribute("msg", "用戶名或密碼錯誤");
				//使用請求轉發
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else {
				//success login
				response.sendRedirect("/web02_login/success.jsp");
			}
		}catch(Exception e) {
			System.out.println("exception");
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
