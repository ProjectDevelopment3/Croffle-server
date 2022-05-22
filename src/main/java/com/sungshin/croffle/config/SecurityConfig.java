package com.sungshin.croffle.config;

import com.sungshin.croffle.config.auth.CustomOAuth2UserService;
import com.sungshin.croffle.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService oAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/board/{id}", "/cafes", "/cafe",
                        "/cafe/recommend", "/review/list")
                .permitAll()
                    .antMatchers("/review", "/report/**", "/likes/**",
                        "/nickname/**", "/stamps", "/coupons", "/board/**")
                        .hasRole(Role.USER.name())
                    .antMatchers("/owner/**").hasRole(Role.OWNER.name())
                    .antMatchers("/**/**").hasRole(Role.ADMIN.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(oAuth2UserService);
    }
}
