package com.tia102g4.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import com.tia102g4.member.model.Member;
//產生假資料使用
public class MakeFriends {
	public List<Member> ImaginaryFriend(int seat) {
        List<Member> memberList = new ArrayList<>();
        
        Supplier<Member> memberSupplier = () -> {
            Member member = new Member();
            member.setMemberId(memberList.size() + 1L);
            return member;
        };
        
        for (int i = 0; i < seat; i++) {
            memberList.add(memberSupplier.get());
        }
        
        return memberList;
    }
}
