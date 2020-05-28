package com.awesome.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesome.domain.ResultInfo;
import com.awesome.domain.User;
import com.awesome.service.UserService;
import com.awesome.service.impl.UserServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1.獲取用戶名和密碼
        Map<String, String[]> map = request.getParameterMap();

        // 2.封裝對象
        User user = new User();
        try {
            // org.apache.commons.beanutils.BeanUtils
            // BeanUtils.populate(Object bean, Map properties);
            // 會把 Map裡面的 key對應的 value給予 bean的屬性
            // 也就是把 map的值，存給 user,封裝
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 3.調用 service檢查是否存在
        UserService service = new UserServiceImpl();
        User u = service.login(user);

        ResultInfo info = new ResultInfo();

        // 判斷是否為空，如果註冊失敗就會回空，登入就會有個空值
        if (u == null) {
            info.setFlag(false);
            info.setErrorMsg("用戶名或密碼錯誤");
        }
        // 判斷是否激活
        if (u != null && ("Y".equals(user.getStatus()))) {
            info.setFlag(false);
            info.setErrorMsg("激活失敗，請至信箱確認激活信件");
        }

        // 判斷登入成功
        if( u != null && ("Y".equals(u.getStatus()))){
            // 將數值存到 Session裡面，之後可以使用
            request.getSession().setAttribute("user", u);

            info.setFlag(true);
        }

        // 響應數據
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json; charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
