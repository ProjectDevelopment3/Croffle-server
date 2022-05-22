package com.sungshin.croffle.config.auth;

import com.sungshin.croffle.config.auth.dto.OAuthAttributes;
import com.sungshin.croffle.config.auth.dto.SessionUser;
import com.sungshin.croffle.domain.user.User;
import com.sungshin.croffle.domain.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String userNameAttributeName = userRequest
                .getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
        System.out.println("userNameAttributeName:" + userNameAttributeName);

        OAuthAttributes attributes = OAuthAttributes.of(userNameAttributeName, oAuth2User.getAttributes());
        System.out.print("attributes: ");
        System.out.println(attributes);

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(
                        new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByNaverId(attributes.getNaverId())
                .map(entity -> entity.update(attributes.getPhone(), attributes.getProfileUrl()))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
