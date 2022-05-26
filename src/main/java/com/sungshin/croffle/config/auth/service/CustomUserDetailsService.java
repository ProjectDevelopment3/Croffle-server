package com.sungshin.croffle.config.auth.service;

import com.sungshin.croffle.config.auth.UserPrincipal;
import com.sungshin.croffle.domain.jpa.UserRepository;
import com.sungshin.croffle.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with name: "
                        + name));

        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(
//                        () -> new ResourceNotFoundException("User", "id", id)
                        () -> new UsernameNotFoundException("User" + id)
                );
        return UserPrincipal.create(user);
    }
}
