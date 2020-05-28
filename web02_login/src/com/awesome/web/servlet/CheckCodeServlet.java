package com.awesome.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 驗證碼
 */
@WebServlet("/checkCode")
public class CheckCodeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		// 服務器通知瀏覽器不要緩存
		response.setHeader("pragma","no-cache");
		response.setHeader("cache-control","no-cache");
		response.setHeader("expires","0");
		
		// 在內存中創建一個長80，寬30的圖片，默認黑色背景
		// 參數一:長
		// 參數一:寬
		// 參數一:顏色
		int width = 80;
		int height = 30;
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		// 獲取畫筆
		Graphics g = image.getGraphics();
		// 設置畫筆顏色為灰色
		g.setColor(Color.GRAY);
		// 填充圖片
		g.fillRect(0,0, width,height);
		
		// 產生4個隨機驗證碼
		String checkCode = getCheckCode();
		// 將驗證碼放入HttpSession中
		request.getSession().setAttribute("CHECKCODE_SERVER",checkCode);
		
		// 設置畫筆顏色為黃色
		g.setColor(Color.YELLOW);
		// 設置字體大小
		g.setFont(new Font("黑体",Font.BOLD,24));
		// 向圖片上放入驗證碼
		g.drawString(checkCode,15,25);
		
		//将内存中的图片输出到浏览器
		//参数一：图片对象
		//参数二：图片的格式，如PNG,JPG,GIF
		//参数三：图片输出到哪里去
		ImageIO.write(image,"PNG",response.getOutputStream());
	}
	/**
	 * 產生4個隨機字符串 
	 */
	private String getCheckCode() {
		String base = "0123456789ABCDEFGabcdefg/*-";
		int size = base.length();
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i=1;i<=4;i++){
			// 產生0到size-1隨機值
			int index = r.nextInt(size);
			// 在base字符串中獲取下標為index的字符
			char c = base.charAt(index);
			// 將c放入到StringBuffer中
			sb.append(c);
		}
		return sb.toString();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request,response);
	}
}



