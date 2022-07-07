package demo4.service;

import demo4.mybatisRepository.MemberMybatisRepository;
import demo4.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserService {
    private final MemberMybatisRepository memberMybatisRepository;
    private final PasswordEncoder passwordEncoder;

    public void insertMember(Member member) {
        
        // 비밀번호 암호화
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberMybatisRepository.insertMember(member);
    }

    public int insertMemberDuCheck(Member member) {
        return memberMybatisRepository.insertMemberDuCheck(member);
    }
}
