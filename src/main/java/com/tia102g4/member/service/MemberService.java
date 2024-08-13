package com.tia102g4.member.service;

import java.sql.SQLException;
import java.sql.Timestamp;

import com.tia102g4.member.model.Member;

public interface MemberService {

	boolean registerMember(String name, Boolean gender, String account, String password, String mobileNo, String email,
			byte[] sticker, Timestamp regisDate, Boolean accStatus) throws SQLException;

	boolean authenticate(String account, String password) throws SQLException;

	Long getMemberIdByAccount(String account) throws SQLException;

	Member findMemberByAccount(String account) throws SQLException;

	boolean resetPassword(String token, String newPassword) throws SQLException;

	void savePasswordResetToken(Long memberId, String token, Timestamp tokenCreatedAt) throws SQLException;

	boolean isValidResetToken(String token) throws SQLException;

}