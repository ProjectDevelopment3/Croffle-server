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
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService oAuth2UserService;
    private final CustomUserDetailsService userDetailsService;
    private final OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;
    private final OAuthAuthenticationFailureHandler oAuthAuthenticationFailureHandler;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://34.64.186.163");
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedHeader("Authorization");
        config.addAllowedHeader("Cache-Control");
        config.addAllowedHeader("Content-Type");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
////        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://34.64.173.239"));
//        corsConfiguration.setAllowedOrigins(Arrays.asList("http://34.64.186.163", "http://34.64.186.163/", "http://34.64.186.163:3000"));
//        corsConfiguration.setAllowedMethods(Arrays.asList("HEAD", "OPTIONS", "GET",
//                "POST", "PUT", "DELETE"));
////        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
//        corsConfiguration.setMaxAge(3600L);
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        return source;
//    }

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
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .cors()//.configurationSource(corsConfigurationSource())
                .and()
                .authorizeRequests()
                    .antMatchers("/owner/**").hasAnyRole(Role.OWNER.name(), Role.ADMIN.name())
//                    .antMatchers("/review", "/report/**", "/likes/**", "/user/**",
//                        "/nickname", "/stamps", "/coupons", "/board/**", "/owner/verify")
//                        .hasAnyRole(Role.USER.name(), Role.OWNER.name(), Role.ADMIN.name())
//                    .antMatchers(HttpMethod.GET, "/board/{id}", "").hasAnyRole()
                    .antMatchers(HttpMethod.POST, "/board/{id}").hasAnyRole(Role.USER.name(), Role.OWNER.name(), Role.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/board/{id}}").hasAnyRole(Role.USER.name(), Role.OWNER.name(), Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/board/{id}").hasAnyRole(Role.USER.name(), Role.OWNER.name(), Role.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/board/{id}").permitAll()
                    .antMatchers("/board", "/likes", "/like", "/like/**", "/coupons",
                            "/coupon/use", "/owner/verify", "/owner/find-user", "/report/info", "/report/cafe", "/report/menu",
                            "/review", "/review/**", "/stamps", "/user/me", "/nickname")
                    .hasAnyRole(Role.USER.name(), Role.OWNER.name(), Role.ADMIN.name())
                .antMatchers("/", "/boards", "/cafes", "/cafe", "/cafe/**",
                        "/cafe/recommend", "/review/list", "/nickname/verify", "/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/review/{id}").permitAll()
//                .anyRequest().authenticated()
                .and()
//                    .logout()
//                        .logoutSuccessUrl("/")
//                .and()
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
