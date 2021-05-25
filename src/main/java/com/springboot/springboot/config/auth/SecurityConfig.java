package com.springboot.springboot.config.auth;

import com.springboot.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity    // spring security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http // h2-console 옵션 disable
            .csrf().disable().headers().frameOptions().disable()
            // url 별 권한관리
            .and().authorizeRequests()
                .antMatchers("/","css/**","/images/**","/js/**","/h2-console/**").permitAll() //전체열람
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //user만 가능
                .anyRequest().authenticated()  // 인증된(로그인한한) 사자만 허용
            .and().logout().logoutSuccessUrl("/")
            .and().oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }
}
