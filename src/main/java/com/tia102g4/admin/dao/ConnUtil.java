package com.tia102g4.admin.dao;

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


/*
* ThreadLocal
* - get() ， set(obj)
* - ThreadLocal 稱之為本地執行緒。我們可以通過set方法在當前執行緒上存儲數據、通過get方法在當前執行緒獲取數據
* - set方法源碼分析
* public void set(T value) {
        Thread t = Thread.currentThread();   >獲取當前的執行緒
        ThreadLocalMap map = getMap(t);      >每一個執行緒都維護各自的一個容器(ThreadLocalMap)
        if (map != null) {
            map.set(this, value);            >這裡的key對應的是ThreadLocal，因為我們的組建中需要傳輸(共享)的物件可能會有多個(不止Connection)
        } else {
            createMap(t, value);             >默認情況下map是沒有初始化的，那麼第一次往其中添加數據時，會初始化。
        }
    }
* */
