package com.sungshin.croffle.config.auth;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
//import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Getter
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final Auth auth = new Auth();
    private final Oauth2 oauth2 = new Oauth2();

    @Data
    @Getter
    @RequiredArgsConstructor
    public static class Auth {
        private String tokenSecret;
        private long tokenExpirationMsec;

        @Builder
        public Auth(String tokenSecret, long tokenExpirationMsec) {
            this.tokenSecret = tokenSecret;
            this.tokenExpirationMsec = tokenExpirationMsec;
        }
    }

    @Data
    @RequiredArgsConstructor
    @Getter
    public static final class Oauth2 {
        private List<String> authorizedRedirectUris = new ArrayList<>();

        public Oauth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }
}
