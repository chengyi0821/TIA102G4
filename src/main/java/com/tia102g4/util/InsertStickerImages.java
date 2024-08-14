package com.tia102g4.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertStickerImages {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/chugether";
		String user = "root";
		String password = "123456";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 載入JDBC驅動程式
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 建立連線
			conn = DriverManager.getConnection(url, user, password);
			
			// 關閉自動提交，啟用手動提交
			conn.setAutoCommit(false);

			// 定義對應的 account 值
			String[] accounts = {
				"john.doe@example.com",
				"jane.smith@example.com",
				"alice.johnson@example.com",
				"bob.brown@example.com",
				"charlie.davis@example.com",
				"diana.evans@example.com",
				"frank.green@example.com",
				"grace.harris@example.com",
				"henry.jackson@example.com",
				"ivy.king@example.com",
				"jack.lee@example.com",
				"karen.martin@example.com"
			};

			// 遍歷圖片並插入資料庫
			for (int i = 0; i < accounts.length; i++) {
				// 圖片檔案路徑
				String filePath = "src/main/webapp/frontstage/memberFrontend/Sticker/Sticker" + String.format("%02d", i + 1) + ".png";

				// 查詢對應的 member_id
				String selectSql = "SELECT member_id FROM member WHERE account = ?";
				pstmt = conn.prepareStatement(selectSql);
				pstmt.setString(1, accounts[i]);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					int memberId = rs.getInt("member_id");

					// 讀取圖片並插入
					try (InputStream inputStream = new FileInputStream(filePath)) {
						System.out.println("插入圖片: " + filePath + " 至 member_id = " + memberId);

						String updateSql = "UPDATE member SET sticker = ? WHERE member_id = ?";
						pstmt = conn.prepareStatement(updateSql);
						pstmt.setBinaryStream(1, inputStream, inputStream.available());
						pstmt.setInt(2, memberId);

						// 執行更新操作
						int rowsAffected = pstmt.executeUpdate();
						if (rowsAffected > 0) {
							System.out.println("成功插入圖片至 member_id = " + memberId);
						} else {
							System.out.println("插入失敗，member_id = " + memberId);
						}
					} catch (Exception ex) {
						System.out.println("圖片讀取失敗: " + filePath);
						ex.printStackTrace();
					}
				} else {
					System.out.println("找不到對應的 member_id，account = " + accounts[i]);
				}
			}

			// 提交事務
			conn.commit();
			System.out.println("所有圖片已成功插入並提交至資料庫。");

		} catch (Exception e) {
			// 如果有任何異常，回滾事務
			if (conn != null) {
				try {
					conn.rollback();
					System.out.println("插入操作失敗，事務已回滾。");
				} catch (Exception rollbackEx) {
					rollbackEx.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
