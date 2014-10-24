package org.jbug.devping.domain.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nadal on 2014. 10. 4..
 */
@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public void signIn(Member member) {
        memberRepository.save(member);
    }

    public void signOut(Member member) {
    }

    public Member logIn(String email, String password) {
        Member member = new Member();
        return member;
    }

    public void logOut(String email) {
    }

    public Member findOne(String email) {
        return memberRepository.findOne(email);
    }
}
