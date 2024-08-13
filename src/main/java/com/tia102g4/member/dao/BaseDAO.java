package com.tia102g4.member.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<T> {
    protected Connection conn;
    protected PreparedStatement ps;
    protected ResultSet rs;

    //T的Class物件
    private Class entityClass;

    public BaseDAO() {
        //getClass() 獲取Class對象，當前我們執行的是new FruitDAOImpl()，創建的是FruitDAOImpl的物件
        //那麼子類建構子內部首先會調用父類(BaseDAO)的無參建構子
        //因此此處的getClass()會被執行，但是getClass獲取的是FruitDAOImpl的Class
        //所以getGenericSuperclass()獲取倒的是BaseDAO的Class
        Type genericType = getClass().getGenericSuperclass();
        //ParameterizedType 參數化類型
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        //獲取到的<T>中的T的真實的型別
        Type actualType = actualTypeArguments[0];
        try {
            entityClass = Class.forName(actualType.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("constructor Error");
        }
    }

    /**
     * 獲取連線
     *
     * @return conn
     */
    protected Connection getConn() {
        return ConnUtil.getConn();
    }

    /**
     * 資源關閉
     *
     * @param conn
     * @param ps
     * @param rs
     */
    protected void close(Connection conn, PreparedStatement ps, ResultSet rs) {
    }

    /**
     * 給預處理命令物件設置參數
     *
     * @param ps
     * @param params
     * @throws SQLException
     */
    private void setParams(PreparedStatement ps, Object... params) throws SQLException {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        }
    }

    /**
     * 執行更新
     *
     * @param sql
     * @param params
     * @return 返回影響筆數
     */
    protected int executeUpdate(String sql, Object... params) {
        boolean insertFlag = false;
        insertFlag = sql.trim().toUpperCase().startsWith("insert");
        conn = getConn();
        try {
            if (insertFlag) {
                ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            } else {
                ps = conn.prepareStatement(sql);
            }
            setParams(ps, params);
            int count = ps.executeUpdate();

            if (insertFlag) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return ((Long) rs.getLong(1)).intValue();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO.executeUpdate error: " + e.getMessage());
        }
        return 0;
    }

    /**
     * 通過反射技術給obj物件的property屬性賦propertyValue值
     *
     * @param obj
     * @param property
     * @param propertyValue
     */
    private void setValue(Object obj, String property, Object propertyValue) throws IllegalAccessException, NoSuchFieldException {
        Class clazz = obj.getClass();

        //獲取property這個字符串對應的屬性名，比如"fid" 去找obj物件中的fid
        Field field = clazz.getDeclaredField(property);
        if (field != null) {
            field.setAccessible(true);
            field.set(obj, propertyValue);
        }
    }

    /**
     * 執行複雜查詢
     *
     * @param sql
     * @param params
     * @return 統計結果
     */
    protected Object[] executeComplexQuery(String sql, Object... params) {
        conn = getConn();
        try {
            ps = conn.prepareStatement(sql);
            setParams(ps, params);
            rs = ps.executeQuery();

            //通過rs可以獲取結果集的元數據
            //元數據，描述結果集數據的數據，簡單講 就是這個結果集有哪些【欄、型別】等等
            ResultSetMetaData rsmd = rs.getMetaData();
            //獲取結果集的欄數
            int columnCount = rsmd.getColumnCount();
            Object[] columnValueArr = new Object[columnCount];
            //解析rs
            if (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    columnValueArr[i] = columnValue;
                }
                return columnValueArr;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO.executeComplexQuery error: " + e.getMessage());
        }
        return null;
    }

    /**
     * 執行查詢
     *
     * @param sql
     * @param params
     * @return 一個實體物件
     */
    protected T load(String sql, Object... params) {
        conn = getConn();
        try {
            ps = conn.prepareStatement(sql);
            setParams(ps, params);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                T t = (T) entityClass.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    Object columnValue = rs.getObject(i + 1);
                    setValue(t, columnLabel, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO.load error: " + e.getMessage());
        }
        return null;
    }

    //執行查詢，返回List
    protected List<T> executeQueryList(String sql, Object... params) {
        List<T> list = null;
        list = new ArrayList<>();
        conn = getConn();
        try {
            ps = conn.prepareStatement(sql);
            setParams(ps, params);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                T t = (T) entityClass.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    Object columnValue = rs.getObject(i + 1);
                    setValue(t, columnLabel, columnValue);
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO.executeQueryList error: " + e.getMessage());
        }
        return list;
    }
}
