package com.sungshin.croffle.config.auth.dto;

import com.sungshin.croffle.domain.user.Role;
import com.sungshin.croffle.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String nickname;
    private String phone;
    private String profileUrl;
    private String naverId;
    private String name;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey,
                           String nickname, String phone, String profileUrl, String naverId, String name) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.nickname = nickname;
        this.phone = phone;
        this.profileUrl = profileUrl;
        this.naverId = naverId;
        this.name = name;
    }

    public static OAuthAttributes of(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.ofNaver("id", attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        System.out.println("attributes.get(response)" + response);
        return OAuthAttributes.builder()
                .nickname((String) response.get("nickname"))
                .naverId((String) response.get("id"))
                .phone((String) response.get("mobile"))
                .profileUrl((String) response.get("profile_image"))
                .name((String) response.get("name"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User
                .builder()
                .nickname(nickname)
                .naverId(naverId)
                .phone(phone)
                .profileUrl(profileUrl)
                .role(Role.USER)
                .name(name)
                .build();
    }

    @Override
    public String toString() {
        return "OAuthAttributes{" +
                "attributes=" + attributes +
                ", nameAttributeKey='" + nameAttributeKey + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                ", naverId='" + naverId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
