package com.tia102g4.member.dao;

import java.util.List;
import java.util.Map;

import com.tia102g4.member.model.Member;


	public interface MemberDAO {
		
		long insert(Member entity);
		
		void updatePassword(Member entity);
		
		void update(Member entity);
		
		long delete(Long id);
		
		Member getById(Long id);
		
		List<Member> getAll();
		
		List<Member> getByCompositeQuery(Map<String, String> map);
		
		List<Member> getAll(int currentPage);
		
		long getTotal();
		
		Member findByEmailAndPassword(String email, String password);

		boolean findForPasswordReset(String email);
		
		boolean isDuplicateAccount(String account);
		
		boolean isDuplicateEmail(String email);
		
		boolean isDuplicateMobileNo(String mobileNo);
		
		Member findfindByNameEmailAndMobile(String name, String email, String mobileNo);
	}


