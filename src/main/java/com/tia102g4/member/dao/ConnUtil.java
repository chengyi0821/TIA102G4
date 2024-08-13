package com.tia102g4.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtil {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/chugether?serverTimezone=Asia/Taipei&useUnicode=true&characterEncoding=utf-8&rewriteBatchedStatements=true";
    public static final String USER = "root";
    public static final String PWD = "123456";

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private static Connection createConn() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getConn(){
        Connection conn = threadLocal.get();
        if(conn == null){
            conn = createConn();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }

    public static void closeConn() throws SQLException {
        Connection conn = threadLocal.get();
        if(conn == null){
           return;
        }
        if(!conn.isClosed()){
            conn.close();

            //threadLocal.set(null);
            //or
            threadLocal.remove();
        }
    }
}
