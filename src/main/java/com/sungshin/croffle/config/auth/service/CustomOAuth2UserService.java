package com.sungshin.croffle.config.auth.service;

import com.sungshin.croffle.config.auth.UserPrincipal;
import com.sungshin.croffle.config.auth.dto.OAuthAttributes;
import com.sungshin.croffle.config.auth.dto.SessionUser;
import com.sungshin.croffle.domain.user.User;
import com.sungshin.croffle.domain.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println("oAuth2User:" + oAuth2User);
        try {
            return processOAuth2User(userRequest, oAuth2User);
        } catch (AuthenticationException exception) {
            throw exception;
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }
    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
//        Oauth2UserInfo oAuth2UserInfo =
//                OAuthUserInfoFactory
//                        .getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(),
//                                oAuth2User.getAttributes());
//        if (StringUtils.hasText(oAuth2UserInfo.getEmail())) {
//            throw new OAuth2AuthenticationProcessingException("OAuth2 공급자에서 이메일을 찾을 수 없습니다.");
//        }
        System.out.println("CustomOAuth2UserService processOAuth2User");
        OAuthAttributes attributes =
                OAuthAttributes.of(
                        oAuth2UserRequest.getClientRegistration().getRegistrationId(),
                        oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    //    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = delegate.loadUser(userRequest);
//
//        System.out.println(oAuth2User);
//        String userNameAttributeName = userRequest
//                .getClientRegistration().getProviderDetails()
//                .getUserInfoEndpoint()
//                .getUserNameAttributeName();
//        System.out.println("userNameAttributeName:" + userNameAttributeName);
//
//        OAuthAttributes attributes = OAuthAttributes.of(userNameAttributeName, oAuth2User.getAttributes());
//        System.out.print("attributes: ");
//        System.out.println(attributes);
//
//        User user = saveOrUpdate(attributes);
//        System.out.println(user);
//        httpSession.setAttribute("user", new SessionUser(user));
//
//        return new DefaultOAuth2User(
//                Collections.singleton(
//                        new SimpleGrantedAuthority(user.getRoleKey())),
//                attributes.getAttributes(),
//                attributes.getNameAttributeKey());
//    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        System.out.println("save or update");
        User user = userRepository.findByNaverId(attributes.getNaverId())
                .map(entity -> entity.update(attributes.getPhone(), attributes.getProfileUrl()))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
