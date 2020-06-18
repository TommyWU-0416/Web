package com.awesome.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.awesome.domain.ResultInfo;
import com.awesome.domain.User;
import com.awesome.service.UserService;
import com.awesome.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/registUserServlet")
public class RegistUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 校驗驗證碼
        String check = request.getParameter("check");
        // 從session中獲取驗證碼
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//保證驗證碼只能使用一次
        // 比較
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            // 驗證碼錯誤
            ResultInfo info = new ResultInfo();
            // 註冊失敗
            info.setFlag(false);
            info.setErrorMsg("驗證碼錯誤");
            //将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
    	
        // 1.獲取數據
        Map<String, String[]> map = request.getParameterMap();
        
        // 2.封裝對象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
            
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        
        // 3.調用service完成註冊
        UserService service = new UserServiceImpl();
        String path = request.getContextPath();
        boolean flag = service.regist(user, path);
        ResultInfo info = new ResultInfo();
        // 4.響應結果
        if(flag){
        	// 註冊成功
            info.setFlag(true);
            System.out.println("註冊成功");
            
        }else{
        	// 註冊失敗
            info.setFlag(false);
            info.setErrorMsg("註冊失敗!");
            
        }

        // 將info對象序列話為json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        // 將json數據寫回客戶端
        // 設置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
