package com.tia102g4.rest.service;

import com.tia102g4.rest.dao.RestaurantDAO;
import com.tia102g4.rest.dao.RestaurantDAOImpl;
import com.tia102g4.rest.model.Restaurant;

import java.sql.SQLException;

public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantDAO restaurantDAO = new RestaurantDAOImpl();

    @Override
    public void register(Restaurant restaurant) throws SQLException {
        restaurantDAO.addRestaurant(restaurant);
    }

    @Override
    public Restaurant login(String account, String password) throws SQLException {
        Restaurant restaurant = restaurantDAO.findRestaurantByAccount(account);
        if (restaurant != null && restaurant.getPassword().equals(password)) {
            return restaurant;
        }
        return null;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) throws SQLException {
        restaurantDAO.updateRestaurant(restaurant);
    }

    @Override
    public void updatePassword(Long id, String newPassword) throws SQLException {
        restaurantDAO.updatePassword(id, newPassword);
    }

    @Override
    public void resetPasswordToken(Long id, String resetToken) throws SQLException {
        restaurantDAO.resetPasswordToken(id, resetToken);
    }

    @Override
    public Restaurant findByEmail(String email) throws SQLException {
        return restaurantDAO.findRestaurantByEmail(email);
    }

    @Override
    public Restaurant findByResetToken(String resetToken) throws SQLException {
        return restaurantDAO.findRestaurantByResetToken(resetToken);
    }
    
    @Override
    public Restaurant findRestaurantByAccount(String account) throws SQLException {
        return restaurantDAO.findRestaurantByResetToken(account);
    }
}
