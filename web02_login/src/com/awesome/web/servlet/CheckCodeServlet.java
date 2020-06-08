package com.awesome.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 驗證碼
 */
@WebServlet("/checkCode")
public class CheckCodeServlet extends HttpServlet {
	private static int codeCount = 4;
	private static StringBuilder sb = new StringBuilder();

	private static String chs = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static int[] fontSize = { 16, 18, 20, 22, 24};
	private static int[] fontStyle = { Font.BOLD, Font.ITALIC, Font.ROMAN_BASELINE };
	private static String[] fontName = { "幼圓", "微軟雅黑", "新宋體", "方正姚體", "方正舒體", "楷體", "隸書", "黑體" };

	private static Random random = new Random();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 服務器通知瀏覽器不要緩存
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("expires", "0");

		// 在內存中創建一個長80，寬30的圖片，默認黑色背景
		// 參數一:長
		// 參數一:寬
		// 參數一:顏色
		int width = 80;
		int height = 30;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 獲取畫筆
		Graphics g = image.getGraphics();
		// 設置畫筆顏色為灰色
		g.setColor(Color.WHITE);
		// 填充圖片
		g.fillRect(1, 1, 78, 28);

//		// 產生4個隨機驗證碼
//		String checkCode = getCheckCode();
//		// 將驗證碼放入HttpSession中
//		request.getSession().setAttribute("CHECKCODE_SERVER", checkCode);
//
//		// 設置畫筆顏色為黃色
//		g.setColor(Color.YELLOW);
//		// 設置字體大小
//		g.setFont(new Font("黑体", Font.BOLD, 24));
//
//		g.setColor(Color.BLACK);
//		// 向圖片上放入驗證碼
//		g.drawString(checkCode, 15, 25);

		// 獲得隨機產生的數字以及字型
		randomNum(g);
		
		// 將驗證碼放入HttpSession中
		request.getSession().setAttribute("CHECKCODE_SERVER", sb.toString());
		// 設置干擾線
		randomLine(g);
		sb.delete(0, 4);
		// 將內存中的圖片輸出到瀏覽器
		// 參數一:圖片對象
		// 參數二:圖片的格式，如PNG, JPG, GIF
		// 參數三:圖片輸出到哪裡去
		ImageIO.write(image, "PNG", response.getOutputStream());
	}

	/**
	 * 隨機顏色
	 * 
	 * @return
	 */
	private static Color randomColor() {
		return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
	}

	/**
	 * 隨機產生干擾線
	 * 
	 * @param g
	 */
	private void randomLine(Graphics g) {
		for (int i = 0; i < 10; i++) {
			g.setColor(randomColor());
			g.drawLine(random.nextInt(81) + 5, random.nextInt(31) + 5, random.nextInt(81) + 5,
					random.nextInt(31) + 5);
		}
	}

	/**
	 * 產生數字
	 * 
	 * @param g
	 */
	private static void randomNum(Graphics g) {
		String numStr = null;
		for (int i = 0; i < codeCount; i++) {
			numStr = randomChar();
			sb.append(numStr);
			g.setColor(randomColor());
			g.setFont(randomFont());
			g.drawString(numStr, 10+i*17, 20);
		}
	}

	/**
	 * 隨機字型
	 * 
	 * @return
	 */
	private static Font randomFont() {
		int size = fontSize[random.nextInt(fontSize.length)];
		int style = fontStyle[random.nextInt(fontStyle.length)];
		String font = fontName[random.nextInt(fontName.length)];
		Font f = new Font(font, style, size);
		return f;
	}

	/**
	 * 隨機數
	 * 
	 * @return
	 */
	private static String randomChar() {
		String str = chs.charAt(random.nextInt(chs.length()))+"";
		System.err.println(str);
		return str;
	}

	/**
	 * 產生4個隨機字符串
	 */
	private String getCheckCode() {
		String base = "0123456789ABCDEFGabcdefg/*-";
		int size = base.length();
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= 4; i++) {
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
		this.doGet(request, response);
	}
}
