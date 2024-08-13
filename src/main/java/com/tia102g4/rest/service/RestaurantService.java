package com.tia102g4.rest.service;

import com.tia102g4.rest.model.Restaurant;

import java.sql.SQLException;

public interface RestaurantService {
    void register(Restaurant restaurant) throws SQLException;
    Restaurant login(String account, String password) throws SQLException;
    void updateRestaurant(Restaurant restaurant) throws SQLException;
    void updatePassword(Long id, String newPassword) throws SQLException;
    void resetPasswordToken(Long id, String resetToken) throws SQLException;
    Restaurant findByEmail(String email) throws SQLException;
    Restaurant findByResetToken(String resetToken) throws SQLException;
	Restaurant findRestaurantByAccount(String account) throws SQLException;
	Long getRestaurantIdByAccount(String value);;
}
