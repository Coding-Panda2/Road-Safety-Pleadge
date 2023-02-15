package com.roadpledge.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.roadpledge.security.CitizenUserDetailService;
import com.roadpledge.security.CustomAuthenticationFailureHandler;
import com.roadpledge.security.CustomAuthenticationSuccessHandler;

import ch.qos.logback.classic.selector.servlet.LoggerContextFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	protected UserDetailsService userDetailService() {
		return new CitizenUserDetailService();
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider authenticate = new DaoAuthenticationProvider();
		authenticate.setUserDetailsService(userDetailService());
		authenticate.setPasswordEncoder(passwordEncoder());
		return authenticate;
	}
	
	@Bean
	AuthenticationSuccessHandler authenticationSuccessHandler() {
	    return new CustomAuthenticationSuccessHandler();
	  }

    @Bean
	AuthenticationFailureHandler authenticationFailureHandler() {
	    return new CustomAuthenticationFailureHandler();
	  }

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().cors().disable()
//		    .antMatcher("/up_govt/citizen/**")
		    .authorizeRequests()
		    .antMatchers("/up_govt/citizen/**").hasAnyRole("USER", "ADMIN")
		    .and()
		    .authorizeRequests()
		    .antMatchers("/up_govt/admin/**").hasRole("ADMIN")
		    .and()
		    .authorizeRequests()
		    .antMatchers("/up_govt/mail/**").hasRole("ADMIN").anyRequest().authenticated()
			.and()
			.formLogin()
			.and().httpBasic();



		return http.build();
	}



}
