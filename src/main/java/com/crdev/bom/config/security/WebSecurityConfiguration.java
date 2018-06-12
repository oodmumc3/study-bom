package com.crdev.bom.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 스프링 시큐리티 설정 클래스
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        // Spring Security should completely ignore URLs starting with /resources/
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/board/**").hasRole("USER") // '/board'로 들어오는 모든 요청에 대해서 'USER'권한으로 제어
                .antMatchers("/login").anonymous()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("user.id")
                        .passwordParameter("user.pw")
                        .defaultSuccessUrl("/")
                        .successHandler(this.loginSuccessHandler("/", "targetUrl"))
                        .failureHandler(this.loginFailureHandler())
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler())
                .and().exceptionHandling().accessDeniedPage("/error/403");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("guest").password("1234").roles("GUEST")
                .and()
                .withUser("user").password("qwer1234").roles("USER");
    }

    /**
     * 로그인 성공 핸들러 반환
     */
    private AuthenticationSuccessHandler loginSuccessHandler(String defaultTargetUrl, String targetUrlParameter) {
        return new MyLoginSuccessHandler(defaultTargetUrl, targetUrlParameter);
    }

    /**
     * 로그인 실패 핸들러 반환
     */
    private AuthenticationFailureHandler loginFailureHandler() {
        return (req, res, ex) -> {
            SessionFlashMapManager sfmm = new SessionFlashMapManager();
            FlashMap fm = new FlashMap();
            fm.put("loginErrorMsg", "로그인에 실패하였습니다. 다시 입력해주세요.");
            sfmm.saveOutputFlashMap(fm, req, res);
            res.sendRedirect("/login");
        };
    }

    /**
     * 로그인 성공 핸들러 클래스
     */
    private class MyLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

        MyLoginSuccessHandler(String defaultTargetUrl, String targetUrlParameter) {
            super.setDefaultTargetUrl(defaultTargetUrl);
            super.setTargetUrlParameter(targetUrlParameter);
        }

        @Override
        public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {
            this.onAuthenticationSuccessProcess(req.getParameter("user.id"));
            super.onAuthenticationSuccess(req, res, auth);
        }

        private void onAuthenticationSuccessProcess(String userId) {
            System.out.println("userId = " + userId);
        }
    }

}