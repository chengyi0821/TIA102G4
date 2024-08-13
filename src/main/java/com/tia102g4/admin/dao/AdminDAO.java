package com.tia102g4.admin.dao;

import com.tia102g4.admin.model.Admin;

import java.sql.SQLException;
import java.util.List;

public interface AdminDAO {
    void addAdmin(Admin admin) throws SQLException;
    Admin findAdminById(Long id) throws SQLException;
    List<Admin> findAllAdmins() throws SQLException;
    int countFilteredAdmins(String permissionFilter, Boolean statusFilter, String startDate, String endDate, String search) throws SQLException;

    List<Admin> findFilteredAdmins(String permissionFilter, Boolean statusFilter, String startDate, String endDate,
                                   String search, String sortField, String sortOrder,
                                   int itemsPerPage, int currentPage) throws SQLException;

    void updateAdmin(Admin admin) throws SQLException;
    void deleteAdmin(Long id) throws SQLException;
    Admin findAdminByAccount(String account) throws SQLException;
	List<Admin> getAdminList();
}
