package com.min.qbp.service;

import com.min.qbp.entity.User;
import com.min.qbp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.min.qbp.dto.UserRole.*;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> loginUser = userRepository.findByusername(username);

        if (loginUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        User user = loginUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(user.getUsername())) {
            authorities.add(new SimpleGrantedAuthority(ADMIN.toString()));
        } else {
            authorities.add(new SimpleGrantedAuthority(USER.toString()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
