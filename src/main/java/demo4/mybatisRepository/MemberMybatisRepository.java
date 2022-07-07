package demo4.mybatisRepository;

import demo4.vo.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMybatisRepository {
    void insertMember(Member member);

    int insertMemberDuCheck(Member member);

    Optional<Member> getMember(String username);
}
