package com.tia102g4.member.service;


	import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;

	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import java.util.Set;

	import com.tia102g4.member.dao.MemberDAO;
	import com.tia102g4.member.dao.MemberDAOImp1;
	import com.tia102g4.member.model.Member;

	public class MemberServiceImp1 implements MemberService {
	    
	    private MemberDAO dao;

	    public MemberServiceImp1() {
	        dao = new MemberDAOImp1();
	    }
	    
	    @Override
		public Member addMember(Member member) {
	    	dao.insert(member);
			return member;
		}


		@Override
		public Member updateMember(Member member) {
		    dao.update(member);
		    return member;
		}
	    
		@Override
		public void deleteMember(Long memberId) {
			dao.delete(memberId);
		}

		@Override
		public Member getMemberByMemberId(Long memberId) {
			return dao.getById(memberId);
		}

		@Override
		public List<Member> getAllMembers(int currentPage) {
			 return dao.getAll(currentPage);
		}

		@Override
		public List<Member> getMembersByCompositeQuery(Map<String, String[]> map) {
			Map<String, String> query = new HashMap<>();
			Set<Map.Entry<String, String[]>> entry = map.entrySet();
			
			for (Map.Entry<String, String[]> row : entry) {
				String key = row.getKey();
				if ("action".equals(key)) {
					continue;
				}
				String value = row.getValue()[0]; 
				if (value == null || value.isEmpty()) {
					continue;
				}
				query.put(key, value);
			}
			
			System.out.println(query);
			
			return dao.getByCompositeQuery(query);
		}

		@Override
		public int getPageTotal() {
			long total = dao.getTotal();
			int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
			return pageQty;
		}

		@Override
		public Member login(String email, String password) {
			return dao.findByEmailAndPassword(email, password);
		}

		@Override
		public boolean validateForPasswordReset(String email) {
			return dao.findForPasswordReset(email);
		}

		@Override
		public void updatePassword(String email, String newPassword) {
			Member member = new Member();
			member.setPassword(newPassword);
			member.setEmail(email);
			dao.updatePassword(member);
		}

		@Override
		public boolean isDuplicate(Member member) {
			return dao.isDuplicateAccount(member.getAccount())||
				   dao.isDuplicateEmail(member.getEmail()) ||
				   dao.isDuplicateMobileNo(member.getMobileNo());
		}
		

		}
