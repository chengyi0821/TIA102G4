package com.tia102g4.admin.service;

import java.sql.SQLException;
import java.util.List;

import com.tia102g4.admin.dao.AdminDAO;
import com.tia102g4.admin.dao.AdminDAOImpl;
import com.tia102g4.admin.model.Admin;

public class AdminServiceImpl implements AdminService {

    private AdminDAO adminDAO = new AdminDAOImpl();

    @Override
    public List<Admin> findFilteredAdmins(String permissionFilter, Boolean statusFilter, String startDate, String endDate,
            String search, String sortField, String sortOrder,
            int itemsPerPage, int currentPage) throws SQLException {
return adminDAO.findFilteredAdmins(permissionFilter, statusFilter, startDate, endDate, search, sortField, sortOrder, itemsPerPage, currentPage);
}

    public int countFilteredAdmins(String permissionFilter, Boolean statusFilter, String startDate, String endDate, String search) throws SQLException {
        return adminDAO.countFilteredAdmins(permissionFilter, statusFilter, startDate, endDate, search);
    }

    @Override
    public List<Admin> findAllAdmins() throws SQLException {
        return adminDAO.findAllAdmins();
    }

    @Override
    public Admin findAdminById(Long id) throws SQLException {
        return adminDAO.findAdminById(id);
    }

    @Override
    public Admin findAdminByAccount(String account) throws SQLException {
        return adminDAO.findAdminByAccount(account);
    }

    @Override
    public void addAdmin(Admin admin) throws SQLException {
        adminDAO.addAdmin(admin);
    }

    @Override
    public void updateAdmin(Admin admin) throws SQLException {
        adminDAO.updateAdmin(admin);
    }

    @Override
    public void deleteAdmin(Long id) throws SQLException {
        adminDAO.deleteAdmin(id);
    }

    @Override
    public Admin authenticate(String account, String password) throws SQLException {
        Admin admin = adminDAO.findAdminByAccount(account);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }
}
