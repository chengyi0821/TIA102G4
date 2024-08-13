package com.tia102g4.member.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import com.tia102g4.member.dao.MemberDAO;
import com.tia102g4.member.dao.MemberDAOImpl;
import com.tia102g4.member.model.Member;

public class MemberServiceImpl implements MemberService {
	
	public boolean testDatabaseConnection() {
	    try {
	        Member member = memberDAO.findMemberByAccount("test_account");
	        if (member != null) {
	            System.out.println("Database connection and query successful!");
	            return true;
	        } else {
	            System.out.println("Query executed, but no data found.");
	            return false;
	        }
	    } catch (SQLException e) {
	        System.out.println("Database connection or query failed: " + e.getMessage());
	        return false;
	    }
	}

	private static final int TOKEN_EXPIRATION_TIME = 30; // Token 有效期（分鐘）
	private MemberDAO memberDAO = new MemberDAOImpl();
	

	@Override
	public boolean registerMember(String name, Boolean gender, String account, String password, String mobileNo,
			String email, byte[] sticker, Timestamp regisDate, Boolean accStatus) throws SQLException {

		if (memberDAO.findMemberByAccount(account) != null) {
			return false;
		}
		Member member = new Member();
		member.setName(name);
		member.setGender(gender);
		member.setAccount(account);
		member.setPassword(password);
		member.setMobileNo(mobileNo);
		member.setEmail(email);
		member.setSticker(sticker);
		member.setRegisDate(new Timestamp(System.currentTimeMillis()));
		member.setAccStatus(accStatus);
		memberDAO.saveMember(member);
		return true;
	}

	@Override
	public boolean authenticate(String account, String password) throws SQLException {
		Member member = memberDAO.findMemberByAccount(account);
		return member != null && member.getPassword().equals(password);
	}

	@Override
	public Long getMemberIdByAccount(String account) throws SQLException {
		Member member = memberDAO.findMemberByAccount(account);
		return member != null ? member.getMemberId() : null;
	}

	@Override
	public Member findMemberByAccount(String account) throws SQLException {
		return memberDAO.findMemberByAccount(account);
	}

	@Override
	public boolean resetPassword(String token, String newPassword) throws SQLException {
		Member member = memberDAO.findMemberByResetToken(token);
		if (member == null || member.getTokenCreatedAt() == null) {
			return false;
		}

		long currentTimeMillis = System.currentTimeMillis();
		long tokenTimeMillis = ((Timestamp) member.getTokenCreatedAt()).getTime();
		long diff = currentTimeMillis - tokenTimeMillis;

		// Token 有效期為 30 分鐘
		if (diff > TOKEN_EXPIRATION_TIME * 60 * 1000) {
			return false;
		}

		member.setPassword(newPassword);
		memberDAO.updateMember(member);
		return true;
	}

	@Override
	public void savePasswordResetToken(Long memberId, String token, Timestamp tokenCreatedAt) throws SQLException {
		memberDAO.savePasswordResetToken(memberId, token, tokenCreatedAt);
	}

	@Override
	public boolean isValidResetToken(String token) throws SQLException {
		Member member = memberDAO.findMemberByResetToken(token);
		if (member == null || member.getTokenCreatedAt() == null) {
			return false;
		}

		long currentTimeMillis = System.currentTimeMillis();
		long tokenTimeMillis = ((Timestamp) member.getTokenCreatedAt()).getTime();
		long diff = currentTimeMillis - tokenTimeMillis;

		return diff <= TOKEN_EXPIRATION_TIME * 60 * 1000;
	}

}