package com.awesome.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 發mail工具類
 */
public final class MailUtils {
    //String host = "smtp.gmail.com";
    //int port = 587;
    private static final String USER = "thirdshopcp123@gmail.com"; 	// 發信人帳號
    private static final String PASSWORD = "ConnectionP/123"; 		// 發信人密碼

    /**
     * @param to    收件人帳號
     * @param text  正文
     * @param title 標題
     */
    /* 發送驗證信息的郵件 */
    public static boolean sendMail(String to, String text, String title) {
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", 587);

            // 發件人的帳號
            props.put("mail.user", USER);
            // 發件人的密碼
            props.put("mail.password", PASSWORD);

            // 構造授權信息，用於進行SMTP進行身分驗證
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用戶名，密碼
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用環境屬性和授權信息，創建郵件會話
            Session mailSession = Session.getInstance(props, authenticator);
            // 創建郵件信息
            MimeMessage message = new MimeMessage(mailSession);
            // 設置發件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            // 設置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            // 設置郵件標題
            message.setSubject(title);

            // 設置郵件內容
            message.setContent(text, "text/html;charset=UTF-8");
            // 發送郵件
            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws Exception { // 測試郵件
        MailUtils.sendMail("tommy111811@gmail.com", "您好，這是測試用，無須回復。", "測試郵件");
        System.out.println("發送成功发送成功");
    }

}
