package com.tia102g4.member.dao;

import java.sql.SQLException;
import java.util.List;
import com.tia102g4.member.model.Member;

public interface MemberDAO {
    void saveMember(Member member) throws SQLException;
    void updateMember(Member member) throws SQLException;
    void savePasswordResetToken(Long memberId, String resetToken, java.sql.Timestamp tokenCreatedAt) throws SQLException;
    Member findMemberByAccount(String account) throws SQLException;
    Member findMemberByResetToken(String resetToken) throws SQLException;
    Member findMemberById(Long memberId) throws SQLException;
    
    // 會員列表
    List<Member> findAllMembers() throws SQLException;
    List<Member> findFilteredMembers(String search, Boolean genderFilter, String startDate, String endDate, String sortField, String sortOrder, int itemsPerPage, int currentPage) throws SQLException;
    int countFilteredMembers(String search, Boolean genderFilter, String startDate, String endDate) throws SQLException;
    void disableMember(Long memberId) throws SQLException;
	List<Member> getMemberList();
}