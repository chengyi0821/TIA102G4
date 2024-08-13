package com.tia102g4.member.dao;

import com.tia102g4.member.dao.MemberDAO;
import com.tia102g4.member.model.Member;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl extends BaseDAO<Member> implements MemberDAO {
	
	
	@Override
    public List<Member> getMemberList() {
            return super.executeQueryList("select * from Member");
    }
	
	  private DataSource dataSource;

	    public MemberDAOImpl() {
	        try {
	            Context initContext = new InitialContext();
	            dataSource = (DataSource) initContext.lookup("java:/comp/env/jdbc/TestDB2");
	        } catch (NamingException e) {
	            e.printStackTrace();
	        }
	    }

	    private Connection getConnection() throws SQLException {
	        return dataSource.getConnection();
	    }

	    @Override
	    public void saveMember(Member member) throws SQLException {
	        String sql = "INSERT INTO member (name, gender, account, password, mobile_no, email, sticker, regis_date, acc_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, member.getName());
	            pstmt.setBoolean(2, member.getGender());
	            pstmt.setString(3, member.getAccount());
	            pstmt.setString(4, member.getPassword());
	            pstmt.setString(5, member.getMobileNo());
	            pstmt.setString(6, member.getEmail());
	            pstmt.setBytes(7, member.getSticker());
	            pstmt.setTimestamp(8, member.getRegisDate());
	            pstmt.setBoolean(9, member.getAccStatus());
	            pstmt.executeUpdate();
	        }
	    }

	    @Override
	    public void updateMember(Member member) throws SQLException {
	        String sql = "UPDATE member SET name = ?, gender = ?, account = ?, password = ?, mobile_no = ?, email = ?, acc_status = ?, sticker = ?, reset_token = ?, token_created_at = ? WHERE member_id = ?";
	        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, member.getName());
	            stmt.setBoolean(2, member.getGender());
	            stmt.setString(3, member.getAccount());
	            stmt.setString(4, member.getPassword());
	            stmt.setString(5, member.getMobileNo());
	            stmt.setString(6, member.getEmail());
	            stmt.setBoolean(7, member.getAccStatus());
	            stmt.setBytes(8, member.getSticker());
	            stmt.setString(9, member.getResetToken());
	            stmt.setTimestamp(10, member.getTokenCreatedAt());
	            stmt.setLong(11, member.getMemberId());
	            stmt.executeUpdate();
	        }
	    }

	    @Override
	    public void savePasswordResetToken(Long memberId, String resetToken, Timestamp tokenCreatedAt) throws SQLException {
	        String sql = "UPDATE member SET reset_token = ?, token_created_at = ? WHERE member_id = ?";
	        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, resetToken);
	            stmt.setTimestamp(2, tokenCreatedAt);
	            stmt.setLong(3, memberId);
	            stmt.executeUpdate();
	        }
	    }

	    @Override
	    public Member findMemberByAccount(String account) throws SQLException {
	        String sql = "SELECT * FROM member WHERE account = ?";
	        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, account);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return mapRowToMember(rs);
	                }
	            }
	        }
	        return null;
	    }

	    @Override
	    public Member findMemberByResetToken(String resetToken) throws SQLException {
	        String sql = "SELECT * FROM member WHERE reset_token = ?";
	        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, resetToken);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return mapRowToMember(rs);
	                }
	            }
	        }
	        return null;
	    }

	    @Override
	    public Member findMemberById(Long memberId) throws SQLException {
	        String sql = "SELECT * FROM member WHERE member_id = ?";
	        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setLong(1, memberId);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return mapRowToMember(rs);
	                }
	            }
	        }
	        return null;
	    }

	    @Override
	    public List<Member> findAllMembers() throws SQLException {
	        String sql = "SELECT * FROM member";
	        List<Member> members = new ArrayList<>();
	        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                members.add(mapRowToMember(rs));
	            }
	        }
	        return members;
	    }

	    @Override
	    public List<Member> findFilteredMembers(String search, Boolean genderFilter, String startDate, String endDate, String sortField, String sortOrder, int itemsPerPage, int currentPage) throws SQLException {
	        StringBuilder sql = new StringBuilder("SELECT * FROM member WHERE 1=1");
	        List<Object> params = new ArrayList<>();

	        if (search != null && !search.isEmpty()) {
	            sql.append(" AND (name LIKE ? OR account LIKE ? OR email LIKE ? OR mobile_no LIKE ?)");
	            String searchPattern = "%" + search + "%";
	            params.add(searchPattern);
	            params.add(searchPattern);
	            params.add(searchPattern);
	            params.add(searchPattern);
	        }

	        if (genderFilter != null) {
	            sql.append(" AND gender = ?");
	            params.add(genderFilter);
	        }

	        if (startDate != null && !startDate.isEmpty()) {
	            sql.append(" AND regis_date >= ?");
	            params.add(Timestamp.valueOf(startDate + " 00:00:00"));
	        }

	        if (endDate != null && !endDate.isEmpty()) {
	            sql.append(" AND regis_date <= ?");
	            params.add(Timestamp.valueOf(endDate + " 23:59:59"));
	        }

	        if (sortField != null && !sortField.isEmpty()) {
	            sql.append(" ORDER BY ").append(sortField).append(" ").append(sortOrder);
	        } else {
	            sql.append(" ORDER BY regis_date DESC");
	        }

	        sql.append(" LIMIT ? OFFSET ?");
	        params.add(itemsPerPage);
	        params.add((currentPage - 1) * itemsPerPage);

	        List<Member> members = new ArrayList<>();
	        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
	            for (int i = 0; i < params.size(); i++) {
	                stmt.setObject(i + 1, params.get(i));
	            }

	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    members.add(mapRowToMember(rs));
	                }
	            }
	        }
	        return members;
	    }

	    @Override
	    public int countFilteredMembers(String search, Boolean genderFilter, String startDate, String endDate) throws SQLException {
	        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM member WHERE 1=1");
	        List<Object> params = new ArrayList<>();

	        if (search != null && !search.isEmpty()) {
	            sql.append(" AND (name LIKE ? OR account LIKE ? OR email LIKE ? OR mobile_no LIKE ?)");
	            String searchPattern = "%" + search + "%";
	            params.add(searchPattern);
	            params.add(searchPattern);
	            params.add(searchPattern);
	            params.add(searchPattern);
	        }

	        if (genderFilter != null) {
	            sql.append(" AND gender = ?");
	            params.add(genderFilter);
	        }

	        if (startDate != null && !startDate.isEmpty()) {
	            sql.append(" AND regis_date >= ?");
	            params.add(Timestamp.valueOf(startDate + " 00:00:00"));
	        }

	        if (endDate != null && !endDate.isEmpty()) {
	            sql.append(" AND regis_date <= ?");
	            params.add(Timestamp.valueOf(endDate + " 23:59:59"));
	        }

	        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
	            for (int i = 0; i < params.size(); i++) {
	                stmt.setObject(i + 1, params.get(i));
	            }

	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt(1);
	                }
	            }
	        }
	        return 0;
	    }

	    @Override
	    public void disableMember(Long memberId) throws SQLException {
	        String sql = "UPDATE member SET acc_status = false WHERE member_id = ?";
	        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setLong(1, memberId);
	            stmt.executeUpdate();
	        }
	    }

	    private Member mapRowToMember(ResultSet rs) throws SQLException {
	        Member member = new Member();
	        member.setMemberId(rs.getLong("member_id"));
	        member.setName(rs.getString("name"));
	        member.setGender(rs.getBoolean("gender"));
	        member.setAccount(rs.getString("account"));
	        member.setPassword(rs.getString("password"));
	        member.setMobileNo(rs.getString("mobile_no"));
	        member.setEmail(rs.getString("email"));
	        member.setSticker(rs.getBytes("sticker"));
	        member.setRegisDate(rs.getTimestamp("regis_date"));
	        member.setAccStatus(rs.getBoolean("acc_status"));
	        member.setResetToken(rs.getString("reset_token"));
	        member.setTokenCreatedAt(rs.getTimestamp("token_created_at"));
	        return member;
	    }
	}