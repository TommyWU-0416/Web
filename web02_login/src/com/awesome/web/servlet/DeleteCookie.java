package com.awesome.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteCookie
 */
@WebServlet("/delcookie")
public class DeleteCookie extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie=new Cookie("lastAccessDate",System.currentTimeMillis()+"");
        cookie.setMaxAge(0);//删除
        cookie.setPath("/cookie");
        response.addCookie(cookie);
        response.sendRedirect(request.getContextPath()+"/cookie");//**重定向回到显示cookie的页面，可以看到页面上已经不显示上次访问的时间，但是！！！由于我们又访问了一次cookie页面，给服务器重新发送了一个请求，所以实际上又生成了新的cookie（加载页面后），刷新可看到**
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}