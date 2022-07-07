package demo4.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import demo4.mybatisRepository.MemberMybatisRepository;
import demo4.vo.Member;
import demo4.vo.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

    private final MemberMybatisRepository memberMybatisRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

     //   Optional<Member> _siteUser = this.memberMybatisRepository.findByusername(username);
        Optional<Member> _siteUser = memberMybatisRepository.getMember(username);


        if (_siteUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        Member siteUser = _siteUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }
        return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);
    }
}