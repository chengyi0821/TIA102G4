package com.tia102g4.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailUtil {

	public static void sendEmail(String to, String subject, String content) {
		String from = "miffyai0716@gmail.com"; // 發件人電子郵件
		String host = "smtp.gmail.com"; // SMTP 伺服器

		// 設置系統屬性
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host); // 設置 SMTP 伺服器的主機名
		properties.setProperty("mail.smtp.port", "587"); // 設置 SMTP 伺服器的端口號，Gmail 使用端口 587
		properties.setProperty("mail.smtp.auth", "true"); // 設置 SMTP 認證為 true，表示需要用戶名和密碼來驗證身份
		properties.setProperty("mail.smtp.starttls.enable", "true"); // 設置為 true，啟用 TLS（傳輸層安全性）來加密通信

		// 獲取默認 session 對象
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("miffyai0716@gmail.com", "rfifwpgpkekhqoqq");
			}
		});

		try {
			// 創建默認的 MimeMessage 對象
			MimeMessage message = new MimeMessage(session);

			// 設置發件人
			message.setFrom(new InternetAddress(from));

			// 設置收件人
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// 設置主題
			message.setSubject(subject);

			// 設置正文為 HTML 內容
			message.setContent(content, "text/html; charset=utf-8");

			// 發送消息
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}