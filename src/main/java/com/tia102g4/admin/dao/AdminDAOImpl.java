package com.tia102g4.admin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.tia102g4.admin.model.Admin;

	public class AdminDAOImpl extends BaseDAO<Admin> implements AdminDAO {
		
		@Override
	    public List<Admin> getAdminList() {
	            return super.executeQueryList("select * from Admin");
	    }


    private String dbUrl;
    private String dbUsername;
    private String dbPassword;

    public AdminDAOImpl() {
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
    public void addAdmin(Admin admin) throws SQLException {
        String sql = "INSERT INTO admin (name, account, password, permission, regis_date, acc_status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, admin.getName());
            ps.setString(2, admin.getAccount());
            ps.setString(3, admin.getPassword());
            ps.setInt(4, admin.getPermission());
            ps.setTimestamp(5, new java.sql.Timestamp(admin.getRegisDate().getTime()));
            ps.setBoolean(6, admin.getAccStatus());
            ps.executeUpdate();
        }
    }

    @Override
    public Admin findAdminById(Long id) throws SQLException {
        String sql = "SELECT * FROM admin WHERE admin_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractAdminFromResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public List<Admin> findAllAdmins() throws SQLException {
        String sql = "SELECT * FROM admin";
        List<Admin> admins = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                admins.add(extractAdminFromResultSet(rs));
            }
        }
        return admins;
    }

    @Override
    public List<Admin> findFilteredAdmins(String permissionFilter, Boolean statusFilter, String startDate, String endDate,
            String search, String sortField, String sortOrder,
            int itemsPerPage, int currentPage) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT * FROM admin WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (permissionFilter != null && !permissionFilter.isEmpty()) {
            sql.append("AND permission = ? ");
            params.add(Integer.parseInt(permissionFilter));
        }

        if (statusFilter != null) {
            sql.append("AND acc_status = ? ");
            params.add(statusFilter);
        }

        if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
            sql.append("AND regis_date BETWEEN ? AND ? ");
            params.add(Timestamp.valueOf(startDate + " 00:00:00"));
            params.add(Timestamp.valueOf(endDate + " 23:59:59"));
        }

        if (search != null && !search.isEmpty()) {
            sql.append("AND (name LIKE ? OR account LIKE ?) ");
            params.add("%" + search + "%");
            params.add("%" + search + "%");
        }

        if (sortField != null && !sortField.isEmpty()) {
            sql.append("ORDER BY ").append(sortField).append(" ").append(sortOrder).append(" ");
        } else {
            sql.append("ORDER BY regis_date DESC ");
        }

        sql.append("LIMIT ? OFFSET ?");
        params.add(itemsPerPage);
        params.add((currentPage - 1) * itemsPerPage);

        List<Admin> admins = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = createPreparedStatement(conn, sql.toString(), params);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                admins.add(extractAdminFromResultSet(rs));
            }
        }
        return admins;
    }

    @Override
    public int countFilteredAdmins(String permissionFilter, Boolean statusFilter, String startDate, String endDate, String search) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM admin WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (permissionFilter != null && !permissionFilter.isEmpty()) {
            sql.append("AND permission = ? ");
            params.add(Integer.parseInt(permissionFilter));
        }

        if (statusFilter != null) {
            sql.append("AND acc_status = ? ");
            params.add(statusFilter);
        }

        if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
            sql.append("AND regis_date BETWEEN ? AND ? ");
            params.add(Timestamp.valueOf(startDate + " 00:00:00"));
            params.add(Timestamp.valueOf(endDate + " 23:59:59"));
        }

        if (search != null && !search.isEmpty()) {
            sql.append("AND (name LIKE ? OR account LIKE ?) ");
            params.add("%" + search + "%");
            params.add("%" + search + "%");
        }

        try (Connection conn = getConnection();
             PreparedStatement ps = createPreparedStatement(conn, sql.toString(), params);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    @Override
    public void updateAdmin(Admin admin) throws SQLException {
        String sql = "UPDATE admin SET name = ?, account = ?, password = ?, permission = ?, acc_status = ? WHERE admin_id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, admin.getName());
            pstmt.setString(2, admin.getAccount());
            pstmt.setString(3, admin.getPassword());
            pstmt.setInt(4, admin.getPermission());
            pstmt.setBoolean(5, admin.getAccStatus());
            pstmt.setLong(6, admin.getAdminId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteAdmin(Long id) throws SQLException {
        String sql = "DELETE FROM admin WHERE admin_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Admin findAdminByAccount(String account) throws SQLException {
        String sql = "SELECT * FROM admin WHERE account = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, account);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractAdminFromResultSet(rs);
            }
        }
        return null;
    }

    private Admin extractAdminFromResultSet(ResultSet rs) throws SQLException {
        Admin admin = new Admin();
        admin.setAdminId(rs.getLong("admin_id"));
        admin.setName(rs.getString("name"));
        admin.setAccount(rs.getString("account"));
        admin.setPassword(rs.getString("password"));
        admin.setPermission(rs.getInt("permission"));
        admin.setRegisDate(rs.getTimestamp("regis_date"));
        admin.setAccStatus(rs.getBoolean("acc_status"));
        return admin;
    }

    private PreparedStatement createPreparedStatement(Connection conn, String sql, List<Object> params) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < params.size(); i++) {
            ps.setObject(i + 1, params.get(i));
        }
        return ps;
    }
}
