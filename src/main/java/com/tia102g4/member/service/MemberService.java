package com.tia102g4.member.service;

import java.util.List;
import java.util.Map;

import com.tia102g4.member.model.Member;

public interface MemberService {
   Member addMember(Member member);
   
   Member updateMember(Member member);
   
   void deleteMember(Long memberId);
   
   Member getMemberByMemberId(Long memberId);
   
   List<Member> getAllMembers(int currentPage);
   
   int getPageTotal();
   
   List<Member> getMembersByCompositeQuery(Map<String, String[]> map);
   
   Member login(String email, String password);
   
   boolean validateForPasswordReset(String email);
   
   void updatePassword(String email, String newPassword);
   
   boolean isDuplicate(Member member);
   
   
}



