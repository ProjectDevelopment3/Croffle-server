package com.sungshin.croffle.config;

import com.sungshin.croffle.config.auth.service.CustomOAuth2UserService;
import com.sungshin.croffle.config.auth.service.CustomUserDetailsService;
import com.sungshin.croffle.config.auth.HttpCookieOAuth2AuthorizationRequestRepository;
import com.sungshin.croffle.config.auth.handler.OAuthAuthenticationFailureHandler;
import com.sungshin.croffle.config.auth.handler.OAuthAuthenticationSuccessHandler;
import com.sungshin.croffle.config.auth.token.TokenAuthenticationFilter;
import com.sungshin.croffle.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService oAuth2UserService;
    private final CustomUserDetailsService userDetailsService;
    private final OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;
    private final OAuthAuthenticationFailureHandler oAuthAuthenticationFailureHandler;
//    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .authorizeRequests()
                    .antMatchers("/board/{id}", "/cafes", "/cafe",
                        "/cafe/recommend", "/review/list")
                .permitAll()
                    .antMatchers("/review", "/report/**", "/likes/**", "/user/**",
                        "/nickname/**", "/stamps", "/coupons", "/board/**", "/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs")
                        .hasRole(Role.USER.name())
                    .antMatchers("/owner/**").hasRole(Role.OWNER.name())
                    .antMatchers("/review", "/report/**", "/likes/**", "/user/**",
                            "/nickname/**", "/stamps", "/coupons",
                            "/board/**", "/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs",
                            "/owner/**").hasRole(Role.ADMIN.name())

                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .authorizationEndpoint()
                            .baseUri("/oauth2/authorization")
                                .authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository())
                .and()
                    .userInfoEndpoint()
                        .userService(oAuth2UserService)
                .and()
                    .successHandler(oAuthAuthenticationSuccessHandler)
                    .failureHandler(oAuthAuthenticationFailureHandler);
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
