package com.sungshin.croffle.config.auth;

import com.sungshin.croffle.domain.user.Role;
import com.sungshin.croffle.domain.user.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

@Getter
public class UserPrincipal implements OAuth2User, UserDetails {
    private Long id;
    private String nickname;
    private String naverId;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public UserPrincipal(Long id, String nickname, String naverId,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nickname = nickname;
        this.naverId = naverId;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        System.out.println("UserPrincipal create user");
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRoleKey().equals("ROLE_OWNER")) {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getKey()));
        } else if (user.getRoleKey().equals("ROLE_ADMIN")) {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getKey()));
            authorities.add(new SimpleGrantedAuthority(Role.OWNER.getKey()));
        }
        authorities.add(new SimpleGrantedAuthority(user.getRoleKey()));

        return new UserPrincipal(
                user.getId(),
                user.getNickname(),
                user.getNaverId(),
                authorities
        );
    }

    public static UserPrincipal create(User user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "UserPrincipal{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", naverId='" + naverId + '\'' +
                ", authorities=" + authorities +
                ", attributes=" + attributes +
                '}';
    }
}
