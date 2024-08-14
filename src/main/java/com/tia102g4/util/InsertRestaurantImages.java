package com.tia102g4.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertRestaurantImages {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/chugether";
        String user = "root";
        String password = "123456";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 載入JDBC驅動程式
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立連線
            conn = DriverManager.getConnection(url, user, password);
            
            // 關閉自動提交，啟用手動提交
            conn.setAutoCommit(false);

            // 準備SQL語句
            String sql = "UPDATE restaurant SET sticker = ? WHERE rest_name = ?";

            pstmt = conn.prepareStatement(sql);

            // 餐廳名稱與圖片對應的路徑
            String[][] restaurantData = {
                {"美式金黃炸物屋", "src/main/webapp/restaurant_images/GoldenFriedHouseUSA.png"},
                {"韓味館", "src/main/webapp/restaurant_images/KoreanFlavorHouse.png"},
                {"壽司天堂", "src/main/webapp/restaurant_images/SushiParadise.png"},
                {"法式風情", "src/main/webapp/restaurant_images/ElegantFrench.png"},
                {"華府餐廳", "src/main/webapp/restaurant_images/ImperialPalace.png"},
                {"爵頂牛排館", "src/main/webapp/restaurant_images/PrimeSteakhouse.png"},
                {"泰風味", "src/main/webapp/restaurant_images/ThaiFlavor.png"},
                {"甜蜜時光", "src/main/webapp/restaurant_images/SweetMoments.png"},
                {"拉麵匠", "src/main/webapp/restaurant_images/RamenMaster.png"},
                {"羅馬風味館", "src/main/webapp/restaurant_images/RomeFlavorHouse.png"}
            };

            // 遍歷餐廳數據並插入圖片
            for (String[] data : restaurantData) {
                String restName = data[0];
                String filePath = data[1];

                // 讀取圖片
                try (InputStream inputStream = new FileInputStream(filePath)) {
                    System.out.println("插入圖片: " + filePath + " 至餐廳: " + restName);

                    pstmt.setBinaryStream(1, inputStream, inputStream.available());
                    pstmt.setString(2, restName);

                    // 執行更新操作
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("成功插入圖片至餐廳: " + restName);
                    } else {
                        System.out.println("插入失敗，餐廳: " + restName);
                    }
                } catch (Exception ex) {
                    System.out.println("圖片讀取失敗: " + filePath);
                    ex.printStackTrace();
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
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
