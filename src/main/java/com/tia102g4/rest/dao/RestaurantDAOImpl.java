package com.tia102g4.rest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import com.tia102g4.rest.model.Restaurant;

public class RestaurantDAOImpl extends BaseDAO<Restaurant> implements RestaurantDAO {
		
		@Override
	    public List<Restaurant> getRestaurantList() {
	            return super.executeQueryList("select * from Restaurant");
	    }

    private String dbUrl;
    private String dbUsername;
    private String dbPassword;

    public RestaurantDAOImpl() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
            dbUrl = props.getProperty("db.url");
            dbUsername = props.getProperty("db.username");
            dbPassword = props.getProperty("db.password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

    @Override
    public void addRestaurant(Restaurant restaurant) throws SQLException {
        String sql = "INSERT INTO restaurant (type_id, rest_name, description, location, phone, email, password, account, sticker, open_day, open_time1, close_time1, open_time2, close_time2, acc_status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, restaurant.getTypeId());
            ps.setString(2, restaurant.getRestName());
            ps.setString(3, restaurant.getDescription());
            ps.setString(4, restaurant.getLocation());
            ps.setString(5, restaurant.getPhone());
            ps.setString(6, restaurant.getEmail());
            ps.setString(7, restaurant.getPassword());
            ps.setString(8, restaurant.getAccount());
            ps.setBytes(9, restaurant.getSticker());
            ps.setString(10, restaurant.getOpenDay());
            ps.setTime(11, restaurant.getOpenTime1());
            ps.setTime(12, restaurant.getCloseTime1());
            ps.setTime(13, restaurant.getOpenTime2());
            ps.setTime(14, restaurant.getCloseTime2());
            ps.setBoolean(15, restaurant.getAccStatus());
            ps.executeUpdate();
        }
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws SQLException {
        String sql = "SELECT * FROM restaurant WHERE rest_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractRestaurantFromResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public Restaurant findRestaurantByAccount(String account) throws SQLException {
        String sql = "SELECT * FROM restaurant WHERE account = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, account);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractRestaurantFromResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) throws SQLException {
        String sql = "UPDATE restaurant SET type_id = ?, rest_name = ?, description = ?, location = ?, phone = ?, email = ?, open_day = ?, open_time1 = ?, close_time1 = ?, open_time2 = ?, close_time2 = ?, acc_status = ? WHERE rest_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, restaurant.getTypeId());
            ps.setString(2, restaurant.getRestName());
            ps.setString(3, restaurant.getDescription());
            ps.setString(4, restaurant.getLocation());
            ps.setString(5, restaurant.getPhone());
            ps.setString(6, restaurant.getEmail());
            ps.setString(7, restaurant.getOpenDay());
            ps.setTime(8, restaurant.getOpenTime1());
            ps.setTime(9, restaurant.getCloseTime1());
            ps.setTime(10, restaurant.getOpenTime2());
            ps.setTime(11, restaurant.getCloseTime2());
            ps.setBoolean(12, restaurant.getAccStatus());
            ps.setLong(13, restaurant.getRestId());
            ps.executeUpdate();
        }
    }

    @Override
    public void updatePassword(Long id, String newPassword) throws SQLException {
        String sql = "UPDATE restaurant SET password = ? WHERE rest_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setLong(2, id);
            ps.executeUpdate();
        }
    }

    @Override
    public void resetPasswordToken(Long id, String resetToken) throws SQLException {
        String sql = "UPDATE restaurant SET reset_token = ?, token_created_at = CURRENT_TIMESTAMP WHERE rest_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, resetToken);
            ps.setLong(2, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Restaurant findRestaurantByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM restaurant WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractRestaurantFromResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public Restaurant findRestaurantByResetToken(String resetToken) throws SQLException {
        String sql = "SELECT * FROM restaurant WHERE reset_token = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, resetToken);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractRestaurantFromResultSet(rs);
            }
        }
        return null;
    }

    private Restaurant extractRestaurantFromResultSet(ResultSet rs) throws SQLException {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestId(rs.getLong("rest_id"));
        restaurant.setTypeId(rs.getLong("type_id"));
        restaurant.setRestName(rs.getString("rest_name"));
        restaurant.setDescription(rs.getString("description"));
        restaurant.setLocation(rs.getString("location"));
        restaurant.setPhone(rs.getString("phone"));
        restaurant.setRegistTime(rs.getTimestamp("regist_time"));
        restaurant.setEmail(rs.getString("email"));
        restaurant.setPassword(rs.getString("password"));
        restaurant.setAccount(rs.getString("account"));
        restaurant.setSticker(rs.getBytes("sticker"));
        restaurant.setOpenDay(rs.getString("open_day"));
        restaurant.setOpenTime1(rs.getTime("open_time1"));
        restaurant.setCloseTime1(rs.getTime("close_time1"));
        restaurant.setOpenTime2(rs.getTime("open_time2"));
        restaurant.setCloseTime2(rs.getTime("close_time2"));
        restaurant.setRatingPnum(rs.getInt("rating_pnum"));
        restaurant.setRatingStar(rs.getInt("rating_star"));
        restaurant.setAccStatus(rs.getBoolean("acc_status"));
        restaurant.setResetToken(rs.getString("reset_token"));
        restaurant.setTokenCreatedAt(rs.getTimestamp("token_created_at"));
        return restaurant;
    }
}
