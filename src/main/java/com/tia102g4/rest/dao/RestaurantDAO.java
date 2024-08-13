package com.tia102g4.rest.dao;

import java.sql.SQLException;
import java.util.List;

import com.tia102g4.rest.model.Restaurant;

public interface RestaurantDAO {
    void addRestaurant(Restaurant restaurant) throws SQLException;
    Restaurant findRestaurantById(Long id) throws SQLException;
    Restaurant findRestaurantByAccount(String account) throws SQLException;
    void updateRestaurant(Restaurant restaurant) throws SQLException;
    void updatePassword(Long id, String newPassword) throws SQLException;
    void resetPasswordToken(Long id, String resetToken) throws SQLException;
    Restaurant findRestaurantByEmail(String email) throws SQLException;
    Restaurant findRestaurantByResetToken(String resetToken) throws SQLException;
    List<Restaurant> getRestaurantList();
}
