package com.awesome.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesome.service.UserService;
import com.awesome.service.impl.UserServiceImpl;

@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.獲取激活代碼
        String code = request.getParameter("code");
        if(code != null){
            // 2.調用 service完成激活
            UserService service = new UserServiceImpl();
            boolean flag = service.active(code);

            // 3.判斷，之後要做記號
            String msg = null;
            if(flag){
                // 註冊成功
                msg = "註冊成功，請<a href='login.html'>登入</a>";
            }else{
                // 註冊失败
                msg = "註冊失敗，請聯繫管理員!";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
